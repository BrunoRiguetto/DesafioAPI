package com.gft.veterinariagft.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.exceptions.DataIntegrityViolentionException;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public Page<Cliente> listarTodosClientes(Pageable pageable) {

		return clienteRepository.findAll(pageable);
	}
	
	public void desativaCliente(String email) {
		
		Cliente cliente = buscarClientePorEmail(email);
		
		if (cliente.isEnabled()) {
			cliente.setEnabled(false);
			salvarCliente(cliente);
		}
	}
	
	public void ativaCliente(Long id) {
		Cliente cliente = buscarCliente(id);
		if(!cliente.isEnabled()) {
			cliente.setEnabled(true);
			salvarCliente(cliente);
		}
	}

	public Cliente salvarCliente(Cliente cliente) {	
		try {
			return clienteRepository.save(cliente);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("possui email cadastrado");
		}
			
	}

	@Transactional
	public Cliente buscarCliente(Long id) {

		Optional<Cliente> optional = clienteRepository.findById(id);

		return optional.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado - ID: " + id));
	}

	public Cliente buscarClientePorEmail(String email) {
		Optional<Cliente> optional = clienteRepository.findByEmail(email);
		return optional.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado - Email: " + email));
	}

	public Cliente atualizarCliente(Cliente cliente, Long id) {

		Cliente clienteOriginal = this.buscarCliente(id);

		cliente.setId(clienteOriginal.getId());

		return clienteRepository.save(cliente);
	}

	public void excluirCliente(Long id) {

		Cliente clienteOriginal = this.buscarCliente(id);

		try {
			clienteRepository.delete(clienteOriginal);
		}catch(DataIntegrityViolationException ex) {
            throw new DataIntegrityViolentionException("Este objeto tem vinculos no banco, verificar.");
        }

	}

}
