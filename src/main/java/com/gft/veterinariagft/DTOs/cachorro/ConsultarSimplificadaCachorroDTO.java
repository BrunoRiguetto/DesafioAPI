package com.gft.veterinariagft.DTOs.cachorro;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

@SuppressWarnings("serial")
public class ConsultarSimplificadaCachorroDTO extends RepresentationModel<ConsultarSimplificadaCachorroDTO> implements Serializable{
	private Long id;
	private String nomeCachorro;
	private String nomeCliente;
	private String cpf;
	
	public ConsultarSimplificadaCachorroDTO() {
		// TODO Auto-generated constructor stub
	}	

	public ConsultarSimplificadaCachorroDTO(Long id, String nomeCachorro, String nomeCliente, String cpf) {
		super();
		this.id = id;
		this.nomeCachorro = nomeCachorro;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCachorro() {
		return nomeCachorro;
	}

	public void setNomeCachorro(String nomeCachorro) {
		this.nomeCachorro = nomeCachorro;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
		result = prime * result + Objects.hash(cpf, id, nomeCachorro, nomeCliente);
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
		ConsultarSimplificadaCachorroDTO other = (ConsultarSimplificadaCachorroDTO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id)
				&& Objects.equals(nomeCachorro, other.nomeCachorro) && Objects.equals(nomeCliente, other.nomeCliente);
	}
	
	
}
