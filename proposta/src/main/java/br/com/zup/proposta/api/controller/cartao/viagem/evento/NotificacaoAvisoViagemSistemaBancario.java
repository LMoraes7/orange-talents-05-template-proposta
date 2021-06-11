package br.com.zup.proposta.api.controller.cartao.viagem.evento;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.viagem.AvisoViagem;

public class NotificacaoAvisoViagemSistemaBancario {

	private AvisoViagem avisoViagem;
	private Cartao cartao;

	public NotificacaoAvisoViagemSistemaBancario(AvisoViagem avisoViagem, Cartao cartao) {
		this.avisoViagem = avisoViagem;
		this.cartao = cartao;
	}

	public AvisoViagem getAvisoViagem() {
		return avisoViagem;
	}

	public Cartao getCartao() {
		return cartao;
	}

	@Override
	public String toString() {
		return "NotificacaoAvisoViagemSistemaBancario [avisoViagem=" + avisoViagem + ", cartao=" + cartao + "]";
	}
}
