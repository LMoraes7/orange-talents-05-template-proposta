package br.com.zup.proposta.dominio.exception.biometria;

import br.com.zup.proposta.dominio.exception.EntidadeNaoEncontradaException;

public class BiometriaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public BiometriaNaoEncontradaException(String msg) {
		super(msg);
	}

	public BiometriaNaoEncontradaException(Long idDoCartao) {
		this("Biometria de id " + idDoCartao + " não existe.");
	}
}
