package com.accenture.academico.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accenture.academico.model.Agencia;
import com.accenture.academico.repository.AgenciaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AgenciaServiceTest {

	
	
	
	@Mock
	private AgenciaRepository agenciaRepostiryMock;
		@Test
	void testsss() {
		assertNotNull(agenciaRepostiryMock.findAll());
	}
	@Test
	void salvarAgenciaTest() {
		Agencia agencia = new Agencia(1,"agenciaTest", "1111", "12344321", LocalDate.now());

		agenciaRepostiryMock.save(agencia);
		assertEquals(1,agenciaRepostiryMock.findAll().size());
	}

}
