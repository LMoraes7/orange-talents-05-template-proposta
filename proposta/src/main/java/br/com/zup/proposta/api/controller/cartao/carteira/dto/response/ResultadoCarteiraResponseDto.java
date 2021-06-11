package br.com.zup.proposta.api.controller.cartao.carteira.dto.response;

public class ResultadoCarteiraResponseDto {

	private StatusCarteiraResponse resultado;
	private String id;

	public StatusCarteiraResponse getResultado() {
		return resultado;
	}

	public void setResultado(StatusCarteiraResponse resultado) {
		this.resultado = resultado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ResultadoCarteiraResponseDto [resultado=" + resultado + ", id=" + id + "]";
	}
}
