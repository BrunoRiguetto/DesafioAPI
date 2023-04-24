package com.gft.veterinariagft.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DadosAnimal {
	
	@Column(nullable = true)
	private Double temperatura;

	@Column(nullable = true)
	private String sintomas;
	
	private Double pressao;
	
	public DadosAnimal() {}
	
	public DadosAnimal(Double temperatura, String sintomas, Double pressao) {
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
		DadosAnimal other = (DadosAnimal) obj;
		return Objects.equals(pressao, other.pressao) && Objects.equals(sintomas, other.sintomas)
				&& Objects.equals(temperatura, other.temperatura);
	}
	
	
	
}
