package com.gft.veterinariagft.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.exceptions.DataIntegrityViolentionException;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.VeterinarioRepository;

@Service
public class VeterinarioService {

	private final VeterinarioRepository veterinarioRepository;

	public VeterinarioService(VeterinarioRepository veterinarioRepository) {
		this.veterinarioRepository = veterinarioRepository;
	}

	public Veterinario salvar(Veterinario veterinario) {
		return veterinarioRepository.save(veterinario);
	}
	
	public void desativaVeterinario(Long id) {
		Veterinario veterinario = buscaID(id);
		
		if(veterinario.isEnabled()) {
			veterinario.setEnabled(false);
			salvar(veterinario);
		}
	}
	
	public void ativaVeterinario(Long id) {
		Veterinario veterinario = buscaID(id);
		
		if(!veterinario.isEnabled()) {
			veterinario.setEnabled(true);
			salvar(veterinario);
		}
	}

	@Transactional
	public Veterinario buscaID(Long id) {
		Optional<Veterinario> veterinario = veterinarioRepository.findById(id);
		return veterinario.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado - ID: " + id));
	}

	@Transactional
	public Page<Veterinario> listaTodos(Pageable page) {
		return veterinarioRepository.findAll(page);
	}

	public Veterinario atualizarVeterinario(Veterinario veterinario, Long id) {
		Veterinario veterinarioOriginal = this.buscaID(id);
		veterinario.setId(veterinarioOriginal.getId());
		return veterinarioRepository.save(veterinario);
	}

	public void excluir(Long id) {
		try {
			Veterinario veterinario = buscaID(id);
			veterinarioRepository.delete(veterinario);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolentionException("Este objeto tem vinculos no banco, verificar.");
		}
	}
	
	public Veterinario buscarVeterinarioPorEmail(String email) {
		Optional<Veterinario> optional = veterinarioRepository.findByEmail(email);
		return optional.orElseThrow(() -> new ResourceNotFoundException("Veterinario não encontrado - Email: " + email));
	}


}
