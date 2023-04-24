package com.gft.veterinariagft.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.veterinariagft.DTOs.atendimento.AtendimentoMapper;
import com.gft.veterinariagft.DTOs.atendimento.ConsultaAtendimentoDTO;
import com.gft.veterinariagft.DTOs.atendimento.ConsultaSimplificadaAtendimentoDTO;
import com.gft.veterinariagft.DTOs.atendimento.RegistroAtendimentoDTO;
import com.gft.veterinariagft.DTOs.error.ErroDetalhes;
import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.services.AtendimentoService;
import com.gft.veterinariagft.services.CachorroService;
import com.gft.veterinariagft.services.ClienteService;
import com.gft.veterinariagft.services.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/Atendimento")
@Tag(name = "Atendimento", description = "responsavel pelo CRUD do atendimento")
public class AtendimentoController {

	private final AtendimentoService atendimentoService;
	private final VeterinarioService veterinarioService;
	private final CachorroService cachorroService;
	private final ClienteService clienteService;

	public AtendimentoController(AtendimentoService atendimentoService, VeterinarioService veterinarioService,
			CachorroService cachorroService, ClienteService clienteService) {
		this.atendimentoService = atendimentoService;
		this.veterinarioService = veterinarioService;
		this.cachorroService = cachorroService;
		this.clienteService = clienteService;
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
	@Operation(summary = "salvar atendimento, somente usuario com acesso ADMIN e VETERINARIO tem acesso", tags = { "Atendimento" }, responses = {
			@ApiResponse(description = "created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class)))
	})
	public ResponseEntity<RegistroAtendimentoDTO> salvarAtendimento(@RequestBody RegistroAtendimentoDTO dto) {
		Cachorro cachorro = cachorroService.buscarPorId(dto.getIdAnimal());

		Atendimento atendimento = AtendimentoMapper.fromDTO(dto, cachorro.getCliente(),
				veterinarioService.buscaID(dto.getIdVeterinario()), cachorro);

		atendimento = atendimentoService.salvar(atendimento);

		ConsultaAtendimentoDTO dto2 = AtendimentoMapper.fromEntity(atendimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto2.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("{id}")@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
	@Operation(summary = "busca atendimento pelo id, somente usuario com acesso ADMIN e VETERINARIO tem acesso", tags = { "Atendimento" }, responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaAtendimentoDTO.class))),
			@ApiResponse(description = "nao encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class)))
	})
	public ResponseEntity<ConsultaAtendimentoDTO> buscaPorId(@PathVariable Long id) {

		Atendimento atendimento = atendimentoService.buscaID(id);

		return ResponseEntity.ok(AtendimentoMapper.fromEntity(atendimento));

	}

	@GetMapping("/cliente")
	@PreAuthorize("hasAnyAuthority('ROLE_CLIENTE')")
	@Operation(summary = "Historico de atendimentos para cliente logado, somente usuario Cliente tem acesso", tags = { "Atendimento" }, responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaAtendimentoDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class)))
	})
	public ResponseEntity<Page<ConsultaAtendimentoDTO>> buscaAtendimentoPorIdCliente(@PageableDefault(page = 0 , size = 10) Pageable pageable) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		Cliente cliente = clienteService.buscarClientePorEmail(username);		
		return ResponseEntity.ok(atendimentoService.buscaAtendimentosPorCliente(cliente,pageable).map(a -> AtendimentoMapper.fromEntity(a)));
	}
	
	
	@GetMapping("/veterinario")
	@PreAuthorize("hasAnyAuthority('ROLE_VETERINARIO')")
	@Operation(summary = "Historico de atendimentos para veterinario logado, somente usuario Veterinario tem acesso", tags = { "Atendimento" }, responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaAtendimentoDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class)))
	})
	public ResponseEntity<Page<ConsultaAtendimentoDTO>> buscaAtendimentoPorIdVeterinario(@PageableDefault(page = 0 , size = 10) Pageable pageable, Principal principal ) {
		Veterinario veterinario = veterinarioService.buscarVeterinarioPorEmail(principal.getName());
		return ResponseEntity.ok(atendimentoService.buscaAtendimentosPorVeterinario(veterinario,pageable).map(a -> AtendimentoMapper.fromEntity(a)));
	}
	

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
	@Operation(summary = "buscar todos atendimentos, somente usuario ADMIN e VETERINARIO tem acesso ao endpoint", tags = {
			"Atendimento" }, responses = {
					@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConsultaSimplificadaAtendimentoDTO.class)))),
					@ApiResponse(description = "nao autorizado", responseCode = "403", content = @Content())

	})
	public ResponseEntity<Page<Object>> buscaTodos(@PageableDefault(page = 0 , size = 10) Pageable page) {

		return ResponseEntity.ok(atendimentoService.listaTodos(page)
				.map(v -> AtendimentoMapper.fromEntitySimplificada(v)
						.add(linkTo(methodOn(AtendimentoController.class)
								.buscaPorId(v.getId()))
								.withRel("Informação completa do Atendimento"))));
	}

	@PutMapping("{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
	@Operation(summary = "alterar atendimento, somente usuario ADMIN e VETERINARIO tem acesso ao endpoint", tags = { "Atendimento" }, responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistroAtendimentoDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class)))

	})
	public ResponseEntity<ConsultaAtendimentoDTO> alterarAtendimento(@RequestBody RegistroAtendimentoDTO dto,
			@PathVariable Long id) {

		Cachorro cachorro = cachorroService.buscarPorId(dto.getIdAnimal());

		Atendimento obj = AtendimentoMapper.fromDTO(dto, cachorro.getCliente(),
				veterinarioService.buscaID(dto.getIdVeterinario()), cachorro);

		Atendimento atendimento = atendimentoService.atualizarAtendimento(obj, id);

		return ResponseEntity.ok(AtendimentoMapper.fromEntity(atendimento));
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
	@Operation(summary = "excluir atendimento, somente usuario ADMIN e VETERINARIO tem acesso ao endpoint", tags = { "Atendimento" }, responses = {
			@ApiResponse(description = "sucesso", responseCode = "204", content = @Content()),
			@ApiResponse(description = "nao encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class)))
	})
	@DeleteMapping("{id}")	
	public ResponseEntity<ConsultaAtendimentoDTO> excluirAtendimento(@PathVariable Long id) {
		atendimentoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
