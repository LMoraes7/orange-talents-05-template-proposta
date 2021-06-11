package br.com.zup.proposta.dominio.modelo.cartao.teste;

import java.time.LocalDateTime;

public class BloqueioDto {

	private String id;
	private LocalDateTime bloqueadoEm;
	private String sistemaResponsavel;
	private boolean ativo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getBloqueadoEm() {
		return bloqueadoEm;
	}

	public void setBloqueadoEm(LocalDateTime bloqueadoEm) {
		this.bloqueadoEm = bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "BloqueioDto [id=" + id + ", bloqueadoEm=" + bloqueadoEm + ", sistemaResponsavel=" + sistemaResponsavel
				+ ", ativo=" + ativo + "]";
	}
}
