package com.gft.veterinariagft.DTOs.cachorro;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.gft.veterinariagft.DTOs.cliente.ConsultaClienteDTO;
import com.gft.veterinariagft.util.Porte;

@SuppressWarnings("serial")
public class ConsultaCachorroDTO  extends RepresentationModel<ConsultaCachorroDTO> implements Serializable{
     
	private Long id;
	private String nome;
	private String raca;
	private String racaDescricao;
	private Double peso;
	private String porte;
	private ConsultaClienteDTO cliente;
	


	public ConsultaCachorroDTO() {
	
	}

	public ConsultaCachorroDTO(Long id, String nome, String raca, String racaDescricao, Double peso, Porte porte,
			ConsultaClienteDTO cliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.porte = porte.getDescricao();
		this.cliente = cliente;
		this.racaDescricao = racaDescricao;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getRaca() {
		return raca;
	}



	public void setRaca(String raca) {
		this.raca = raca;
	}



	public Double getPeso() {
		return peso;
	}



	public void setPeso(Double peso) {
		this.peso = peso;
	}



	public String getPorte() {
		return porte;
	}



	public void setPorte(String porte) {
		this.porte = porte;
	}



	public ConsultaClienteDTO getCliente() {
		return cliente;
	}



	public void setCliente(ConsultaClienteDTO cliente) {
		this.cliente = cliente;
	}

	public String getRacaDescricao() {
		return racaDescricao;
	}

	public void setRacaDescricao(String racaDescricao) {
		this.racaDescricao = racaDescricao;
	}
}
