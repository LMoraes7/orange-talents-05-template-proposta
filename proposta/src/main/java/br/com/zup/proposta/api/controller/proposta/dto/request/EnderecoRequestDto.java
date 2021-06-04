package br.com.zup.proposta.api.controller.proposta.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class EnderecoRequestDto {

	@NotBlank
	private String logradouro;

	@NotNull
	@PositiveOrZero
	private Long numero;
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "EnderecoRequestDto [logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento
				+ "]";
	}
}
