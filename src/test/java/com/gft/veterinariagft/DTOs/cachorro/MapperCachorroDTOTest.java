package com.gft.veterinariagft.DTOs.cachorro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gft.veterinariagft.DTOs.cliente.MapperClienteDTO;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.util.Porte;

class MapperCachorroDTOTest {
	
	@Test
	void testFromDTO() {
		RegistroCachorroDTO cachorroDTO = new RegistroCachorroDTO("cachorro", "pitbull", 12.0, 1, null);
		Cachorro testCachorro = MapperCachorroDTO.fromDTO(cachorroDTO);
		
		Cachorro cachorro = new Cachorro("cachorro", null, 12.0, Porte.toEnum(1), null);
		assertEquals(cachorro,testCachorro);
	}
	
	@Test
	void testFromDTODeveRetornaUmErroSerNaoExistirPorteDoCachorro() {
		RegistroCachorroDTO cachorroDTO = new RegistroCachorroDTO("cachorro", "pitbull", 12.0, 0, null);
		
		Throwable exception = assertThrows(IllegalArgumentException.class,
				() ->  MapperCachorroDTO.fromDTO(cachorroDTO) );
		
		assertEquals("Id inválido: "+cachorroDTO.getPorte(),exception.getMessage());
	}
	
	@Test 
	void testFromEntity() { 
		Cachorro cachorro = new Cachorro(1l,"cachorro", "pitbull", 12.0, Porte.toEnum(1), new Cliente());
		ConsultaCachorroDTO cachorroDTO = MapperCachorroDTO.fromEntity(cachorro);
		
		ConsultaCachorroDTO tesConsultaCachorroDTO = new ConsultaCachorroDTO(1l, "cachorro", "pitbull", null, 12.0, Porte.toEnum(1), MapperClienteDTO.fromEntity(new Cliente()));
		
		assertEquals(tesConsultaCachorroDTO, cachorroDTO);
	}
	
	@Test 
	void testFromEntitySimplificada() { 
		Cachorro cachorro = new Cachorro(1l,"cachorro", "pitbull", 12.0, Porte.toEnum(1), new Cliente("usuario@test.com","12345" ,"test" ,"000.000.000-00", "71 00000 - 0000"));
		ConsultarSimplificadaCachorroDTO cachorroDTO = MapperCachorroDTO.fromEntitySimplificada(cachorro);
		
		ConsultarSimplificadaCachorroDTO consultarSimplificadaCachorroDTO = new ConsultarSimplificadaCachorroDTO(1l, "cachorro", "test", "000.000.000-00");
		
		assertEquals(consultarSimplificadaCachorroDTO, cachorroDTO);
		
		
	}
	 

}
