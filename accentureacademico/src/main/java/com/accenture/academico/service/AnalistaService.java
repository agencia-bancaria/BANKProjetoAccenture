package com.accenture.academico.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.Analista;
import com.accenture.academico.repository.AnalistaRepository;

@Service
public class AnalistaService {

	@Autowired
	private AnalistaRepository analistaRepository;

	// METODO PARA BUSCAR TODOS OS ANALISTAS
	public List<Analista> buscarAnalistas() {
		return this.analistaRepository.findAll();
	}

	// MÉTODO PARA SALVAR ANALISTA
	public Analista salvarAnalista(Analista analista) {
		String cpf = analista.getCpfAnalista();
		String nome = analista.getNomeAnalista();
		String fone = analista.getTelefoneAnalista();

		if (ClienteService.validarCPF(cpf)) {
			this.analistaRepository.save(analista);

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF INVÁLIDO");

		}
		if (nome == null || nome.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O NOME INVÁLIDO");

		}
		if (fone == null || fone.equals("")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O TELEFONE INVÁLIDO");

		}

		return analista;

	}

	// MÉTODO PARA ALTERAR ANALISTA
	public void atualizarAnalista(Analista analista, Long id) {
		Analista analistaBD = this.analistaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Analista não encontrado"));
		BeanUtils.copyProperties(analista, analistaBD, "id");
		this.salvarAnalista(analistaBD);
	}

	// MÉTODO PARA EXCLUIR ANALISTA
	public void excluirAnalista(Long id) {
		this.analistaRepository.deleteById(id);

	}

	// MÉTODO PARA BUSCAR ANALISTA POR ID
	public Analista buscarAnalistaID(Long id) {
		Optional<Analista> analista = analistaRepository.findById(id);
		return analista
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Analista não encontrado"));

	}

}