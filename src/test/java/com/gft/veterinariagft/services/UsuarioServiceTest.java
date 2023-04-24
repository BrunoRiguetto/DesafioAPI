package com.gft.veterinariagft.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gft.veterinariagft.domain.Usuario;
import com.gft.veterinariagft.repositories.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	private UsuarioService usuarioService;
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
    public void setUp() {
		Mockito.mock(usuarioRepository.getClass());
		usuarioService = new UsuarioService(usuarioRepository);
    }
	
	@Test
	public void TestLoadUserByUsername() {
		Optional<Usuario> usuario = Optional.ofNullable(new Usuario(1l, "test@gmail.com", "12345"));
		when(usuarioRepository.findByEmail("test@gmail.com")).thenReturn(usuario);
		
		UserDetails usuarioDetails = usuarioService.loadUserByUsername("test@gmail.com");
		
		//verify
		verify(usuarioRepository).findByEmail("test@gmail.com");
		assertEquals(usuarioDetails, usuario.get() );
	}
	
	@Test
	public void TestLoadUserByUsernameRetornaraUmErrorSeNaoExistirID() {
		Optional<Usuario> usuario = Optional.empty() ;
		when(usuarioRepository.findByEmail("test@gmail.com")).thenReturn(usuario);
		
		Throwable exception = assertThrows(UsernameNotFoundException.class,
				() -> usuarioService.loadUserByUsername("test@gmail.com"));
		//verify	
		verify(usuarioRepository).findByEmail("test@gmail.com");
		assertEquals(exception.getMessage(), "Dados inválido");		
	}
		
	@Test
	public void TestBuscaUsuarioPorId() {
		Optional<Usuario> usuario = Optional.ofNullable(new Usuario(1l, "test@gmail.com", "12345"));
		when(usuarioRepository.findById(1L)).thenReturn(usuario);
		
		Usuario usuarioTest = usuarioService.BuscaUsuarioPorId(1L);
		
		//verify
		verify(usuarioRepository).findById(1L);
		assertEquals(usuarioTest, usuario.get() );
	}
	
	@Test
	public void TestBuscaUsuarioPorIdRetornaraUmErrorSeNaoExistirID() {
		Optional<Usuario> usuario = Optional.empty();
		when(usuarioRepository.findById(1L)).thenReturn(usuario);
		
		Throwable exception = assertThrows(UsernameNotFoundException.class,
				() ->usuarioService.BuscaUsuarioPorId(1L) );
		
		//verify
		verify(usuarioRepository).findById(1L);
		assertEquals(exception.getMessage(), "Dados inválido");	
	}
	
	@Test
	public void TestSalvaUsuario() {
		Usuario usuario = new Usuario(1l, "test@gmail.com", "12345");
		when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		usuarioService.salva(new Usuario( 2L,"test@gmail.com", "12345"));
		
		//verify
		verify(usuarioRepository).save(Mockito.any(Usuario.class));
	}
}
