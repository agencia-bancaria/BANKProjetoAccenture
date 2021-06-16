package com.accenture.academico.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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



@RestController //recebe requisições restful
@RequestMapping(value = "/agencias")
public class AgenciaController {
	
	@Autowired
	private AgenciaService agenciaService;
	
	
	//METODO PARA BUSCAR TODAS AS AGENCIAS
	@GetMapping("/")
//	@PreAuthorize("hasRole('ANALISTA')")
    public List<Agencia> buscarAgencia(){
		return this.agenciaService.buscarAgencias();
	}
			    
	//MÉTODO PARA SALVAR AGENCIA
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void salvarAgencia(@RequestBody String nomeAgencia, String telefone, String numeroAgencia) {
		this.agenciaService.salvarAgencia(LocalDate.now(), nomeAgencia, telefone, numeroAgencia);
	}
		
	
	//MÉTODO PARA ALTERAR AGENCIA
	@PutMapping("/{id}")
	public void alterarAgencia(@PathVariable("id") Long id, @RequestBody String nomeAgencia, String telefone, String numeroAgencia) {
		this.agenciaService.atualizarAgencia(nomeAgencia, telefone, numeroAgencia, id);
	}
	
	//MÉTODO PARA DELETAR AGENCIA
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String excluirAgencia(@PathVariable("id") Long id) {
		
		this.agenciaService.excluirAgencia(id);
		
		return "Agencia de ID " + id + " foi deletada com sucesso!";
				
	}
	
	//MÉTODO PARA BUSCAR AGENCIA POR ID
	@GetMapping("/{id}")
	public Agencia buscarAgenciaID(@PathVariable("id")Long id){
		return this.agenciaService.buscarAgenciaID(id);
	}
		
		

}
