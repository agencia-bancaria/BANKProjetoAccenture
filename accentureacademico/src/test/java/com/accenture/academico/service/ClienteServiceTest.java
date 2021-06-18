package com.accenture.academico.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.repository.ClienteRepository;
@SpringBootTest
class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepostiryMock;
	@Test
	void testsss() {
		assertNotNull(clienteRepostiryMock.findAll());
	}
	
	@Test
	void salvarAgenciaTest() {
		Cliente cliente = new Cliente("asd","70106727419", "2213");
		clienteRepostiryMock.save(cliente);
		assertEquals(1,clienteRepostiryMock.findAll().size());
	}
}
