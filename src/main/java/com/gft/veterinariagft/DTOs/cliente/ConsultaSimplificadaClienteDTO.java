package com.gft.veterinariagft.DTOs.cliente;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

@SuppressWarnings("serial")
public class ConsultaSimplificadaClienteDTO  extends RepresentationModel<ConsultaSimplificadaClienteDTO> implements Serializable {
	
	private Long key;
	private String nome;
	private String cpf;
	
	public ConsultaSimplificadaClienteDTO() {}	
	
	public ConsultaSimplificadaClienteDTO(Long key, String nome, String cpf) {
		super();
		this.key = key;
		this.nome = nome;
		this.cpf = cpf;
	}


	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cpf, key, nome);
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
		ConsultaSimplificadaClienteDTO other = (ConsultaSimplificadaClienteDTO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(key, other.key) && Objects.equals(nome, other.nome);
	}
	
	
	
}
