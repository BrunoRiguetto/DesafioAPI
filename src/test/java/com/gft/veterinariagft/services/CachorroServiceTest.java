package com.gft.veterinariagft.services;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
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

import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.CachorroRepository;
import com.gft.veterinariagft.util.Porte;

@ExtendWith(MockitoExtension.class)
class CachorroServiceTest {
	
	private CachorroService cachorroService;
	
	@Mock
	private CachorroRepository cachorroRepository;
	
	@BeforeEach
	void before() {
		Mockito.mock(cachorroRepository.getClass());
		cachorroService = new CachorroService(cachorroRepository);
	}
	
	@Test
	public void testBuscarTodos() {
		
		Cliente cliente = new Cliente(1l, "usuario@test.com", "test","test", "000.000.000-00",  "71 00000-0000");
		Cachorro cachorro = new Cachorro(3l, "cachorro", "labrador", 14.5, Porte.GRANDE, cliente);
		List<Cachorro> list = Arrays.asList(cachorro,cachorro,cachorro);
		Page<Cachorro> page = new PageImpl<>(list);
		Pageable pageable = null;
		when(cachorroRepository.findAll(pageable)).thenReturn(page);
		
		Page<Cachorro> listaClientePage = cachorroService.buscarTodos(null);
		
		//verify
		verify(cachorroRepository).findAll(pageable);
		assertEquals(listaClientePage, page);
	}
	
	@Test
	public void testBuscarPorId() {
		Optional<Cachorro> cachorro = ofNullable(new Cachorro());
		when(cachorroRepository.findById(1l)).thenReturn(cachorro);
		
		Cachorro cachorroTest = cachorroService.buscarPorId(1l);
		
		//verify
		verify(cachorroRepository).findById(1l);
		assertEquals(cachorroTest, cachorro.get());
	}
	
	@Test
	public void testBuscarPorIdRetornaraUmErrorSeNaoExistirID() {
		Optional<Cachorro> cachorro = empty() ;
		when(cachorroRepository.findById(1l)).thenReturn(cachorro);
		
		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> cachorroService.buscarPorId(1l));
		
		//verify
		verify(cachorroRepository).findById(1l);
		assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 1");		
	}
	
	@Test
	public void testCadastrar() {
		Cliente cliente = new Cliente(1l, "usuario@test.com", "test","test", "000.000.000-00",  "71 00000-0000");
		Cachorro cachorro = new Cachorro(3l, "cachorro", "labrador", 14.5, Porte.GRANDE, cliente);
		when(cachorroRepository.save(Mockito.any(Cachorro.class))).thenReturn(cachorro);
		
		Cliente clienteTest = new Cliente("usuario@test.com", "test","test", "000.000.000-00",  "71 00000-0000");
		Cachorro cachorroTest = new Cachorro( "cachorro", "labrador", 14.5, Porte.GRANDE, clienteTest);
		cachorroService.cadastrar(cachorroTest);
		
		//verify
		verify(cachorroRepository).save(cachorroTest);
	}
	
	@Test
	public void testAtualizar() {
		Optional<Cachorro> cachorrOptional = Optional.ofNullable(new Cachorro(3l, "cachorro", "labrador", 14.5, Porte.GRANDE, new Cliente()));
		
		when(cachorroRepository.findById(3l)).thenReturn(cachorrOptional);
		when(cachorroRepository.save(Mockito.any(Cachorro.class))).thenReturn(cachorrOptional.get());
		
		Cachorro  cachorro =  cachorroService.atualizar( new Cachorro("cachorro", "labrador", 14.5, Porte.GRANDE, new Cliente()), 3l) ;
		
		
		//verify
		verify(cachorroRepository).findById(3l);
		verify(cachorroRepository).save(Mockito.any(Cachorro.class));
		assertEquals(cachorro,cachorrOptional.get());
	}
	
	@Test
	public void testAtualizarRetornaraUmErrorSeNaoExistirID() {
		Optional<Cachorro> cachorrOptional = empty() ;
		
		when(cachorroRepository.findById(3l)).thenReturn(cachorrOptional);
		
		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> cachorroService.atualizar( new Cachorro("cachorro", "labrador", 14.5, Porte.GRANDE, new Cliente()), 3l));
		
		//verify
		verify(cachorroRepository).findById(3l);
		verify(cachorroRepository,never()).save(Mockito.any(Cachorro.class));
		assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 3");	
	}
	
	@Test
	public void testDelete() {
		Optional<Cachorro> cachorro = Optional.ofNullable(new Cachorro(3l, "cachorro", "labrador", 14.5, Porte.GRANDE, new Cliente())) ;
		when(cachorroRepository.findById(1l)).thenReturn(cachorro);

		cachorroService.delete(1l);
		
		//verify
		verify(cachorroRepository).findById(1l);
		verify(cachorroRepository).delete(Mockito.any(Cachorro.class));
		
	}
	
	@Test
	public void testDeleteRetornaraUmErrorSeNaoExistirID() {
		Optional<Cachorro> cachorro = empty() ;
		when(cachorroRepository.findById(1l)).thenReturn(cachorro);

		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> cachorroService.delete(1l) );
		
		//verify
		verify(cachorroRepository).findById(1l);
		verify(cachorroRepository,never()).delete(Mockito.any(Cachorro.class));
		assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 1");
	}
}
