package com.accenture.academico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.Analista;
import com.accenture.academico.repository.AnalistaRepository;

public class AnalistaService {
	
	@Autowired
	private AnalistaRepository analistaRepository;
	
	// METODO PARA BUSCAR TODOS OS ANALISTAS
	public List<Analista> buscarAnalistas(Pageable pageable){
		return this.analistaRepository.findAll(pageable).getContent();
	}
	
	
	//MÉTODO PARA SALVAR ANALISTA
	public void salvarAnalista(Analista analista) {
		this.analistaRepository.save(analista);
	}
	
	
	//MÉTODO PARA ALTERAR ANALISTA
	public void atualizarAnalista(Analista analista, Long id) {
		Analista analistaBD = this.analistaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Analista não encontrado"));
	    BeanUtils.copyProperties(analista, analistaBD, "id");
	    this.salvarAnalista(analistaBD);
	}
	
	
	//MÉTODO PARA EXCLUIR ANALISTA
	public void excluirAnalista(Long id) {
		this.analistaRepository.deleteById(id);
	
	}
	
	//MÉTODO PARA BUSCAR ANALISTA POR ID
	public Analista buscarAnalistaID(Long id){
		Optional<Analista> analista = analistaRepository.findById(id);
		return analista.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Analista não encontrado"));
	
	}

}