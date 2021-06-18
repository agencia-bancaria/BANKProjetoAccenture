package com.accenture.academico.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accenture.academico.model.Cliente;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClienteRepositoryTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@Test
	void test() {
		Cliente cliente = new Cliente("asd","70106727419", "2213");
		clienteRepository.save(cliente);
		assertEquals(1,clienteRepository.findAll().size());
	}

}
