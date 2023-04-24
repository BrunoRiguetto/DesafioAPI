package com.gft.veterinariagft.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

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

import com.gft.veterinariagft.DTOs.cliente.ConsultaClienteDTO;
import com.gft.veterinariagft.DTOs.cliente.ConsultaSimplificadaClienteDTO;
import com.gft.veterinariagft.DTOs.cliente.MapperClienteDTO;
import com.gft.veterinariagft.DTOs.cliente.RegistroClienteDTO;
import com.gft.veterinariagft.DTOs.error.ErroDetalhes;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/clientes")
@Tag(name = "Cliente", description = "responsavel pelo CRUD do cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		
		this.clienteService = clienteService;
		
	}

	@GetMapping @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')") 
	@Operation(summary = "buscar todos os clientes, somente usuario ADMIN e VETERINARIO tem acesso ao endpoint ", tags = {"Cliente"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConsultaSimplificadaClienteDTO.class)))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())

	})
	public ResponseEntity<Page<ConsultaSimplificadaClienteDTO>> buscarTodosClientes(@PageableDefault(page = 0 , size = 10) Pageable pageable){
						
		return ResponseEntity.ok(clienteService.listarTodosClientes(pageable)
				.map( e -> MapperClienteDTO.fromEntitySimplificada(e)
						.add(linkTo(methodOn(ClienteController.class)
								.buscarCliente(e.getId())).withRel("Informação completa do cliente"))));
	}
	
	@PostMapping
	@Operation(summary = "salvar cliente", tags = {"Cliente"},
		responses = {
				@ApiResponse(description = "created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaClienteDTO.class))),
				@ApiResponse(description = "Bad Request", responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class)))
	})
	public ResponseEntity<RegistroClienteDTO> salvarCliente(@Valid @RequestBody RegistroClienteDTO dto){
		
		clienteService.salvarCliente(MapperClienteDTO.fromDTO(dto));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCpf()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}	
	
	
	@GetMapping("{id}") @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')") 
	@Operation(summary = "buscar cliente, somente usuario ADMIN e VETERINARIO tem acesso ao endpoint ", tags = {"Cliente"},
		responses = {
				@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaClienteDTO.class))),
				@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
				@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
	})
	public ResponseEntity<ConsultaClienteDTO> buscarCliente(@PathVariable Long id){
		
		Cliente cliente = clienteService.buscarCliente(id);
		
		return ResponseEntity.ok(MapperClienteDTO.fromEntity(cliente));
	}	
	
	
	@PutMapping("{id}")@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
	@Operation(summary = "alterar cliente, somente usuario ADMIN e VETERINARIO tem acesso ao endpoint", tags = {"Cliente"},
		responses = {
				@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaClienteDTO.class))),
				@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
				@ApiResponse(description = "dados invalido", responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
				@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
	})
	public ResponseEntity<ConsultaClienteDTO> alterarCliente(@Valid @RequestBody RegistroClienteDTO dto, @PathVariable Long id){
		
		Cliente cliente = clienteService.atualizarCliente(MapperClienteDTO.fromDTO(dto), id);
		
		return ResponseEntity.ok(MapperClienteDTO.fromEntity(cliente));
	}
	
	
	
	@DeleteMapping("{id}")@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "excluir cliente, somente usuario ADMIN tem acesso ao endpoint", tags = {"Cliente"},
   	responses = {
   			@ApiResponse(description = "sucesso", responseCode = "204", content = @Content()),
   			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
   			@ApiResponse(description = "Bad Request", responseCode = "403",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
   			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
    })
	public ResponseEntity<?> excluirCliente(@PathVariable Long id){
		clienteService.excluirCliente(id);
		return ResponseEntity.noContent().build();		
	}
	
	@PutMapping("desativa") @PreAuthorize("hasAuthority('ROLE_CLIENTE')")
	@Operation(summary = "Desativa conta do cliente logado, Somente cliente tem acesso, o token atual ficara indisponivel", tags = {"Cliente"},
   	responses = {
   			@ApiResponse(description = "sucesso", responseCode = "204", content = @Content()),
   			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
    })
	public ResponseEntity<?> desativaConta(Principal principal){
		clienteService.desativaCliente(principal.getName());
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("ativa/{id}") @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Ativa conta do cliente, Somente ADMIN tem acesso ao endPoint", tags = {"Cliente"},
   	responses = {
   			@ApiResponse(description = "sucesso", responseCode = "204", content = @Content()),
   			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
    })
	public ResponseEntity<?> AtivaConta(@PathVariable Long id){
		clienteService.ativaCliente(id);
		return ResponseEntity.noContent().build();
	}
	
}
