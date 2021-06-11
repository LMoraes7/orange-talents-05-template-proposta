package br.com.zup.proposta.api.controller.cartao.viagem.dto.response;

public enum StatusAvisoViagem {

	CRIADO {
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
