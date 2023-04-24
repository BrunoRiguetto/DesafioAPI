package com.gft.veterinariagft.DTOs.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.Perfil;
import com.gft.veterinariagft.util.TipoPerfil;

class MapperClienteDTOTest {

	@Test
	void testFromDTO() {
		RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO("usuario@test.com", "12345","Cliente" ,"000.000.000-00", "71 00000-0000");
		Cliente testCliente = MapperClienteDTO.fromDTO(registroClienteDTO);
		
		Cliente cliente = new Cliente("usuario@test.com", "12345","Cliente" ,"000.000.000-00", "71 00000-0000");
		cliente.getPerfils().add(new Perfil(TipoPerfil.ROLE_CLIENTE));
		assertEquals(cliente.getEmail(), testCliente.getEmail());
		assertEquals(cliente.getCpf(), testCliente.getCpf());
	}
	
	@Test
	void testFromEntity() {
		Cliente cliente = new Cliente(1l,"usuario@test.com", "123456","cliente" ,"000.000.000-00", "71 00000-0000");
		ConsultaClienteDTO consultaClienteDTO = MapperClienteDTO.fromEntity(cliente);
		
		ConsultaClienteDTO testConsultaClienteDTO = new ConsultaClienteDTO(1l, "cliente","000.000.000-00" ,"71 00000-0000", "usuario@test.com");
		Perfil perfil = new Perfil(TipoPerfil.ROLE_CLIENTE);
		cliente.getPerfils().add(perfil);
		
		assertEquals(testConsultaClienteDTO, consultaClienteDTO);;
	}

	@Test
	void testFromEntitySimplificada() {
		Cliente cliente = new Cliente(1l,"usuario@test.com", "123456","cliente" ,"000.000.000-00", "71 00000-0000");
		ConsultaSimplificadaClienteDTO consultaSimplificadaClienteDTO = MapperClienteDTO.fromEntitySimplificada(cliente);
		
		ConsultaSimplificadaClienteDTO clienteDTO = new ConsultaSimplificadaClienteDTO(1l, "cliente","000.000.000-00");
		
		assertEquals(clienteDTO, consultaSimplificadaClienteDTO);
	}

}
