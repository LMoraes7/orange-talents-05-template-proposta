package br.com.zup.proposta.dominio.exception;

public class ValorJaCadastradoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public ValorJaCadastradoException(String msg) {
		super(msg);
	}

	public ValorJaCadastradoException(String campo, Object value) {
		this(campo + " de valor " + value + " já está cadastrado.");
	}
}
