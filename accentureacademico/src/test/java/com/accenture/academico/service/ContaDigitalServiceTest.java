package com.accenture.academico.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.academico.model.ContaDigital;
import com.accenture.academico.repository.ContaDigitalRepository;
@SpringBootTest
class ContaDigitalServiceTest {

	@Mock
	private ContaDigitalRepository contaRepostiryMock;
	@Test
	void testsss() {
		assertNotNull(contaRepostiryMock.findAll());
	}
	
	@Test
	void salvarAgenciaTest() {
		ContaDigital conta = new ContaDigital();
		contaRepostiryMock.save(conta);
		assertEquals(1,contaRepostiryMock.findAll().size());
	}

}
