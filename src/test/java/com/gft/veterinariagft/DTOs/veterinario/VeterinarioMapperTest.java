package com.gft.veterinariagft.DTOs.veterinario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.gft.veterinariagft.domain.Veterinario;

class VeterinarioMapperTest {

	@Test
	void testFromDTO() {
		RegistroVeterinarioDTO registroVeterinarioDTO = new RegistroVeterinarioDTO("veterinario@gft.com", "12345", "veterinario", "12345");
		Veterinario veterinario = VeterinarioMapper.fromDTO(registroVeterinarioDTO);
		
		Veterinario testVeterinario = new Veterinario("veterinario@gft.com", "12345", "veterinario", "12345");

		assertEquals(testVeterinario.getEmail(), veterinario.getEmail());
		assertNotEquals(testVeterinario.getPassword(), veterinario.getPassword());
		assertEquals(testVeterinario.getNome(), veterinario.getNome());
		assertEquals(testVeterinario.getCrmv(), veterinario.getCrmv());
	}

	@Test
	void testFromEntity() {
		Veterinario veterinario = new Veterinario(1l,"veterinario@gft.com", "12345", "veterinario", "12345");
		ConsultaVeterinarioDTO consultaVeterinarioDTO = VeterinarioMapper.fromEntity(veterinario);
		
		ConsultaVeterinarioDTO dto = new ConsultaVeterinarioDTO(1l, "veterinario", "12345");
		
		assertEquals(dto, consultaVeterinarioDTO);
	}

}
