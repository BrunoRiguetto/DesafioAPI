package com.gft.veterinariagft.DTOs.veterinario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegistroVeterinarioDTO {
	
	@Email
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
    private String nome;
	@NotBlank(message = "crmv é obrigatório")
	@Pattern(regexp="\\d{5}$",message = "é necessario o padrao 00000")
    private String crmv;

    //alguma classe

    public RegistroVeterinarioDTO() {}

    public RegistroVeterinarioDTO(@Email String email, @NotBlank String senha, @NotBlank String nome,
			@NotBlank(message = "crmv é obrigatório") @Pattern(regexp = "\\d{5}$", message = "é necessario o padrao 00000") String crmv) {
		super();
		this.email = email;
		this.senha = senha;
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
