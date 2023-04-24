package com.gft.veterinariagft.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Veterinario  extends Usuario{


	@Column(nullable = true)
    private String nome;

	@Column(nullable = true)
    private String crmv;
    
    @OneToMany(mappedBy = "veterinario")
    List<Atendimento> atendimentos;
    

    public Veterinario() {

    }

    public Veterinario(Long id, String email, String senha, String nome, String crmv) {
    	super(id,email,senha);
		this.nome = nome;
		this.crmv = crmv;
	}
    
    public Veterinario( String email, String senha, String nome, String crmv) {
    	super(null,email,senha);
		this.nome = nome;
		this.crmv = crmv;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrmv() {
		return crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
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
		result = prime * result + Objects.hash(atendimentos, crmv, nome);
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
		Veterinario other = (Veterinario) obj;
		return Objects.equals(atendimentos, other.atendimentos) && Objects.equals(crmv, other.crmv)
				&& Objects.equals(nome, other.nome);
	}
	
	

}

