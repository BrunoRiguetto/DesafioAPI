package com.gft.veterinariagft.DTOs.atendimento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

@SuppressWarnings("serial")
public class ConsultaSimplificadaAtendimentoDTO extends RepresentationModel<ConsultaSimplificadaAtendimentoDTO> implements Serializable{

	private Long id;
	private String nomeCachorro;
	private String nomeCliente;
	private String cpfCliente;
	private String nomeVeterinario;
	private String crmv;
	private String data;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public ConsultaSimplificadaAtendimentoDTO() {}

	public ConsultaSimplificadaAtendimentoDTO(Long id, String nomeCachorro, String nomeCliente, String cpfCliente,
			String nomeVeterinario, String crmv, Date data) {
		super();
		this.id = id;
		this.nomeCachorro = nomeCachorro;
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
		this.nomeVeterinario = nomeVeterinario;
		this.crmv = crmv;
		this.data = sdf.format(data);
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

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNomeVeterinario() {
		return nomeVeterinario;
	}

	public void setNomeVeterinario(String nomeVeterinario) {
		this.nomeVeterinario = nomeVeterinario;
	}

	public String getCrmv() {
		return crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cpfCliente, crmv, id, nomeCachorro, nomeCliente, nomeVeterinario);
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
		ConsultaSimplificadaAtendimentoDTO other = (ConsultaSimplificadaAtendimentoDTO) obj;
		return Objects.equals(cpfCliente, other.cpfCliente) && Objects.equals(crmv, other.crmv)
				&& Objects.equals(id, other.id) && Objects.equals(nomeCachorro, other.nomeCachorro)
				&& Objects.equals(nomeCliente, other.nomeCliente)
				&& Objects.equals(nomeVeterinario, other.nomeVeterinario);
	}


	
}
