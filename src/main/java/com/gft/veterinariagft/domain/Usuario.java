package com.gft.veterinariagft.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gft.veterinariagft.util.TipoPerfil;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
    
	@Column(nullable = true, unique = true)
	private String email;

	@Column(nullable = true)
	private String senha;
	
	private Boolean Enabled;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST) 
	private List<Perfil> perfils = new ArrayList<>();

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(Long id, String email, String senha) {
		setId(id);
		setEmail(email);
		setSenha(senha);
		Enabled = true;
	}
	
	public void adicionaPerfil(TipoPerfil perfil) {
		getPerfils().add( new Perfil(perfil));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		String senhaCrypt= new BCryptPasswordEncoder().encode(senha);
		this.senha = senhaCrypt;
	}
	
	
	public List<Perfil> getPerfils() {
		return perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return Enabled;
	}

	@Override
	public Collection<Perfil> getAuthorities() {
		return perfils;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, perfils);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(perfils, other.perfils);
	}

	public Boolean getEnabled() {
		return Enabled;
	}

	public void setEnabled(Boolean enabled) {
		Enabled = enabled;
	}	
	
}
