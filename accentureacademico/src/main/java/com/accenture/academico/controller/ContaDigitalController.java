package com.accenture.academico.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;

import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.model.Operacao;
import com.accenture.academico.service.ContaDigitalService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController // recebe requisições restful
@RequestMapping(value = "/contaDigital")
@SecurityRequirement(name = "accenture")
public class ContaDigitalController {

	@Autowired
	private ContaDigitalService contaDigitalService;

	// MÉTODO PARA BUSCAR TODAS AS CONTAS DIGITAL
	@GetMapping("/")
	public List<ContaDigital> buscarContaDigital() {
		return this.contaDigitalService.buscarContaDigital();
	}

	// MÉTODO PARA SALVAR CONTAS DIGITAL
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarcontaDigital(@RequestBody ContaDigital contaDigital) {
		this.contaDigitalService.salvarContaDigital(contaDigital);
	}

	// MÉTODO PARA ALTERAR CONTA DIGITAL
	@PutMapping("/{id}")
	public void alterarcontaDigital(@PathVariable("id") Long id, @RequestBody ContaDigital contaDigital) {
		this.contaDigitalService.alterarContaDigital(contaDigital, id);
	}

	// MÉTODO PARA DELETAR CONTA DIGITAL
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluircontaDigital(@PathVariable("id") Long id) {
		this.contaDigitalService.excluirContaDigital(id);

		return "Conta corrente de ID " + id + " foi deletada com sucesso";
	}

	// MÉTODO PARA BUSCAR CONTADIGITAL POR ID
	@GetMapping("/{id}")
	public ContaDigital buscarcontaDigitalID(@PathVariable("id") Long id) {
		return this.contaDigitalService.buscarContaDigitalID(id);
	}

	// MÉTODO SAQUE
	@PutMapping("/saque/{id}")
	public void saque(@PathVariable("id") Long id, @RequestBody double valor) {
		this.contaDigitalService.sacar(valor, id);
	}

	// MÉTODO DEPOSITO
	@PutMapping("/deposito/{id}")
	public void deposito(@PathVariable("id") Long id, @RequestBody double valor) {
		this.contaDigitalService.depositar(valor, id);
	}

	// MÉTODO TRANSFERENCIA
	@PutMapping("/transferencia/{idContaOrigem}/{idContaDestino}")
	public void transferencia(@PathVariable("idContaOrigem") Long idContaOrigem,
			@PathVariable("idContaDestino") Long idContaDestino, @RequestBody double valor) {
		this.contaDigitalService.transferir(idContaDestino, idContaOrigem, valor);
	}

	// MÉTODO PAGAMENTO
	@PutMapping("/pagamento/{id}")
	public void pagamento(@PathVariable("id") Long id, @RequestBody double valor) {
		this.contaDigitalService.pagamento(valor, id);
	}

	// MÉTODO PARA BUSCAR EXTRATO POR CONTA
	@GetMapping("/extrato/{id}")
	public List<Operacao> extratoPorConta(@PathVariable("id") Long id) {
		return this.contaDigitalService.extrato(id);
	}

}