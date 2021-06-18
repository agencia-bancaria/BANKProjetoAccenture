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

import com.accenture.academico.service.AnalistaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.accenture.academico.model.Agencia;
import com.accenture.academico.model.Analista;

@RestController // recebe requisições restful
@RequestMapping(value = "/analistas")
@SecurityRequirement(name = "accenture")
public class AnalistaController {

	@Autowired
	private AnalistaService analistaService;

	// METODO PARA BUSCAR TODAS AS AnalistaS
	@Operation(summary ="Lista todos os Analistas")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Analistas listados com sucesso.", responseHeaders = {}),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/")
	public List<Analista> buscarAnalista() {
		return this.analistaService.buscarAnalistas();
	}

	// MÉTODO PARA SALVAR Analista
	@Operation(summary ="Cria novo Analista")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Analista criado com sucesso.", responseHeaders = {}),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarAnalista(@RequestBody @Valid Analista analista) {
		this.analistaService.salvarAnalista(analista);
	}

	// MÉTODO PARA ALTERAR Analista
	@Operation(summary ="Atualiza Analista por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Analista atualizado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/{id}")
	public void alterarAnalista(@Parameter(description = "Id de Analista")
			@PathVariable("id") @ Valid Long id, @RequestBody @Valid Analista analista) {
		this.analistaService.atualizarAnalista(analista, id);
	}

	// MÉTODO PARA DELETAR Analista
	@Operation(summary ="Remove Analista por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Analista removido com sucesso.", responseHeaders = {}),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluirAnalista(@Parameter(description = "Id de Analista")
								@PathVariable("id") @Valid Long id) {

		this.analistaService.excluirAnalista(id);

		return "Analista de ID " + id + " foi deletada com sucesso!";

	}

	// MÉTODO PARA BUSCAR Analista POR ID
	@Operation(summary ="Busca Analista por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Analista encontrado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/{id}")
	public Analista buscarAnalistaID(@Parameter(description = "Id de Analista")
									@PathVariable("id") @Valid Long id) {
		return this.analistaService.buscarAnalistaID(id);
	}
}