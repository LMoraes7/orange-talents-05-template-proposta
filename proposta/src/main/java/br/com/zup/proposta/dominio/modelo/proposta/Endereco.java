package br.com.zup.proposta.dominio.modelo.proposta;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	@Column(nullable = false)
	private String logradouro;

	private String complemento;

	@Column(nullable = false)
	private Long numero;

	@Deprecated
	public Endereco() {
	}

	public Endereco(String logradouro, Long numero, String complemento) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", complemento=" + complemento + ", numero=" + numero + "]";
	}

}
