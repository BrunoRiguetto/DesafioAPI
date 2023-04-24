package com.gft.veterinariagft.services;

import static com.gft.veterinariagft.services.AtendimentoBuilder.umAtendimento;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.exceptions.ResourceNotFoundException;
import com.gft.veterinariagft.repositories.AtendimentoRepository;

@ExtendWith(MockitoExtension.class)
class AtendimentoServiceTest {
	
	private AtendimentoService atendimentoService;
	
	@Mock
	private AtendimentoRepository atendimentoRepository;
	
	@BeforeEach
	void before() {
		Mockito.mock(atendimentoRepository.getClass());
		atendimentoService = new AtendimentoService(atendimentoRepository);
	}
	
	@Test
	public void testSalva() {		
		Atendimento atendimentoTest = umAtendimento().agora();
		
		when(atendimentoRepository.save(Mockito.any(Atendimento.class))).thenReturn( atendimentoTest);
		
		Atendimento atendimento = umAtendimento().agora();
		Atendimento atendimentoVerify = atendimentoService.salvar(atendimento);
		atendimento.setId(1l);
		//verify
		verify(atendimentoRepository).save(atendimento);
		assertEquals(atendimentoTest, atendimentoVerify);
	}
	
	@Test
	public void testBuscaID() {
		Optional<Atendimento> atendimento = Optional.ofNullable(umAtendimento().agora());
		when(atendimentoRepository.findById(1l)).thenReturn(atendimento);
		
		Atendimento testAtendimento = atendimentoService.buscaID(1l);
		
		//verify
		verify(atendimentoRepository).findById(1l);
		assertEquals(testAtendimento,atendimento.get());
	}
	
	@Test
	public void testBuscaIdRetornaraUmErrorSeNaoExistirID() {
		Optional<Atendimento> atendimento = Optional.empty() ;
		when(atendimentoRepository.findById(1l)).thenReturn(atendimento);
				
		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> atendimentoService.buscaID(1l) );
		
		//verify
		verify(atendimentoRepository).findById(1l);
		assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 1");		
	}
	
	@Test
	public void testBuscaTodosAtendimento() {
		List<Atendimento> list = AtendimentoBuilder.umAtendimento().listaAtendimentos();
		Pageable page = null;
		Page<Atendimento> listaPage = new PageImpl<>(list);
		when(atendimentoRepository.findAll(page)).thenReturn(listaPage);
		
		Page<Atendimento> listaAtendimentos = atendimentoService.listaTodos(page);
		
		//verify
		verify(atendimentoRepository).findAll(page);
		assertEquals(listaAtendimentos, listaPage);
	}
	
	@Test
	public void testAtualizarAtendimento() {
		Optional<Atendimento> atendimento = Optional.ofNullable(AtendimentoBuilder.umAtendimento().agora());
		when(atendimentoRepository.findById(1l)).thenReturn(atendimento);
		when(atendimentoRepository.save(Mockito.any(Atendimento.class))).thenReturn(atendimento.get());
		Atendimento testAtendimento =atendimentoService.atualizarAtendimento(umAtendimento().comIDNull() , 1l);
		
		//verify
		verify(atendimentoRepository).findById(1l);
		assertEquals(testAtendimento, atendimento.get());
	}
	
	@Test
	public void testExcluir() {
		Optional<Atendimento> atendimento = Optional.ofNullable(AtendimentoBuilder.umAtendimento().agora());
		when(atendimentoRepository.findById(1l)).thenReturn(atendimento);
		
		atendimentoService.excluir(1l);
		
		//verify
		verify(atendimentoRepository).findById(1l);
	}
	
	@Test
	public void testExcluirRetornaraUmErrorSeNaoExistirID() {
		Optional<Atendimento> atendimento = Optional.empty() ;
		when(atendimentoRepository.findById(1l)).thenReturn(atendimento);
				
		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> atendimentoService.excluir(1l) );
		verify(atendimentoRepository).findById(1l);
		assertEquals(exception.getMessage(), "Objeto não encontrado - ID: 1");
	}

}
