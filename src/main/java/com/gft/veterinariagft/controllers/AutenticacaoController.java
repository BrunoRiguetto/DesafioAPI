package com.gft.veterinariagft.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.veterinariagft.DTOs.error.ErroDetalhes;
import com.gft.veterinariagft.DTOs.token.AutenticacaoForm;
import com.gft.veterinariagft.DTOs.token.TokenDTO;
import com.gft.veterinariagft.services.AutenticacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/auth")
@Tag(name = "Autenticação", description = "responsavel pela autenticação do usuário")
public class AutenticacaoController{

	@Autowired
	AutenticacaoService autenticacaoService;
	
	@PostMapping
	@Operation(summary = "autenticação do usuário", tags = {"Autenticação"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))),
			@ApiResponse(description = "UNAUTHORIZED", responseCode = "401",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class)))
	})
	public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoForm authForm) throws AuthenticationException{
		return ResponseEntity.ok(autenticacaoService.autentica(authForm));
	}
	
}
