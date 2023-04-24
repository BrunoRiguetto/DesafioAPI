package com.gft.veterinariagft.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private Date data;
	private String hora;

	private DadosAnimal dadosAnimalnoDia;

	@Column(length = 500)
	private String diagnostico;

	@Column(length = 1000)
	private String comentarios;

	@ManyToOne
	private Cachorro cachorro;

	@ManyToOne
	private Veterinario veterinario;

	@ManyToOne
	private Cliente cliente;

	public Atendimento() {

	}

	public Atendimento(Long id, Date data, String hora, DadosAnimal dadosAnimalnoDia, String diagnostico,
			String comentarios,
			Cachorro cachorro, Veterinario veterinario, Cliente cliente) {
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.dadosAnimalnoDia = dadosAnimalnoDia;
		this.diagnostico = diagnostico;
		this.comentarios = comentarios;
		this.cachorro = cachorro;
		this.veterinario = veterinario;
		this.cliente = cliente;
	}
	
	public Atendimento( Date data, String hora, DadosAnimal dadosAnimalnoDia, String diagnostico,
			String comentarios,
			Cachorro cachorro, Veterinario veterinario, Cliente cliente) {
		this.id = null;
		this.data = data;
		this.hora = hora;
		this.dadosAnimalnoDia = dadosAnimalnoDia;
		this.diagnostico = diagnostico;
		this.comentarios = comentarios;
		this.cachorro = cachorro;
		this.veterinario = veterinario;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public DadosAnimal getDadosAnimalnoDia() {
		return dadosAnimalnoDia;
	}

	public void setDadosAnimalnoDia(DadosAnimal dadosAnimalnoDia) {
		this.dadosAnimalnoDia = dadosAnimalnoDia;
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

	public Cachorro getCachorro() {
		return cachorro;
	}

	public void setCachorro(Cachorro cachorro) {
		this.cachorro = cachorro;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cachorro, cliente, comentarios, dadosAnimalnoDia, data, diagnostico, hora, id, veterinario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		return Objects.equals(cachorro, other.cachorro) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(dadosAnimalnoDia, other.dadosAnimalnoDia) && Objects.equals(data, other.data)
				&& Objects.equals(diagnostico, other.diagnostico) && Objects.equals(hora, other.hora)
				&& Objects.equals(id, other.id) && Objects.equals(veterinario, other.veterinario);
	}

	
}
