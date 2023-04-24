package com.gft.veterinariagft.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.exceptions.DataIntegrityViolentionException;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.CachorroRepository;

@Service
public class CachorroService {

    private final CachorroRepository repository;

    public CachorroService(CachorroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Page<Cachorro> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Cachorro buscarPorId(Long id) {
        Optional<Cachorro> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado - ID: " + id));
    }
    
    @Transactional
    public Page<Cachorro> buscarPorCliente(Cliente cliente,Pageable pageable) {
    	return repository.findByCliente(cliente,pageable);
    }

    public Cachorro cadastrar(Cachorro obj) {
        return repository.save(obj);
    }

    public Cachorro atualizar(Cachorro obj, Long id) {
        Cachorro cachorro = buscarPorId(id);
        obj.setId(cachorro.getId());
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            Cachorro cachorro =  buscarPorId(id);
            repository.delete(cachorro);
        }
        catch(DataIntegrityViolationException ex) {
            throw new DataIntegrityViolentionException("Este objeto tem vinculos no banco, verificar.");
        }
    }

}