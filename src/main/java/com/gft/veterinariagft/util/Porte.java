package com.gft.veterinariagft.util;

public enum Porte {

	PEQUENO(1, "Pequeno"),
	MEDIO(2, "Medio"),
	GRANDE(3, "grande");

	private int codigo;
	private String descricao;

	private Porte(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Porte toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}

		for(Porte p : Porte.values()) {
			if(cod.equals(p.getCodigo()))
				return p;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
