package br.com.zup.proposta.api.controller.cartao.carteira.dto.request;

public class SolicitacaoInclusaoCarteiraRequestDto {

	private String email;
	private String carteira;

	public SolicitacaoInclusaoCarteiraRequestDto(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getCarteira() {
		return carteira;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "SolicitacaoInclusaoCarteiraRequestDto [email=" + email + ", carteira=" + carteira + "]";
	}
}