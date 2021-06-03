package br.com.zup.proposta.dominio.exception.proposta;

import br.com.zup.proposta.dominio.exception.EntidadeNaoEncontradaException;

public class PropostaNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PropostaNaoEncontradaException(String msg) {
		super(msg);
	}

	public PropostaNaoEncontradaException(Long id) {
		this("Proposta de id " +id+ " não existe.");
	}
}
