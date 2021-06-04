package br.com.zup.proposta.api.controller.proposta.dto.response;

import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.modelo.proposta.Status;

public class PropostaResponseDto {

	private Long id;
	private Status status;

	public PropostaResponseDto(Proposta proposta) {
		this.id = proposta.getId();
		this.status = proposta.getStatus();
	}

	public Long getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "PropostaResponseDto [id=" + id + ", status=" + status + "]";
	}
}