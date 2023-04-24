package com.gft.veterinariagft.controllers;

import java.net.URI;
import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.veterinariagft.DTOs.error.ErroDetalhes;
import com.gft.veterinariagft.DTOs.veterinario.ConsultaVeterinarioDTO;
import com.gft.veterinariagft.DTOs.veterinario.RegistroVeterinarioDTO;
import com.gft.veterinariagft.DTOs.veterinario.VeterinarioMapper;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.services.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/veterinarios")
@Tag(name = "Veterinário", description = "responsavel pelo CRUD do Veterinário")
public class VeterinarioController {

	private final VeterinarioService veterinarioService;

	public VeterinarioController(VeterinarioService veterinarioService) {
		this.veterinarioService = veterinarioService;
	}

	@PostMapping @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "salvar veterinário, somente usuario ADMIN tem acesso ao endpoint", tags = {"Veterinário"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaVeterinarioDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
	})
	public ResponseEntity<ConsultaVeterinarioDTO> salvar(@RequestBody RegistroVeterinarioDTO dto) {

		Veterinario veterinario = veterinarioService.salvar(VeterinarioMapper.fromDTO(dto));
		ConsultaVeterinarioDTO dto2 = VeterinarioMapper.fromEntity(veterinario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto2.getId()).toUri();
		return ResponseEntity.created(uri).body(dto2);

	}
	

	@GetMapping("/{id}")
	@Operation(summary = "buscar veterinário por id", tags = {"Veterinário"},
		responses = {
				@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaVeterinarioDTO.class))),
				@ApiResponse(description = "não encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class)))
				
	})
	public ResponseEntity<ConsultaVeterinarioDTO> buscaPeloID(@PathVariable Long id) {
		Veterinario veterinario = veterinarioService.buscaID(id);
		return ResponseEntity.ok(VeterinarioMapper.fromEntity(veterinario));
	}

	
	@GetMapping
	@Operation(summary = "buscar todos os veterinário por id", tags = {"Veterinário"},
		responses = {
				@ApiResponse(description = "sucesso", responseCode = "200")
	})
	public ResponseEntity<Page<Object>> buscaTodos(@PageableDefault(page = 0 , size = 10) Pageable page) {
		return ResponseEntity.ok(veterinarioService.listaTodos(page).map(v -> VeterinarioMapper.fromEntity(v)));
	}

	
	@PutMapping("{id}") @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')") 
	@Operation(summary = "alterar veterinário, somente usuario ADMIN e VETERINARIO tem acesso ao endpoint", tags = {"Veterinário"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaVeterinarioDTO.class))),
			@ApiResponse(description = "não encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
	})
	public ResponseEntity<ConsultaVeterinarioDTO> alteraVeterinario(@RequestBody RegistroVeterinarioDTO dto,
			@PathVariable Long id) {
		Veterinario veterinario = veterinarioService.atualizarVeterinario(VeterinarioMapper.fromDTO(dto), id);
		return ResponseEntity.ok(VeterinarioMapper.fromEntity(veterinario));
	}
	
	@DeleteMapping("{id}") @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "excluir veterinário, somente usuario ADMIN tem acesso ao endpoint", tags = {"Veterinário"},
   	responses = {
   			@ApiResponse(description = "noContent", responseCode = "204",content = @Content()),
			@ApiResponse(description = "não encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
   			@ApiResponse(description = "Bad Request", responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
   			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
	})
	public ResponseEntity<ConsultaVeterinarioDTO> excluirVeterinario(@PathVariable Long id){
		veterinarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("desativa/{id}") @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Desativa conta do Veterinario, somente usuario ADMIN tem acesso ao endPoint", tags = {"Veterinário"},
   	responses = {
   			@ApiResponse(description = "sucesso", responseCode = "204", content = @Content()),
   			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
    })
	public ResponseEntity<?> desativaConta(@PathVariable Long id){
		veterinarioService.desativaVeterinario(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("ativa/{id}") @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Ativa conta do Veterinario, somente ADMIN tem acesso ao endPoint", tags = {"Veterinário"},
   	responses = {
   			@ApiResponse(description = "sucesso", responseCode = "204", content = @Content()),
   			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
    })
	public ResponseEntity<?> AtivaConta(@PathVariable Long id){
		veterinarioService.ativaVeterinario(id);
		return ResponseEntity.noContent().build();
	}
}
