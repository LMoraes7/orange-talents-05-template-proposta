package br.com.zup.proposta.api.controller.cartao.event;

import br.com.zup.proposta.api.controller.cartao.dto.response.StatusBloqueioResponse;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.Bloqueio;

public class SolicitacaoParaAtualizacaoDeStatusDoCartao {

	private Cartao cartao;
	private Bloqueio bloqueio;
	private StatusBloqueioResponse resultado;

	public SolicitacaoParaAtualizacaoDeStatusDoCartao(Cartao cartao, Bloqueio bloqueio,
			StatusBloqueioResponse resultado) {
		this.cartao = cartao;
		this.bloqueio = bloqueio;
		this.resultado = resultado;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Bloqueio getBloqueio() {
		return bloqueio;
	}

	public StatusBloqueioResponse getResultado() {
		return resultado;
	}

	@Override
	public String toString() {
		return "SolicitacaoParaAtualizacaoDeStatusDoCartao [cartao=" + cartao + ", bloqueio=" + bloqueio
				+ ", resultado=" + resultado + "]";
	}
}
