package com.gft.veterinariagft.DTOs.cliente;

import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.Perfil;
import com.gft.veterinariagft.util.TipoPerfil;

public class MapperClienteDTO {

	public static Cliente fromDTO(RegistroClienteDTO dto) {
		Cliente cliente = new Cliente( dto.getEmail(), dto.getSenha(), dto.getNome(), dto.getCpf(), dto.getTelefone());
		Perfil perfil = new Perfil(TipoPerfil.ROLE_CLIENTE);
		cliente.getPerfils().add(perfil);
		return cliente;			
				
	}
	
	public static ConsultaClienteDTO fromEntity(Cliente cliente) {
		
		return new ConsultaClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail());
	}
	
	public static ConsultaSimplificadaClienteDTO fromEntitySimplificada(Cliente cliente) {
		
		return new ConsultaSimplificadaClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf());
	}

}
