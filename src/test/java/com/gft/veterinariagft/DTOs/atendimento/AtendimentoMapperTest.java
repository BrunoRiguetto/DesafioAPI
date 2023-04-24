package com.gft.veterinariagft.DTOs.atendimento;

import static com.gft.veterinariagft.services.AtendimentoBuilder.umAtendimento;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.gft.veterinariagft.DTOs.atendimento.dadosAnimal.DadosAnimalDTO;
import com.gft.veterinariagft.DTOs.cachorro.ConsultaCachorroDTO;
import com.gft.veterinariagft.DTOs.cachorro.MapperCachorroDTO;
import com.gft.veterinariagft.DTOs.veterinario.ConsultaVeterinarioDTO;
import com.gft.veterinariagft.DTOs.veterinario.VeterinarioMapper;
import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.DadosAnimal;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.util.Porte;

class AtendimentoMapperTest {

	

	@Test
	void testFromEntity() {
		
		Atendimento atendimento = umAtendimento().agora();
		ConsultaAtendimentoDTO consultaAtendimentoDTO = AtendimentoMapper.fromEntity(atendimento);
		
		
		Cliente cliente =new Cliente(1l, "usuario@test.com", "test","test", "000.000.000-00",  "71 00000-0000");
		ConsultaCachorroDTO cachorroDTO = MapperCachorroDTO.fromEntity(new Cachorro(3l, "cachorro", "labrador", 14.5, Porte.GRANDE, cliente)); 
		ConsultaVeterinarioDTO consultaVeterinarioDTO = VeterinarioMapper.fromEntity(new Veterinario(2l, "veterinario@test.com", "test", "test", "12345"));
		DadosAnimalDTO dadosAnimalDTO = DadosAnimalDTO.fromEntity(new DadosAnimal(39.0, "febre", 12.0));
		ConsultaAtendimentoDTO testConsultaAtendimentoDTO = new ConsultaAtendimentoDTO(1l, cachorroDTO, consultaVeterinarioDTO, new Date(),"1 hora" , dadosAnimalDTO, "pneunomia", "beber muita agua");
		
		
		assertEquals(consultaAtendimentoDTO, testConsultaAtendimentoDTO);
	}

	@Test
	void testFromEntitySimplificada() {
		Atendimento atendimento = umAtendimento().agora();
		ConsultaSimplificadaAtendimentoDTO consultaSimplificadaAtendimentoDTO = AtendimentoMapper.fromEntitySimplificada(atendimento);
		
		ConsultaSimplificadaAtendimentoDTO atendimentoDTO = new ConsultaSimplificadaAtendimentoDTO(1l, "cachorro", "test", "000.000.000-00", "test", "12345", new Date());
		
		assertEquals(atendimentoDTO, consultaSimplificadaAtendimentoDTO);
	}

}
