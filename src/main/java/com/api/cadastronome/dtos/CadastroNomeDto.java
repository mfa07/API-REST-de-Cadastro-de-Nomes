package com.api.cadastronome.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CadastroNomeDto {
	
	@NotBlank
	@Size(max = 100)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
