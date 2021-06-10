package br.com.zup.proposta.api.controller.cartao.dto.request;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.Bloqueio;

public class AvisoBloqueioDeCartaoParaOsSistemas {

	private String sistemaResponsavel;
	private Cartao cartao;
	private Bloqueio bloqueio;

	public AvisoBloqueioDeCartaoParaOsSistemas(String sistemaResponsavel, Cartao cartao, Bloqueio bloqueio) {
		this.sistemaResponsavel = sistemaResponsavel;
		this.cartao = cartao;
		this.bloqueio = bloqueio;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Bloqueio getBloqueio() {
		return bloqueio;
	}

	@Override
	public String toString() {
		return "AvisoBloqueioDeCartaoParaOsSistemas [sistemaResponsavel=" + sistemaResponsavel + ", cartao=" + cartao
				+ ", bloqueio=" + bloqueio + "]";
	}
}