package com.accenture.academico.service;

import java.time.LocalDate;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.*;
import com.accenture.academico.repository.AgenciaRepository;


@Service
public class AgenciaService {
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	// METODO PARA BUSCAR TODAS AS AGENCIAS
	public List<Agencia> buscarAgencias(){
		return this.agenciaRepository.findAll();
	}
	
	
	//MÉTODO PARA SALVAR AGENCIA
	public void salvarAgencia(Agencia agencia) {
//		Agencia agencia = new Agencia();
//		agencia.setDataAbertura(dataAbertura);
//		agencia.setNomeAgencia(nomeAgencia);
//		agencia.setNumeroAgencia(numeroAgencia);
//		agencia.setTelefoneAgencia(telefoneAgencia);
		this.agenciaRepository.save(agencia);
	}
	
	
	//MÉTODO PARA ALTERAR AGENCIA
	public void atualizarAgencia(Agencia novaAgencia, Long id) {
		Agencia agenciaBD = this.agenciaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
//		Agencia novaAgencia = new Agencia(nomeAgencia, numeroAgencia, telefone, agenciaBD.getDataAbertura());
		novaAgencia.setIdAgencia(id);
	    BeanUtils.copyProperties(novaAgencia, agenciaBD, "id");
	    this.salvarAgencia(agenciaBD);
	}
	
	
	//MÉTODO PARA EXCLUIR AGENCIA
	public void excluirAgencia(Long id) {
		this.agenciaRepository.deleteById(id);
	
	}
	
	//MÉTODO PARA BUSCAR AGENCIA POR ID
	public Agencia buscarAgenciaID(Long id){
		Optional<Agencia> agencia = agenciaRepository.findById(id);
		return agencia.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
	}
	
	//MÉTODO PARA BUSCAR TODAS AS CONTAS DA AGÊNCIA
	public List<ContaDigital> listarContas(Long id){
		return buscarAgenciaID(id).getContas();
	}
     

}
