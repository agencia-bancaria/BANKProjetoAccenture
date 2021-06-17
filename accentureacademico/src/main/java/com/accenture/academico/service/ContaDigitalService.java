package com.accenture.academico.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.exceptions.OperacaoInvalidaException;
import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.model.Operacao;
import com.accenture.academico.model.TipoOperacao;
import com.accenture.academico.repository.ClienteRepository;
import com.accenture.academico.repository.ContaDigitalRepository;
import com.accenture.academico.repository.OperacaoRepository;
import com.sun.el.parser.ParseException;

@Service
public class ContaDigitalService {

	@Autowired
	private ContaDigitalRepository contaDigitalRepository;
	@Autowired
	private OperacaoRepository operacaoRepository;
	@Autowired
	private ClienteService clienteService;

	// MÉTODO PARA BUSCAR TODAS AS CONTAS DIGITAL
	public List<ContaDigital> buscarContaDigital() {
		return this.contaDigitalRepository.findAll();
	}

	// MÉTODO PARA SALVAR CONTA DIGITAL
	public void salvarContaDigital(ContaDigital contaDigital)  {

		System.out.println(clienteService.buscarClienteID(contaDigital.getCliente().getIdCliente()));
		if(clienteService.buscarClienteID(contaDigital.getCliente().getIdCliente()) == null) {
			//tratar
		} else {
			this.contaDigitalRepository.save(contaDigital);
		}
	}

	// MÉTODO PARA ALTERAR CONTA DIGITAL
	public void alterarContaDigital(ContaDigital contaDigital, Long id) {
		ContaDigital conta = this.contaDigitalRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
		BeanUtils.copyProperties(contaDigital, conta, "id");
		this.salvarContaDigital(conta);
	}

	// MÉTODO PARA EXCLUIR CONTA DIGITAL
	public void excluirContaDigital(Long id) {
		this.contaDigitalRepository.deleteById(id);
	}

	// MÉTODO PARA BUSCAR CONTA DIGITAL POR ID
	public ContaDigital buscarContaDigitalID(Long id) {
		Optional<ContaDigital> conta = contaDigitalRepository.findById(id);
		return conta.orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta Digital não encontrada!"));
	}

	// realiza o saque da conta e retorna true se a operaçao for bem sucedida
	@SuppressWarnings("static-access")
	public boolean sacar(double valor, Long idConta) {
		ContaDigital conta = this.buscarContaDigitalID(idConta);
		if(conta == null) {
			throw new OperacaoInvalidaException("Conta Invalida!");
		}
		
		if (valor >= conta.getValorsaqueminimo() && valor < conta.getContaSaldo()) {
			conta.setContaSaldo(conta.getContaSaldo() - valor);
			Date today = Calendar.getInstance().getTime();
			Operacao operacao = new Operacao(today, TipoOperacao.SAQUE, valor, conta);
			operacaoRepository.save(operacao);
			this.alterarContaDigital(conta, idConta);
			return true;
		} else {
			throw new OperacaoInvalidaException("Valor Inválido!");
		}
	}

	// debitar um valor do saldo
	public boolean debitar(double valor, Long idConta) {
		ContaDigital conta = this.buscarContaDigitalID(idConta);
		if(conta == null) {
			throw new OperacaoInvalidaException("Conta Invalida!");
		}
		
		if (valor < conta.getContaSaldo() && valor > 0) {
			conta.setContaSaldo(conta.getContaSaldo() - valor);
			this.alterarContaDigital(conta, idConta);
			
			return true;
		} else if(valor ==0){
			throw new OperacaoInvalidaException("Valor nulo não é permitido!");
		}else {
			throw new OperacaoInvalidaException("Valor inválido!");
		}
		

	}

	// realiza o deposito e retorna true se for bem sucedida
	public void depositar(double valor, Long idConta) {
		ContaDigital conta = this.buscarContaDigitalID(idConta);
		if(conta == null) {
			throw new OperacaoInvalidaException("Conta Invalida!");
		} else if(valor <= 0) {
			throw new OperacaoInvalidaException("Valor Inválido!");
		}
		
		conta.setContaSaldo(conta.getContaSaldo() + valor);
		this.alterarContaDigital(conta, idConta);
		
		Date today = Calendar.getInstance().getTime();
		Operacao operacao = new Operacao(today, TipoOperacao.DEPOSITO, valor, conta);
		operacaoRepository.save(operacao);
	}

	// realiza o saque de uma conta e deposito numa conta de destino
	public boolean transferir(Long idContaDestino, Long idContaOrigem, double valor) {

		ContaDigital contaDestino = this.buscarContaDigitalID(idContaDestino);
		if(contaDestino == null) {
			throw new OperacaoInvalidaException("Conta Invalida!");
		}
		
		if (this.debitar(valor, idContaOrigem)) {
			this.depositar(valor, idContaDestino);
			Date today = Calendar.getInstance().getTime();
			Operacao operacao = new Operacao(today, TipoOperacao.TRANSFERENCIA, valor, contaDestino);
			operacaoRepository.save(operacao);
			return true;
		} else {
			return false;
		}

	}

	public boolean pagamento(double valor, Long idConta) {
		ContaDigital conta = this.buscarContaDigitalID(idConta);
		if(conta == null) {
			throw new OperacaoInvalidaException("Conta Invalida!");
		}
		
		if (this.debitar(valor, idConta)) {
			Date today = Calendar.getInstance().getTime();
			Operacao operacao = new Operacao(today, TipoOperacao.PAGAMENTO, valor, conta);
			operacaoRepository.save(operacao);
			return true;
		} else {
			return false;
		}

	}

	// EXTRATO TOTAL
	public List<Operacao> extrato(Long idConta) {
		ContaDigital conta = this.buscarContaDigitalID(idConta);
		if(conta == null) {
			throw new OperacaoInvalidaException("Conta Invalida!");
		}
		
		return operacaoRepository.findAll();
	}

	// EXTRATO A PARTIR DE UMA DATA INICIO ATÉ A DATA ATUAL
	public List<Operacao> extratoTempo(Date dataInicio, Long idConta) throws ParseException {
		ContaDigital conta = this.buscarContaDigitalID(idConta);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<Operacao> operacoesExtrato = new ArrayList<Operacao>();
		
		if(conta == null) {
			throw new OperacaoInvalidaException("Conta Invalida!");
		}
		
		for (Operacao operacao : operacaoRepository.findAll()) {

			Date dataOperacao;
			try {
				dataOperacao = formatador.parse(formatador.format(operacao.getDataHoraOperacao()));
				Date dataInicioExtrato;
				dataInicioExtrato = formatador.parse(formatador.format(dataInicio));
				if (dataOperacao.compareTo(dataInicioExtrato) >= 0)
					operacoesExtrato.add(operacao);
			} catch (java.text.ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return operacoesExtrato;
	}

}