package com.gft.veterinariagft.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.veterinariagft.domain.Usuario;
import com.gft.veterinariagft.repositories.UsuarioRepository;
@Service
public class UsuarioService  implements UserDetailsService{

	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
	
		return usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Dados inválido"));
	}
	@Transactional
	public Usuario BuscaUsuarioPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new UsernameNotFoundException("Dados inválido"));
	}
	
	public void salva(Usuario salvaUsuario){
		usuarioRepository.save(salvaUsuario);
	}

}
