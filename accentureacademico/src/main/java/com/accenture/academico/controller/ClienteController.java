package com.accenture.academico.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.http.HttpStatus;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.service.ClienteService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController // recebe requisições restful
@RequestMapping(value = "/clientes")
@SecurityRequirement(name = "accenture")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	// MÉTODO PARA BUSCAR TODOS OS CLIENTES
	@Operation(summary ="Lista todos os Clientes")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Clientes listados com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.")})
	@GetMapping("/")
	public List<Cliente> buscarClientes() {
		return this.clienteService.buscarClientes();
	}

	// MÉTODO PARA SALVA CLIENTE
	@Operation(summary ="Cria novo Cliente")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente criado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarCliente(@RequestBody Cliente cliente) {
		this.clienteService.salvarCliente(cliente);
	}

	// MÉTODO PARA ATUALIZAR CLIENTE
	@Operation(summary ="Atualiza Cliente por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente atualizado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@PutMapping("/{id}")
	public void atualizarCliente(@Parameter(description = "Id de Cliente")
								@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		this.clienteService.atualizarCliente(cliente, id);
	}

	// MÉTODO PARA EXCLUIR CLIENTE
	@Operation(summary ="Remove Cliente por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente removido com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluirCliente(@Parameter(description = "Id de Cliente")
								@PathVariable("id") Long id) {
		this.clienteService.excluirCliente(id);

		return "Cliente de ID " + id + " foi excluido com sucesso!";
	}

	// MÉTODO PARA BUSCAR CLIENTES POR ID
	@Operation(summary ="Busca Cliente por Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente encontrado com sucesso."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/{id}")
	public Cliente buscarAgenciaID(@Parameter(description = "Id de Cliente")
								@PathVariable("id") Long id) {
		return this.clienteService.buscarClienteID(id);
	}
}