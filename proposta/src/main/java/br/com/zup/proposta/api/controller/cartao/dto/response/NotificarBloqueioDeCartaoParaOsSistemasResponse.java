package br.com.zup.proposta.api.controller.cartao.dto.response;

public class NotificarBloqueioDeCartaoParaOsSistemasResponse {

	private StatusBloqueioResponse resultado;

	public StatusBloqueioResponse getResultado() {
		return resultado;
	}

	public void setResultado(StatusBloqueioResponse resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "NotificarBloqueioDeCartaoParaOsSistemasResponse [resultado=" + resultado + "]";
	}
}