package com.accenture.academico.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.academico.model.Analista;
import com.accenture.academico.repository.AnalistaRepository;
@SpringBootTest
class AnalistaServiceTest {

	@Mock
	private AnalistaRepository analistaRepostiryMock;
	@Test
	void testsss() {
		assertNotNull(analistaRepostiryMock.findAll());
	}
	
	@Test
	void salvarAgenciaTest() {
		Analista analista = new Analista();
		analistaRepostiryMock.save(analista);
		assertEquals(1,analistaRepostiryMock.findAll().size());
	}

}
