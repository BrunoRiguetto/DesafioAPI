package com.gft.veterinariagft.controllers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
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

import com.gft.veterinariagft.DTOs.cachorro.ConsultaCachorroDTO;
import com.gft.veterinariagft.DTOs.cachorro.ConsultarSimplificadaCachorroDTO;
import com.gft.veterinariagft.DTOs.cachorro.MapperCachorroDTO;
import com.gft.veterinariagft.DTOs.cachorro.RegistroCachorroDTO;
import com.gft.veterinariagft.DTOs.error.ErroDetalhes;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.dogapi.Breed;
import com.gft.veterinariagft.services.CachorroService;
import com.gft.veterinariagft.services.ClienteService;
import com.gft.veterinariagft.services.DogApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "v1/cachorros")
@Tag(name = "Cachorro", description = "responsavel pelo CRUD do cachorro")
public class CachorroController {
    
    private final CachorroService service;
    private final ClienteService clienteService;
    private final DogApiService dogService;

    public CachorroController(CachorroService service, ClienteService clienteService, DogApiService dogService) {
        this.service = service;
        this.clienteService = clienteService;
        this.dogService = dogService;
    }

    
    @GetMapping @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
    @Operation(summary = "buscar todos os cachorros, somente usuario ADMIN ou VETERINARIO tem acesso", tags = {"Cachorro"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(  schema = @Schema(implementation = ConsultarSimplificadaCachorroDTO.class)))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
    })
    public ResponseEntity<Page<Object>> buscarTodosOsCachorros(@PageableDefault(page = 0 , size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.buscarTodos(pageable).map(e -> MapperCachorroDTO.fromEntitySimplificada(e)
        		.add(linkTo(methodOn(CachorroController.class).buscarCachorroPorId(e.getId())).withRel("Informação completa do Cachorro"))));
    }
    

    @GetMapping(value = "/{id}")  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VETERINARIO')")
    @Operation(summary = "buscar cachorro por id, somente usuario ADMIN ou VETERINARIO tem acesso", tags = {"Cachorro"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaCachorroDTO.class))),
			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())

    })
    public ResponseEntity<ConsultaCachorroDTO> buscarCachorroPorId(@PathVariable Long id) {
        Cachorro cachorro = service.buscarPorId(id);

        return ResponseEntity.ok(MapperCachorroDTO.fromEntity(cachorro)
                .add(Link.of(cachorro.getImageRaca()).withRel("Imagem da raça")));
    }
      
    @PostMapping
    @Operation(summary = "salvar cachorro", tags = {"Cachorro"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaCachorroDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "nao encontrado", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDetalhes.class)))

    })
    public ResponseEntity<Void> inserirCachorro(@Valid @RequestBody RegistroCachorroDTO objDto) throws IOException {
        Cachorro obj = MapperCachorroDTO.fromDTO(objDto);
        Breed racaDetalhes = dogService.buscarRacaPorId(objDto.getRacaId());

        obj.setCliente(clienteService.buscarCliente(objDto.getClienteId()));
        obj.setRaca(racaDetalhes.getName());
        obj.buildRacaDescricao(racaDetalhes);

        obj = service.cadastrar(obj);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(obj.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    
    @PutMapping(value = "/{id}")
    @Operation(summary = "atualizar cachorro", tags = {"Cachorro"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaCachorroDTO.class))),
			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class)))
	})
    public ResponseEntity<ConsultaCachorroDTO> atualizarCachorro(@Valid @RequestBody RegistroCachorroDTO objDto, @PathVariable Long id) throws IOException {
        Cachorro obj = MapperCachorroDTO.fromDTO(objDto);
        Breed racaDetalhes = dogService.buscarRacaPorId(objDto.getRacaId());
        
        obj.setCliente(clienteService.buscarCliente(objDto.getClienteId()));
        obj.setRaca(racaDetalhes.getName());
        obj.buildRacaDescricao(racaDetalhes);
        obj = service.atualizar(obj, id);
        
        return ResponseEntity.ok(MapperCachorroDTO.fromEntity(obj));
    }

    
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "deletar cachorro", tags = {"Cachorro"},
   	responses = {
   			@ApiResponse(description = "noContent", responseCode = "204"),
   			@ApiResponse(description = "nao encontrado", responseCode = "404",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class))),
   			@ApiResponse(description = "Bad Request", responseCode = "403",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErroDetalhes.class)))
    })
    public ResponseEntity<Void> deletarCachorro(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("usuario")	@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    @Operation(summary = "buscar todos os cachorros do usuario logado, somente usuario Cliente tem acesso", tags = {"Cachorro"},
	responses = {
			@ApiResponse(description = "sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(  schema = @Schema(implementation = ConsultarSimplificadaCachorroDTO.class)))),
			@ApiResponse(description = "nao autorizado", responseCode = "403",content = @Content())
    })
    public ResponseEntity<Page<Object>> buscaCachorroDoCliente(@PageableDefault(page = 0 , size = 10) Pageable pageable,Principal principal){
    	Cliente cliente = clienteService.buscarClientePorEmail(principal.getName());
    	
    	return ResponseEntity.ok((service.buscarPorCliente(cliente,pageable)).map(e -> MapperCachorroDTO.fromEntitySimplificada(e)));
    }
    
    
}
