package com.gft.veterinariagft.DTOs.atendimento.dadosAnimal;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gft.veterinariagft.domain.DadosAnimal;

public class DadosAnimalDTO {
    
	@Min(value = 0)
	private Double temperatura;

	@NotNull
	private String sintomas;
	private Double pressao;
	
	public DadosAnimalDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DadosAnimalDTO(Double temperatura, String sintomas, Double pressao) {
		super();
		this.temperatura = temperatura;
		this.sintomas = sintomas;
		this.pressao = pressao;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	public String getSintomas() {
		return sintomas;
	}
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	public Double getPressao() {
		return pressao;
	}
	public void setPressao(Double pressao) {
		this.pressao = pressao;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(pressao, sintomas, temperatura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosAnimalDTO other = (DadosAnimalDTO) obj;
		return Objects.equals(pressao, other.pressao) && Objects.equals(sintomas, other.sintomas)
				&& Objects.equals(temperatura, other.temperatura);
	}

	
	public static DadosAnimal fromDTO(DadosAnimalDTO dto) {
		return new DadosAnimal(dto.getTemperatura(), dto.getSintomas(), dto.getPressao());
	}
	
	public static DadosAnimalDTO fromEntity(DadosAnimal animal) {
		return new DadosAnimalDTO(animal.getTemperatura(), animal.getSintomas(), animal.getPressao());
	}
}
