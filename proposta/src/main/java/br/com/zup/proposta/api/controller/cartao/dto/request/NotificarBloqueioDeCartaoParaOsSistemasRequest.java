package br.com.zup.proposta.api.controller.cartao.dto.request;

public class NotificarBloqueioDeCartaoParaOsSistemasRequest {

	private String sistemaResponsavel;

	public NotificarBloqueioDeCartaoParaOsSistemasRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	@Override
	public String toString() {
		return "NotificarBloqueioDeCartaoParaOsSistemasRequest [sistemaResponsavel=" + sistemaResponsavel + "]";
	}
}
