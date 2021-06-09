package br.com.zup.proposta.api.controller.cartao.dto.request;

public class BloquearCartaoRequestDto {

	private String sistemaResponsavel;

	public BloquearCartaoRequestDto(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	@Override
	public String toString() {
		return "BloquearCartaoRequestDto [sistemaResponsavel=" + sistemaResponsavel + "]";
	}
}
