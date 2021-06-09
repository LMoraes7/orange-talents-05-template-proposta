package br.com.zup.proposta.api.controller.proposta.dto.request;

import br.com.zup.proposta.dominio.modelo.proposta.StatusProposta;

public class SolicitacaoDeAtualizacaoDeStatusDaPropostaRequest {

	private StatusProposta status;
	private Long id;

	public SolicitacaoDeAtualizacaoDeStatusDaPropostaRequest(StatusProposta status, Long id) {
		this.status = status;
		this.id = id;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public void setStatus(StatusProposta status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SolicitacaoDeAtualizacaoDeStatusDaProposta [status=" + status + ", id=" + id + "]";
	}
}