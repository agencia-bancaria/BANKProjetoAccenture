package com.accenture.academico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.accenture.academico.service.AnalistaService;
import com.accenture.academico.service.ClienteService;
import com.accenture.academico.model.Analista;

@RestController //recebe requisições restful
@RequestMapping(value = "/analistas")
public class AnalistaController {

	@Autowired
	private AnalistaService analistaService;
	
	//METODO PARA BUSCAR TODAS AS AnalistaS
		@GetMapping("/")
		@PreAuthorize("hasRole('ANALISTA')")
	    public List<Analista> buscarAnalista(Pageable pageable){
			return this.analistaService.buscarAnalistas(pageable);
		}
		
		
		//MÉTODO PARA SALVAR Analista
		@PostMapping("/")
		@ResponseStatus(HttpStatus.CREATED)
		@PreAuthorize("hasRole('ANALISTA')")
		public void salvarAnalista(@RequestBody Analista analista) {
			this.analistaService.salvarAnalista(analista);
		}
			
		
		//MÉTODO PARA ALTERAR Analista
		@PutMapping("/{id}")
		@PreAuthorize("hasRole('ANALISTA')")
		public void alterarAnalista(@PathVariable("id") Long id, @RequestBody Analista analista) {
			this.analistaService.atualizarAnalista(analista, id);
		}
		
		//MÉTODO PARA DELETAR Analista
		@DeleteMapping(value = "/{id}", produces = "application/text")
		@PreAuthorize("hasRole('ANALISTA')")
		public String excluirAnalista(@PathVariable("id") Long id) {
			
			this.analistaService.excluirAnalista(id);
			
			return "Analista de ID " + id + " foi deletada com sucesso!";
					
		}
		
		//MÉTODO PARA BUSCAR Analista POR ID
		@GetMapping("/{id}")
		@PreAuthorize("hasRole('ANALISTA')")
		public Analista buscarAnalistaID(@PathVariable("id")Long id){
			return this.analistaService.buscarAnalistaID(id);
		}
}
