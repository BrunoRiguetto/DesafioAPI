package com.gft.veterinariagft.DTOs.atendimento;

import java.util.Date;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.gft.veterinariagft.DTOs.atendimento.dadosAnimal.DadosAnimalDTO;

public class RegistroAtendimentoDTO {

	@NotBlank(message = "É necessário o id do animal")
	@Positive
	private Long idAnimal;

	@NotBlank(message = "É necessário o id do veterinário")
	@Positive
	private Long idVeterinario;

	@NotNull
	@FutureOrPresent
	private Date data;

	@NotBlank(message = "É necessário uma hora válida")
	private String hora;

	@Valid
	private DadosAnimalDTO dadosAnimalNoDia;

	@NotNull
	@Size(min = 3, max = 500)
	private String diagnostico;

	@Size(max = 1000)
	private String comentarios;

	public RegistroAtendimentoDTO() {

	}

	public RegistroAtendimentoDTO(Long idCliente, Long idAnimal, Long idVeterinario, Date data, String hora,
			DadosAnimalDTO dadosAnimalNoDia, String diagnostico, String comentarios) {
		this.idAnimal = idAnimal;
		this.idVeterinario = idVeterinario;
		this.data = data;
		this.hora = hora;
		this.dadosAnimalNoDia = dadosAnimalNoDia;
		this.diagnostico = diagnostico;
		this.comentarios = comentarios;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public Long getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(Long idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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
		return Objects.hash(comentarios, dadosAnimalNoDia, diagnostico, hora, idAnimal, idVeterinario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroAtendimentoDTO other = (RegistroAtendimentoDTO) obj;
		return Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(dadosAnimalNoDia, other.dadosAnimalNoDia)
				&& Objects.equals(diagnostico, other.diagnostico) && Objects.equals(hora, other.hora)
				&& Objects.equals(idAnimal, other.idAnimal) && Objects.equals(idVeterinario, other.idVeterinario);
	}

	
	
}
