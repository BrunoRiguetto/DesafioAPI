package com.gft.veterinariagft.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Cliente extends Usuario{

	@Column(nullable = true)
	private String nome;

	@Column(nullable = true)
	private String cpf;
	
	private String telefone;
	
	@OneToMany(mappedBy = "cliente")
	private List<Atendimento> atendimentos;
	
	@OneToMany(mappedBy = "cliente")
	private List<Cachorro> cachorros;
		
	public Cliente() {
	}
		
	public Cliente(Long id, String email, String senha, String nome, String cpf, String telefone) {
		super(id,email,senha);
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public Cliente( String email, String senha, String nome, String cpf, String telefone) {
		super(null,email,senha);
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Cachorro> getCachorros() {
		return cachorros;
	}

	public void setCachorros(List<Cachorro> cachorros) {
		this.cachorros = cachorros;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atendimentos, cachorros, cpf, nome, telefone);
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
		Cliente other = (Cliente) obj;
		return Objects.equals(atendimentos, other.atendimentos) && Objects.equals(cachorros, other.cachorros)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}
	
}
