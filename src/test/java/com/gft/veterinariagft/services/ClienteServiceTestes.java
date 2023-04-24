package com.gft.veterinariagft.services;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTestes {
	
	private ClienteService service;
	
	@Mock
	private ClienteRepository repository;
	
	private Long idNaoExistente;
	
	@BeforeEach
	void setUp() throws Exception{
		idNaoExistente = 1000L;
		Mockito.mock(repository.getClass());
		service = new ClienteService(repository);
    }
	
	@Test
	public void deveBuscarClientePorId() {
		
		Optional<Cliente> clienteTest = Optional.ofNullable(new Cliente(1L, "maria@gft.com", "12345", "Maria", "666.777.888-99", "12345-67890"));	
		Mockito.when(repository.findById(1l)).thenReturn(clienteTest);
	
		Cliente cliente = service.buscarCliente(1L);
		
		Assertions.assertNotNull(cliente);
	}
	
	@Test
	public void deveLancarExcessaoQuandoBuscarClientePorIdNaoExistente() {
	
		Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException("Cliente não encontrado - ID: "));
		
		try {
			service.buscarCliente(idNaoExistente);
		}catch (Exception e) {
			Assertions.assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}	
	
	@Test
	void deveBuscarTodosClientes() {
		
		Pageable page = null;
				
		List<Cliente> list = Arrays.asList(
				new Cliente(1L, "maria@gft.com", "12345", "Maria", "666.777.888-99", "12345-67890"),
				new Cliente(2l, "jose@gft.com", "12345", "Jose", "666.777.888-99", "12345-67890"),
				new Cliente(1L, "joao@gft.com", "12345", "Joao", "666.777.888-99", "12345-67890"),
				new Cliente(1L, "roberto@gft.com", "12345", "Roberto", "666.777.888-99", "12345-67890"));
		
		Page<Cliente> pageList = new PageImpl<>(list);
		
		Mockito.when(repository.findAll(page)).thenReturn(pageList);
		
		Page<Cliente> testPage = service.listarTodosClientes(page);
		
		Mockito.verify(repository).findAll(page);
		Assertions.assertEquals(testPage, pageList);		
	}	
	
	@Test
	public void deveBuscarClienteERetornarUmObjetoNaoEncontrado() {
		
		Optional<Cliente> page = Optional.empty();		
		Mockito.when(repository.findById(5l)).thenReturn(page);	
		
		try {
			service.buscarCliente(5L);
		}catch (Exception e) {
			Assertions.assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}
	
	@Test
	public void deveSalvarCliente() {
		
		Cliente cliente1 = new Cliente("maria@gft.com", "12345", "Maria", "666.777.888-99", "12345-67890");
		Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(cliente1);
				
		Cliente cliente = service.salvarCliente(cliente1);

        Assertions.assertEquals(cliente, cliente1);
        Mockito.verify(repository).save(cliente1);
	}
	
	@Test
	public void deveAtualizarCliente() {
		
		Optional<Cliente> cliente1 = Optional.ofNullable(new Cliente(2L ,"maria@gft.com", "12345", "Maria", "666.777.888-99", "12345-67890"));
		
		Mockito.when(repository.findById(2L)).thenReturn(cliente1);
		Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(cliente1.get());
		
		Cliente cliente = service.atualizarCliente(new Cliente("maria@gft.com", "12345", "Maria", "666.777.888-99", "12345-67890"), 2L);
		
		Mockito.verify(repository).findById(2l);
		Mockito.verify(repository).save(Mockito.any(Cliente.class));
		Assertions.assertEquals(cliente,cliente1.get());	
	}
	
	@Test
	public void deveDeletarClienteComIdExistente() {
				
		service.excluirCliente(1l);
		
		verify(repository).deleteById(1l);
	}
	
	@Test
	public void deveLancarExcessaoQuandoDeletarClienteComIdNaoExistente() {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException("Cliente não encontrado - ID: "));
		
		try {
			service.buscarCliente(idNaoExistente);
		}catch (Exception e) {
			Assertions.assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}	
}
