package com.gft.veterinariagft.services;

import java.util.Date;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gft.veterinariagft.DTOs.token.AutenticacaoForm;
import com.gft.veterinariagft.DTOs.token.TokenDTO;
import com.gft.veterinariagft.domain.Usuario;

@Service
public class AutenticacaoService {
	
	
	private String secret = "Ffbsz5ISjlfW0ZU1sZG4nEBkFVHCYubm";
	
	
	private String expiration ="60000000";
	
	private String issuer = "programa start";

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public TokenDTO autentica( AutenticacaoForm authForm) throws AuthenticationException{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getEmail(), authForm.getSenha()));
		String token = geraToken(authentication);
		return new TokenDTO(token);
	}
	private Algorithm criarAlgoritimo() {
		return Algorithm.HMAC256(secret);
	}

	private String geraToken(Authentication authentication) {
		Usuario usuario = (Usuario)authentication.getPrincipal();
		Date hoje = new Date();
		Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return JWT.create()
				.withIssuer(issuer)
				.withIssuedAt(hoje)
				.withExpiresAt(expiracao)
				.withSubject(usuario.getId().toString())
				.sign(criarAlgoritimo());
	}
	
	public boolean verificaToken(String token) {

		if(token == null)
			return false;
		
		try {
			JWT.require(this.criarAlgoritimo()).withIssuer(issuer).build().verify(token);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}
		
	}
	
	public Long retornaIDUsuario(String token) {
		String object = JWT.require(this.criarAlgoritimo()).withIssuer(issuer).build().verify(token).getSubject();
		return Long.parseLong(object);
	}

}