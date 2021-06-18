package com.accenture.academico.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.academico.model.Operacao;
import com.accenture.academico.repository.OperacaoRepository;
@SpringBootTest
class OperacaoServiceTest {

	@Mock
	private OperacaoRepository operacaoRepostiryMock;
	@Test
	void testsss() {
		assertNotNull(operacaoRepostiryMock.findAll());
	}
	
	@Test
	void salvarAgenciaTest() {
		Operacao operacao = new Operacao();
		operacaoRepostiryMock.save(operacao);
		assertEquals(1,operacaoRepostiryMock.findAll().size());
	}

}