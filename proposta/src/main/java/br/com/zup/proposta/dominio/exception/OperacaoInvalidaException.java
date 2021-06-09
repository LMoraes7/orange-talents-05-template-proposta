package br.com.zup.proposta.dominio.exception;

public abstract class OperacaoInvalidaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public OperacaoInvalidaException(String msg) {
		super(msg);
	}
}
