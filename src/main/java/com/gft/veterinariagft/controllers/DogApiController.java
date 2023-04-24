package com.gft.veterinariagft.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.veterinariagft.DTOs.vote.RegistroVoteDTO;
import com.gft.veterinariagft.DTOs.vote.VoteReponseGet;
import com.gft.veterinariagft.DTOs.vote.VoteSuccess;
import com.gft.veterinariagft.domain.dogapi.Breed;
import com.gft.veterinariagft.services.DogApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/servicos/")
@Tag(name = "Dog API", description = "Responsável por chamar serviços da DOG API")
public class DogApiController {

    public final DogApiService service;

    public DogApiController(DogApiService service) {
        this.service = service;
    }

    @GetMapping("buscarRaca")
    @Operation(summary = "Busca todas as raças que existem na API", tags = { "Dog API" })
    public ResponseEntity<List<Breed>> buscarRacas() throws IOException {
        List<Breed> response = service.getAllBreeds();

        return ResponseEntity.ok(response);
    }

    @GetMapping("buscarVotos")
    @Operation(summary = "Busca todos os votos que o usuário tem na API", tags = { "Dog API" })
    public ResponseEntity<List<VoteReponseGet>> buscarVotos() throws IOException {
        List<VoteReponseGet> response = service.getAllVotes();

        return ResponseEntity.ok(response);
    }

    @PostMapping("votar")
    @Operation(summary = "Cadastra um voto em uma imagem na API. Value: 1 para voto positivo e 0 para negativo", tags = {
            "Dog API" })
    public ResponseEntity<VoteSuccess> votar(@Valid @RequestBody RegistroVoteDTO dto) throws IOException {

        VoteSuccess obj = service.postVote(dto);

        return ResponseEntity.ok(obj);
    }

}
