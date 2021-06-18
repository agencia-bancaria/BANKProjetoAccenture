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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.model.Operacao;
import com.accenture.academico.repository.OperacaoRepository;
import com.accenture.academico.service.ContaDigitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController // recebe requisições restful
@RequestMapping(value = "/contaDigital")
@SecurityRequirement(name = "accenture")
public class ContaDigitalController {

	@Autowired
	private ContaDigitalService contaDigitalService;
	@Autowired
	
	
	// MÉTODO PARA BUSCAR TODAS AS CONTAS DIGITAL
	@Operation(summary ="Lista todas as Contas Digitais",description = "CONTAS")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Contas Digitais listadas com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/")
	public List<ContaDigital> buscarContaDigital() {
		return this.contaDigitalService.buscarContaDigital();
	}

	// MÉTODO PARA SALVAR CONTAS DIGITAL
	@Operation(summary ="Cria nova Conta Digital")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Conta Digital criada com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.")})
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarcontaDigital(@RequestBody ContaDigital contaDigital) {
		this.contaDigitalService.salvarContaDigital(contaDigital);
	} 

	// MÉTODO PARA ALTERAR CONTA DIGITAL
	@Operation(summary ="Atualiza Conta Digital por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Conta Digital atualizada com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/{id}")
	public void alterarcontaDigital(@Parameter(description = "Id de Conta Digital")
								   @PathVariable("id") Long id, @RequestBody ContaDigital contaDigital) {
		this.contaDigitalService.alterarContaDigital(contaDigital, id);
	}

	// MÉTODO PARA DELETAR CONTA DIGITAL
	@Operation(summary ="Remove Conta Digital por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Conta Digital removida com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluircontaDigital(@Parameter(description = "Id de Conta Digital")
									@PathVariable("id") Long id) {
		this.contaDigitalService.excluirContaDigital(id);

		return "Conta digital de ID " + id + " foi deletada com sucesso";
	}

	// MÉTODO PARA BUSCAR CONTADIGITAL POR ID
	@Operation(summary ="Busca Conta Digital por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Conta Digital encontrado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/{id}")
	public ContaDigital buscarcontaDigitalID(@Parameter(description = "Id de Conta Digital")
											@PathVariable("id") Long id) {
		return this.contaDigitalService.buscarContaDigitalID(id);
	}

	// MÉTODO SAQUE
	@Operation(summary ="Saca valor de Conta Digital por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Conta Digital encontrado com sucesso, saque realizado, saldo atualizado."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/saque/{id}/{valor}")
	public void saque(@Parameter(description = "Id de Conta Digital")
					@PathVariable("id") Long id, @Parameter(description = "Valor do saque") @PathVariable("valor") double valor) {
		this.contaDigitalService.sacar(valor, id);
	}

	// MÉTODO DEPOSITO
	@Operation(summary ="Deposita valor de Conta Digital por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Conta Digital encontrado com sucesso, saldo atualizado."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/deposito/{id}/{valor}")
	public void deposito(@Parameter(description = "Id de Conta Digital")
						@PathVariable("id") Long id, @Parameter(description = "Valor de depósito") @PathVariable("valor") double valor) {
		this.contaDigitalService.depositar(valor, id);
	}

	// MÉTODO TRANSFERENCIA
	@Operation(summary ="Transfere valor de Conta Digital para outra por Id's")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Transferencia realizada com sucesso, saldo de contas atualizadas."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/transferencia/{idContaOrigem}/{idContaDestino}/{valor}")
	public void transferencia(@Parameter(description = "Id de Conta Origem") @PathVariable("idContaOrigem") Long idContaOrigem,
			@Parameter(description = "Id de Conta Destino") @PathVariable("idContaDestino") Long idContaDestino,@Valid @Parameter(description = "Valor de transfêrencia") 
	@PathVariable("valor") double valor) {
		this.contaDigitalService.transferir(idContaDestino, idContaOrigem, valor);
	}

	// MÉTODO PAGAMENTO
	@Operation(summary ="Pagamento de contas por Id de Conta Digital")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Pagamento realizado com sucesso, saldo atualizado."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/pagamento/{id}/{valor}")
	public void pagamento(@Parameter(description = "Id de Conta") @PathVariable("id") Long id, @Parameter(description = "Valor de pagamento") @PathVariable("valor") double valor) {
		this.contaDigitalService.pagamento(valor, id);
	}

	// MÉTODO PARA BUSCAR EXTRATO POR CONTA
	@Operation(summary ="Extrato de Conta Digital por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Extrato de Conta Digital."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/extrato/{id}")
	public List<Operacao> extratoPorConta(@Parameter(description = "Id de Conta")
	@PathVariable("id") Long id) {
		List<Operacao> operacoes = new ArrayList<Operacao>();
		for (Operacao operacao : this.contaDigitalService.extrato(id)) {
			if(operacao.getConta().getIdConta() == id)
				operacoes.add(operacao);
		} ;
		return operacoes;
}
	

}