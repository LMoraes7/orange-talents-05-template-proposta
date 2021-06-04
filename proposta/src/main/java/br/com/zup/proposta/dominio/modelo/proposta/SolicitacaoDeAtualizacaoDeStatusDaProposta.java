package br.com.zup.proposta.dominio.modelo.proposta;

public class SolicitacaoDeAtualizacaoDeStatusDaProposta {

	private Status status;
	private Long id;

	public SolicitacaoDeAtualizacaoDeStatusDaProposta(Status status, Long id) {
		this.status = status;
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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