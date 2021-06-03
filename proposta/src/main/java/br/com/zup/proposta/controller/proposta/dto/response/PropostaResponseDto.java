package br.com.zup.proposta.controller.proposta.dto.response;

import br.com.zup.proposta.dominio.modelo.proposta.Proposta;

public class PropostaResponseDto {

	private Long id;

	public PropostaResponseDto(Proposta proposta) {
		this.id = proposta.getId();
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "PropostaResponseDto [id=" + id + "]";
	}
}
