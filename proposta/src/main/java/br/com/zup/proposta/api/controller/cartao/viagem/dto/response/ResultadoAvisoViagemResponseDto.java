package br.com.zup.proposta.api.controller.cartao.viagem.dto.response;

public class ResultadoAvisoViagemResponseDto {

	private StatusAvisoViagem resultado;

	public StatusAvisoViagem getResultado() {
		return resultado;
	}

	public void setResultado(StatusAvisoViagem resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ResultadoAvisoViagemResponseDto [resultado=" + resultado + "]";
	}
}
