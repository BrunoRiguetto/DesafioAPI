package com.gft.veterinariagft.DTOs.atendimento;

import com.gft.veterinariagft.DTOs.atendimento.dadosAnimal.DadosAnimalDTO;
import com.gft.veterinariagft.DTOs.cachorro.MapperCachorroDTO;
import com.gft.veterinariagft.DTOs.veterinario.VeterinarioMapper;
import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.Veterinario;

public class AtendimentoMapper {
	public static Atendimento fromDTO(RegistroAtendimentoDTO dto, Cliente cliente, Veterinario veterinario, Cachorro cachorro) {
		return new Atendimento(null, dto.getData(), dto.getHora(),
				DadosAnimalDTO.fromDTO(dto.getDadosAnimalNoDia()), dto.getDiagnostico(),
				dto.getComentarios(), cachorro, veterinario, cliente);
	}
	
	public static ConsultaAtendimentoDTO fromEntity(Atendimento atendimento) {
		return new ConsultaAtendimentoDTO(atendimento.getId(),
				MapperCachorroDTO.fromEntity(atendimento.getCachorro()),
				VeterinarioMapper.fromEntity(atendimento.getVeterinario()),
				atendimento.getData(), atendimento.getHora(),DadosAnimalDTO.fromEntity(atendimento.getDadosAnimalnoDia()) ,
				atendimento.getDiagnostico(), atendimento.getComentarios());
	}
	
	public static ConsultaSimplificadaAtendimentoDTO fromEntitySimplificada(Atendimento atendimento) {
		return new ConsultaSimplificadaAtendimentoDTO(atendimento.getId(), atendimento.getCachorro().getNome(),
				atendimento.getCachorro().getCliente().getNome(),
				atendimento.getCachorro().getCliente().getCpf(),
				atendimento.getVeterinario().getNome(), atendimento.getVeterinario().getCrmv(),
				atendimento.getData());
	}
}
