package com.gft.veterinariagft.DTOs.atendimento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.gft.veterinariagft.DTOs.atendimento.dadosAnimal.DadosAnimalDTO;
import com.gft.veterinariagft.DTOs.cachorro.ConsultaCachorroDTO;
import com.gft.veterinariagft.DTOs.veterinario.ConsultaVeterinarioDTO;

@SuppressWarnings("serial")
public class ConsultaAtendimentoDTO extends RepresentationModel<ConsultaAtendimentoDTO> implements Serializable {
	
	private Long id;
	private ConsultaCachorroDTO cachorro;
	private ConsultaVeterinarioDTO veterinario;
	private String data;
	private String hora;
	private DadosAnimalDTO dadosAnimalNoDia;
	private String diagnostico;
	private String comentarios;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public ConsultaAtendimentoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ConsultaAtendimentoDTO(Long id, ConsultaCachorroDTO cachorro,
			ConsultaVeterinarioDTO veterinario, Date data, String hora, DadosAnimalDTO dadosAnimalNoDia,
			String diagnostico, String comentarios) {
		this.id = id;
		this.cachorro = cachorro;
		this.veterinario = veterinario;
		this.data = sdf.format(data);
		this.hora = hora;
		this.dadosAnimalNoDia = dadosAnimalNoDia;
		this.diagnostico = diagnostico;
		this.comentarios = comentarios;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConsultaCachorroDTO getCachorro() {
		return cachorro;
	}

	public void setCachorro(ConsultaCachorroDTO cachorro) {
		this.cachorro = cachorro;
	}

	public ConsultaVeterinarioDTO getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(ConsultaVeterinarioDTO veterinario) {
		this.veterinario = veterinario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public DadosAnimalDTO getDadosAnimalNoDia() {
		return dadosAnimalNoDia;
	}

	public void setDadosAnimalNoDia(DadosAnimalDTO dadosAnimalNoDia) {
		this.dadosAnimalNoDia = dadosAnimalNoDia;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(cachorro, comentarios, dadosAnimalNoDia, diagnostico, hora, id, veterinario);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaAtendimentoDTO other = (ConsultaAtendimentoDTO) obj;
		return Objects.equals(cachorro, other.cachorro) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(dadosAnimalNoDia, other.dadosAnimalNoDia)
				&& Objects.equals(diagnostico, other.diagnostico) && Objects.equals(hora, other.hora)
				&& Objects.equals(id, other.id) && Objects.equals(veterinario, other.veterinario);
	}

	
}
