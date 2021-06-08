package br.com.zup.proposta.dominio.exception.cartao;

import br.com.zup.proposta.dominio.exception.EntidadeNaoEncontradaException;

public class CartaoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CartaoNaoEncontradoException(String msg) {
		super(msg);
	}

	public CartaoNaoEncontradoException(Long idDoCartao) {
		this("Cartão de id " + idDoCartao + " não existe.");
	}
}
