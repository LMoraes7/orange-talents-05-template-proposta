package br.com.zup.proposta.util.builder;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.Bloqueio;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.StatusBloqueio;

public class BloqueioBuilder {

	private String enderecoIp;
	private String userAgent;
	private Cartao cartao;
	private StatusBloqueio status;

	public BloqueioBuilder enderecoIp(String enderecoIp) {
		this.enderecoIp = enderecoIp;
		return this;
	}

	public BloqueioBuilder userAgent(String userAgent) {
		this.userAgent = userAgent;
		return this;
	}

	public BloqueioBuilder cartao(Cartao cartao) {
		this.cartao = cartao;
		return this;
	}

	public BloqueioBuilder status(StatusBloqueio status) {
		this.status = status;
		return this;
	}

	public Bloqueio build() {
		return new Bloqueio(enderecoIp, userAgent, cartao, status);
	}
}
