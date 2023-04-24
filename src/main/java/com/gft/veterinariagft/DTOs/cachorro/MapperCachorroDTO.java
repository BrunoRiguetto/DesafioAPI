package com.gft.veterinariagft.DTOs.cachorro;

import com.gft.veterinariagft.DTOs.cliente.MapperClienteDTO;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.util.Porte;

public class MapperCachorroDTO {
	
	public static Cachorro fromDTO(RegistroCachorroDTO dto) {
		
		return new Cachorro(null, dto.getNome(), null, dto.getPeso(), Porte.toEnum(dto.getPorte()), null);
	}
	
	public static ConsultaCachorroDTO fromEntity(Cachorro dto) {
		return new ConsultaCachorroDTO(dto.getId(), dto.getNome(), dto.getRaca(), dto.getRacaDescricao(), dto.getPeso(), dto.getPorte(), MapperClienteDTO.fromEntity(dto.getCliente()));
	}
	
	public static ConsultarSimplificadaCachorroDTO fromEntitySimplificada(Cachorro dto) {
		return new ConsultarSimplificadaCachorroDTO(dto.getId(), dto.getNome(), dto.getCliente().getNome(), dto.getCliente().getCpf());
	}

	


}
