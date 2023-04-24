package com.gft.veterinariagft.DTOs.cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistroClienteDTO {
	
	@Email(message = "Favor preencher com email valido.")
	@NotBlank(message = "Email obrigatório")
	private String email;

	@NotBlank(message = "Insira sua senha")
	@Size(min = 6, max = 20, message = "Deve ter entre 6 e 20 caracteres")
	private String senha;

	@NotBlank(message = "Preencha com o nome completo")
	private String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	@Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}$",message = "é necessario o padrão 000.000.000-00")
	private String cpf;

	@NotBlank(message = "Telefone é obrigatório")
	@Pattern(regexp="\\d{2} \\d{5}-\\d{4}$",message = "é necessario o padrão 00 00000-0000")
	private String telefone;
	
	public RegistroClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	


	public RegistroClienteDTO(
			@Email(message = "Favor preencher com email valido.") @NotBlank(message = "Email obrigatório") String email,
			@NotBlank(message = "Insira sua senha") @Size(min = 6, max = 20, message = "Deve ter entre 6 e 20 caracteres") String senha,
			@NotBlank(message = "Preencha com o nome completo") String nome,
			@NotBlank(message = "CPF é obrigatório") @Pattern(regexp = "\\d{3}.\\d{3}.\\d{3}-\\d{2}$", message = "é necessario o padrão 000.000.000-00") String cpf,
			@NotBlank(message = "Telefone é obrigatório") @Pattern(regexp = "\\d{2} \\d{5}-\\d{4}$", message = "é necessario o padrão 00 00000-0000") String telefone) {
		this.email = email;
		this.senha = senha;
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
