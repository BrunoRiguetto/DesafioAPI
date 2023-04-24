package com.gft.veterinariagft.DTOs.cachorro;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class RegistroCachorroDTO {
	
	@NotNull
	@Size(min = 3, max = 20)
	private String nome;

	@NotNull
	@Positive
	private int racaId;

	@NotNull
	@DecimalMax(value = "200.0", inclusive = true)
	private Double peso;

	@Min(1)@Max(3)
	private Integer porte;

	@NotNull
	@Positive
	private Long clienteId;

	private String raca;
	
	
	public RegistroCachorroDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RegistroCachorroDTO(@NotNull @Size(min = 3, max = 20) String nome, String raca,
			@NotNull @DecimalMax(value = "200.0", inclusive = true) Double peso, @Min(1) @Max(3) Integer porte,
			@NotNull @Positive Long clienteId) {
		super();
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.porte = porte;
		this.clienteId = clienteId;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getRacaId() {
		return racaId;
	}
	public void setRacaId(int racaId) {
		this.racaId = racaId;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Integer getPorte() {
		return porte;
	}
	public void setPorte(Integer porte) {
		this.porte = porte;
	}


	public String getRaca() {
		return raca;
	}


	public void setRaca(String raca) {
		this.raca = raca;
	}
	
}
