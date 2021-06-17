package com.accenture.academico.service;

import java.util.List;



import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.Operacao;
import com.accenture.academico.repository.OperacaoRepository;

@Service
public class OperacaoService {
	
	@Autowired
	private OperacaoRepository operacaoRepository;
	
	// METODO PARA BUSCAR TODOS OS OPERACAO
	public List<Operacao> buscarOperacoes(){
		return this.operacaoRepository.findAll();
	}
	
	
	//MÉTODO PARA SALVAR OPERACAO
	public void salvarOperacao(Operacao operacao) {
		
		this.operacaoRepository.save(operacao);
	}
	
	
	//MÉTODO PARA ALTERAR OPERACAO
	public void atualizarOperacao(Operacao operacao, Long id) {
		Operacao operacaoBD = this.operacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operacao não encontrada!"));
	    BeanUtils.copyProperties(operacao, operacaoBD, "id");
	    this.salvarOperacao(operacaoBD);
	}
	
	
	//MÉTODO PARA EXCLUIR OPERACAO
	public void excluirOperacao(Long id) {
		this.operacaoRepository.deleteById(id);
	
	}
	
	//MÉTODO PARA BUSCAR OPERACAO POR ID
	public Operacao buscarOperacaoID(Long id){
		Optional<Operacao> operacao = operacaoRepository.findById(id);
		return operacao.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operacao não encontrada!"));
	
	}

}