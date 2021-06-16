package com.accenture.academico.service;

import java.time.LocalDate;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public static boolean validarCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char digVer10, digVer11;
        int soma, i, resto, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            soma = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(CPF.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11))
                digVer10 = '0';
            else digVer10 = (char)(resto + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            soma = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11))
                 digVer11 = '0';
            else digVer11 = (char)(resto + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((digVer10 == CPF.charAt(9)) && (digVer11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }
	
	public ContaDigital criarConta(Long id, String cpf) {
		ContaDigital contaDigital = new ContaDigital();
		try {
			if(validarCPF(cpf)) {
				contaDigital.setDataCriacao(LocalDate.now());
				contaDigital.setContaSaldo(0);
				contaDigital.setCliente(clienteService.buscarClienteID(id));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return contaDigital;
	}
	
	//MÉTODO PARA BUSCAR OS CLIENTES
	public List<Cliente> buscarClientes(){
		return this.clienteRepository.findAll();
	}	
	
	//MÉTODO PARA SALVA CLIENTE
	public Cliente salvarCliente(Cliente cliente) {
		String cpf = cliente.getClienteCPF();
		String nome = cliente.getClienteNome();
		String fone = cliente.getClienteFone();
		
		
			if(validarCPF(cpf)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF INVÁLIDO");
			
			}if(nome == null || nome.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O NOME INVÁLIDO");
			
			}if(fone == null || fone.equals("")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O TELEFONE INVÁLIDO");
				
			}else {
				
				this.clienteRepository.save(cliente);
			}
			
		return cliente;
		
	}
	
	//MÉTODO PARA ALTERAR CLIENTE
	public void atualizarCliente(Cliente cliente, Long id) {
		Cliente clienteBD = this.clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
		BeanUtils.copyProperties(cliente, clienteBD, "id");
		this.salvarCliente(clienteBD);
	
	}
	
	
	//MÉTODO PARA EXCLUIR
	public void excluirCliente(Long id) {
		this.clienteRepository.deleteById(id);
	}

	
	//MÉTODO PARA BUSCAR CLIENTE POR ID
	public Cliente buscarClienteID(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
	}
}