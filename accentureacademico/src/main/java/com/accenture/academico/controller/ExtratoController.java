package com.accenture.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.accenture.academico.model.Operacao;
import com.accenture.academico.repository.OperacaoRepository;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Controller
@RequestMapping("/extrato")
@SecurityRequirement(name = "accenture")
public class ExtratoController {
	
	@Autowired
	private OperacaoRepository operacao;
	
	@Operation(summary ="---------------------------")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Pagamento realizado com sucesso, saldo atualizado."),
			@ApiResponse(code = 401, message = "Você não está autorizado a ver o recurso."),
		    @ApiResponse(code = 403, message = "O acesso ao recurso que você estava tentando acessar é proibido."),
		    @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado.") })
	@GetMapping("/")
	public ModelAndView listar() {
		List<Operacao> lista = operacao.findAll();
		
		ModelAndView modelAndView = new ModelAndView("contatos");		
		modelAndView.addObject("operacao", lista);
		
		return modelAndView;
	}
}
