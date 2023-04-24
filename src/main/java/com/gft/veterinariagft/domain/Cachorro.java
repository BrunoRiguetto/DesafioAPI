package com.gft.veterinariagft.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.gft.veterinariagft.domain.dogapi.Breed;
import com.gft.veterinariagft.util.Porte;
import com.gft.veterinariagft.util.Tradutor;

@Entity
public class Cachorro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private String nome;
	private String raca;
	private Double peso;
	@Column(nullable = true)
	private Integer porte;

	@Column(length = 1000)
	private String racaDescricao;

	private String imageUrl;

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "cachorro")
	private List<Atendimento> atendimentos;

	public Cachorro(Long id, String nome, String raca, Double peso, Porte porte, Cliente cliente) {
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.porte = (porte == null) ? null : porte.getCodigo();
		this.cliente = cliente;
	}

	public Cachorro(String nome, String raca, Double peso, Porte porte, Cliente cliente) {
		this.id = null;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.porte = (porte == null) ? null : porte.getCodigo();
		this.cliente = cliente;
	}

	public Cachorro(Long id, String nome, String raca, Double peso, Integer porte, String racaDescricao,
			String wikipediaRaca, Cliente cliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.porte = porte;
		this.racaDescricao = racaDescricao;
		this.cliente = cliente;

	}

	public Cachorro() {

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

	public Porte getPorte() {
		return Porte.toEnum(porte);
	}

	public void setPorte(Porte porte) {
		this.porte = porte.getCodigo();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPorte(Integer porte) {
		this.porte = porte;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public String getRacaDescricao() {
		return racaDescricao;
	}

	public void setRacaDescricao(String racaDescricao) {
		this.racaDescricao = racaDescricao;
	}

	public String getImageRaca() {
		return imageUrl;
	}

	public void setImageRaca(String imageRaca) {
		this.imageUrl = imageRaca;
	}

	public void buildRacaDescricao(Breed raca) throws IOException {
		StringBuilder sb = new StringBuilder();

		sb.append("Bred for " + raca.getBred_for() + ". The " + raca.getName() + " is ");
		sb.append("originated from " + raca.getOrigin() + ", ");
		sb.append("they can be very: " + raca.getTemperament() + ". ");

		String traducao = Tradutor.translate("en", "pt", sb.toString());

		setRacaDescricao(traducao);

		if (raca.getImage() != null) {
			setImageRaca(raca.getImage().getUrl());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(atendimentos, cliente, id, nome, peso, porte, raca, racaDescricao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cachorro other = (Cachorro) obj;
		return Objects.equals(atendimentos, other.atendimentos) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(peso, other.peso)
				&& Objects.equals(porte, other.porte) && Objects.equals(raca, other.raca)
				&& Objects.equals(racaDescricao, other.racaDescricao);
	}

}
