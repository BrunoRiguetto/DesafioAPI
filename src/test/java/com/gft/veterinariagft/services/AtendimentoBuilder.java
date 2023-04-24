package com.gft.veterinariagft.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.DadosAnimal;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.util.Porte;

public class AtendimentoBuilder {
	
	private static Atendimento atendimento;
	
	private AtendimentoBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public static AtendimentoBuilder umAtendimento() {
		
		AtendimentoBuilder atendimentoBuilder = new AtendimentoBuilder();
		
		Cliente cliente = new Cliente(1l, "usuario@test.com", "test","test", "000.000.000-00",  "71 00000-0000");
		DadosAnimal dadosAnimal = new DadosAnimal(39.0, "febre", 12.0);
		Veterinario veterinario = new Veterinario(2l, "veterinario@test.com", "test", "test", "12345");
		Cachorro cachorro = new Cachorro(3l, "cachorro", "labrador", 14.5, Porte.GRANDE, cliente);
		Atendimento atendimento = new Atendimento(1l, new Date(), "1 hora", dadosAnimal, "pneunomia", "beber muita agua", cachorro, veterinario, cliente);
		AtendimentoBuilder.atendimento = atendimento;
		return atendimentoBuilder;
	}
	
	public Atendimento agora() {
		return atendimento;
	}
	
	public Atendimento comIDNull() {
		atendimento.setId(null);
		return atendimento;
	}
	
	
	public List<Atendimento> listaAtendimentos(){
		return Arrays.asList(atendimento,atendimento,atendimento,atendimento);
	}
}
