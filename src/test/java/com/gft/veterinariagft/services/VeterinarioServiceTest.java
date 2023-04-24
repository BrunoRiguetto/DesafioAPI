package com.gft.veterinariagft.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.VeterinarioRepository;

@ExtendWith(MockitoExtension.class)
class VeterinarioServiceTest {
	
	
	private VeterinarioService veterinarioService;
	
	@Mock
	private VeterinarioRepository veterinarioRepository;
	
	@BeforeEach
    public void setUp() {
		Mockito.mock(veterinarioRepository.getClass());
		veterinarioService = new VeterinarioService(veterinarioRepository);
    }

	@Test
	void testSalvar() {
		
		Veterinario veterinarioTest = new Veterinario("test@gft.com", "12345", "test", "12345");
		
		when(veterinarioRepository.save(Mockito.any(Veterinario.class))).thenReturn(veterinarioTest);
				
		Veterinario veterinario = veterinarioService.salvar(veterinarioTest);
		
		// verify
        assertEquals(veterinario, veterinarioTest);
        verify(veterinarioRepository).save(veterinarioTest);
        
	}
	
	@Test
	void testBuscaID() {
		Optional<Veterinario> veterinarioTest = Optional.ofNullable(new Veterinario(1l,"test@gft.com", "12345", "test", "12345"));
		
		when(veterinarioRepository.findById(1l)).thenReturn(veterinarioTest);
		
		Veterinario veterinario = veterinarioService.buscaID(1l);
		
		//verify
		verify(veterinarioRepository).findById(1l);
        assertEquals(veterinario, veterinarioTest.get());

	}
	
	@Test
	void testBuscaIDRetornaraUmErrorSeNaoExistirID() {		
		Optional<Veterinario> page = Optional.empty() ;
		when(veterinarioRepository.findById(1l)).thenReturn(page);
		
		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> veterinarioService.buscaID(1l) );
				
		//verify
		verify(veterinarioRepository).findById(1l);
        assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 1");

	}
	
	@Test
	void testBuscaTodosOsVeterinario() {
		
		Pageable page = null;
		
		
		List<Veterinario> list = Arrays.asList(
				new Veterinario(1l,"test1@gft.com", "12345", "test", "12345"),
				new Veterinario(2l,"test2@gft.com", "12345", "test", "12345"),
				new Veterinario(3l,"test3@gft.com", "12345", "test", "12345"),
				new Veterinario(4l,"test4@gft.com", "12345", "test", "12345"),
				new Veterinario(5l,"test5@gft.com", "12345", "test", "12345"),
				new Veterinario(6l,"test6@gft.com", "12345", "test", "12345"));
		
		Page<Veterinario> pageList = new PageImpl<>(list);
		
		when(veterinarioRepository.findAll(page)).thenReturn(pageList);
		
		Page<Veterinario> testPage = veterinarioService.listaTodos(page);
		
		//verify
		verify(veterinarioRepository).findAll(page);
		assertEquals(testPage, pageList);
		
	}
	
	@Test
	void testAtualizarVeterinario() {
		Optional<Veterinario> veterinarioTest = Optional.ofNullable(new Veterinario(5l ,"test@gft.com", "12345", "test", "12564"));
		
		when(veterinarioRepository.findById(5l)).thenReturn(veterinarioTest);
		when(veterinarioRepository.save(Mockito.any(Veterinario.class))).thenReturn(veterinarioTest.get());
		
		Veterinario veterinario = veterinarioService.atualizarVeterinario(new Veterinario("test@gft.com", "12345", "test", "12564"), 5l);
		
		//verify
		verify(veterinarioRepository).findById(5l);
		verify(veterinarioRepository).save(Mockito.any(Veterinario.class));
		assertEquals(veterinario,veterinarioTest.get() );
		
	}
	
	@Test
	void testAtualizarVeterinarioRetornaraUmErrorSeNaoExistirID() {
		
		Optional<Veterinario> page = Optional.empty() ;
		when(veterinarioRepository.findById(5l)).thenReturn(page);
		
		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> veterinarioService.atualizarVeterinario(new Veterinario("test@gft.com", "12345", "test", "12564"), 5l) );
		
		
		//verify
		verify(veterinarioRepository).findById(5l);
		verify(veterinarioRepository,never()).save(Mockito.any(Veterinario.class));
		assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 5");		
	}
	
	@Test
	void testExcluir() {
		
		Optional<Veterinario> veterinarioTest = Optional.ofNullable(new Veterinario(1l,"test@gft.com", "12345", "test", "12345"));	
		when(veterinarioRepository.findById(1l)).thenReturn(veterinarioTest);
		
		veterinarioService.excluir(1l);
		
		//verify
		verify(veterinarioRepository).findById(1l);
		
	}
	
	@Test
	void testExcluirRetornaraUmErrorSeNaoExistirID() {
		Optional<Veterinario> page = Optional.empty() ;
		when(veterinarioRepository.findById(1l)).thenReturn(page);
		
		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> veterinarioService.excluir(1l));

		
		//verify
		verify(veterinarioRepository).findById(1l);
		assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 1");		
		
	}
	
}
