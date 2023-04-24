package com.gft.veterinariagft.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {
	private final AtendimentoRepository atendimentoRepository;

	public AtendimentoService(AtendimentoRepository atendimentoRepository) {
		this.atendimentoRepository = atendimentoRepository;
	}

	public Atendimento salvar(Atendimento atendimento) {
		return atendimentoRepository.save(atendimento);
	}

	@Transactional
	public Atendimento buscaID(Long id) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
		return atendimento.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado - ID: " + id));
	}

	@Transactional
	public Page<Atendimento> listaTodos(Pageable page) {
		return atendimentoRepository.findAll(page);
	}

	public Atendimento atualizarAtendimento(Atendimento atendimento, Long id) {
		Atendimento atendimentoOriginal = this.buscaID(id);
		atendimento.setId(atendimentoOriginal.getId());
		return atendimentoRepository.save(atendimento);
	}

	public void excluir(Long id) {
		Atendimento atendimento = buscaID(id);
		atendimentoRepository.delete(atendimento);
		;
	}
	
	@Transactional
	public Page<Atendimento> buscaAtendimentosPorCliente(Cliente cliente,Pageable pageable) {
		return atendimentoRepository.findByCliente(cliente, pageable);
	}
	
	@Transactional
	public Page<Atendimento> buscaAtendimentosPorVeterinario(Veterinario veterinario,Pageable pageable) {
		return   atendimentoRepository.findByVeterinario(veterinario,pageable);
	}


}
