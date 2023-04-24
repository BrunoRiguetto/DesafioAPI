package com.gft.veterinariagft.DTOs.atendimento.dadosAnimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gft.veterinariagft.domain.DadosAnimal;


class DadosAnimalDTOTest {

	@Test
	void testFromDTO() {
		DadosAnimalDTO dadosAnimalDTO = new DadosAnimalDTO(38.5, "febre", 12.8);
		
		DadosAnimal testAnimal = DadosAnimalDTO.fromDTO(dadosAnimalDTO);
		DadosAnimal animal = new DadosAnimal(38.5, "febre", 12.8);
		
		assertEquals(testAnimal, animal);
	}

	@Test
	void testFromEntity() {
		DadosAnimal dadosAnimal = new DadosAnimal(38.5, "febre", 12.8);
		
		DadosAnimalDTO dadosAnimalDTO = new DadosAnimalDTO(38.5, "febre", 12.8);
		DadosAnimalDTO testAnimalDTO = DadosAnimalDTO.fromEntity(dadosAnimal);
		
		assertEquals(testAnimalDTO, dadosAnimalDTO);
	}


}
