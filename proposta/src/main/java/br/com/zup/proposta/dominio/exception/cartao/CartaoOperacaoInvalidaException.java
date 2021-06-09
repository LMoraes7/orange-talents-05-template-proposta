package br.com.zup.proposta.dominio.exception.cartao;

import br.com.zup.proposta.dominio.exception.OperacaoInvalidaException;

public class CartaoOperacaoInvalidaException extends OperacaoInvalidaException {

	private static final long serialVersionUID = 1L;

	public CartaoOperacaoInvalidaException(String msg) {
		super(msg);
	}
}
