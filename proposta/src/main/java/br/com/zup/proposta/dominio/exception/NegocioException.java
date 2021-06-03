package br.com.zup.proposta.dominio.exception;

public abstract class NegocioException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String msg) {
		super(msg);
	}
}
