package com.gft.veterinariagft.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.Veterinario;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    Page<Atendimento> findByCliente(Cliente cliente,Pageable pageable);
    Page<Atendimento> findByVeterinario(Veterinario veterinario,Pageable pageable);

}
