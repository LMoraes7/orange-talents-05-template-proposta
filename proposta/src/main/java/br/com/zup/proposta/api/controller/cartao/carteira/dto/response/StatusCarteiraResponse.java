package br.com.zup.proposta.api.controller.cartao.carteira.dto.response;

public enum StatusCarteiraResponse {

	ASSOCIADA {
		@Override
		public boolean isAprovada() {
			return true;
		}
	},
	FALHA {
		@Override
		public boolean isAprovada() {
			return false;
		}
	};
	
	public abstract boolean isAprovada();
}
