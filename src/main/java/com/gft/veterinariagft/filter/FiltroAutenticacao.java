package com.gft.veterinariagft.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gft.veterinariagft.domain.Usuario;
import com.gft.veterinariagft.services.AutenticacaoService;
import com.gft.veterinariagft.services.UsuarioService;

public class FiltroAutenticacao extends OncePerRequestFilter{
	
	private AutenticacaoService autenticacaoService;
	private UsuarioService usuarioservice;

	public FiltroAutenticacao( AutenticacaoService autenticacaoService, UsuarioService usuarioservice) {
		this.autenticacaoService = autenticacaoService;
		this.usuarioservice = usuarioservice;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
		String header = request.getHeader("Authorization");
		String token = null;
		
		if(header != null && header.startsWith("Bearer ")) 
				token = header.substring(7, header.length());
		
		if(autenticacaoService.verificaToken(token)) {
			Long id = autenticacaoService.retornaIDUsuario(token);
			Usuario usuario = usuarioservice.BuscaUsuarioPorId(id);
			if(usuario.isEnabled()) {
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities()));
			}
		
		}
	
		filterChain.doFilter(request, response);
		
	}
	

}
