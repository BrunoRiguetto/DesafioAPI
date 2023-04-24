package com.gft.veterinariagft.DTOs.veterinario;

import java.util.Objects;

public class ConsultaVeterinarioDTO {
	
    private Long id;
    private String nome;
    private String crmv;

    // estanciar alguma classe

    public ConsultaVeterinarioDTO() {

    }

    public ConsultaVeterinarioDTO(Long id, String nome, String crmv) {
        super();
        this.id = id;
        this.nome = nome;
        this.crmv = crmv;
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

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

	@Override
	public int hashCode() {
		return Objects.hash(crmv, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaVeterinarioDTO other = (ConsultaVeterinarioDTO) obj;
		return Objects.equals(crmv, other.crmv) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
    
    
}
