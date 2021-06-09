package br.com.zup.proposta.api.controller.cartao.dto.response;

import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.StatusBloqueio;

public class BloquearCartaoResponseDto {

	private StatusBloqueio resultado;

	public StatusBloqueio getResultado() {
		return resultado;
	}

	public void setResultado(StatusBloqueio resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "BloquearCartaoResponseDto [resultado=" + resultado + "]";
	}
}
