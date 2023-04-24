package com.gft.veterinariagft.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long>{
    Page<Cachorro> findAll(Pageable pageable);
    Page<Cachorro> findByCliente(Cliente cliente,Pageable pageable);
    Optional<Cachorro> findByClienteCachorrosId(Long id);
}
