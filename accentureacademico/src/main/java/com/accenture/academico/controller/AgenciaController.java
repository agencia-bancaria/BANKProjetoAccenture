package com.accenture.academico.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.accenture.academico.model.Agencia;
import com.accenture.academico.service.AgenciaService;

import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController // recebe requisições restful
@RequestMapping(value = "/agencias")
@SecurityRequirement(name = "accenture")
public class AgenciaController {

	@Autowired
	private AgenciaService agenciaService;

	// METODO PARA BUSCAR TODAS AS AGENCIAS
	@Operation(summary ="Lista todos os Agencia")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Agencia listados com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/")
	public List<Agencia> buscarAgencia() {
		return this.agenciaService.buscarAgencias();
	}

	// MÉTODO PARA SALVAR AGENCIA
	@Operation(summary ="Cria novo Agencia")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Agencia criado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarAgencia(@RequestBody @Valid Agencia agencia) {
		this.agenciaService.salvarAgencia(agencia);
	}

	// MÉTODO PARA ALTERAR AGENCIA
	@Operation(summary ="Atualiza Agencia por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Agencia atualizado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/{id}")
	public void alterarAgencia(@Parameter(description = "Id de Agencia")
							@PathVariable("id") @Valid Long id, @RequestBody @Valid Agencia agencia) {
		this.agenciaService.atualizarAgencia(agencia, id);
	}

	// MÉTODO PARA DELETAR AGENCIA
	@Operation(summary ="Remove Agencia por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Agencia removido com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluirAgencia(@Parameter(description = "Id de Agencia")
								@PathVariable("id") @Valid Long id) {

		this.agenciaService.excluirAgencia(id);

		return "Agencia de ID " + id + " foi deletada com sucesso!";

	}

	// MÉTODO PARA BUSCAR AGENCIA POR ID
	@Operation(summary ="Busca Agencia por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Agencia encontrado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/{id}")
	public Agencia buscarAgenciaID(@Parameter(description = "Id de Agencia")
								 @PathVariable("id") @Valid Long id) {
		return this.agenciaService.buscarAgenciaID(id);
	}

}
