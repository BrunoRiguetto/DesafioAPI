package com.gft.veterinariagft.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.gft.veterinariagft.util.TipoPerfil;

@Entity
public class Perfil implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoPerfil tipoPerfil;
	

	public Perfil(TipoPerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}
	
	public Perfil() {
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public TipoPerfil getTipoPerfil() {
		return tipoPerfil;
	}


	public void setTipoPerfil(TipoPerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}


	@Override
	public String getAuthority() {
		return String.valueOf(tipoPerfil.toString()) ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
