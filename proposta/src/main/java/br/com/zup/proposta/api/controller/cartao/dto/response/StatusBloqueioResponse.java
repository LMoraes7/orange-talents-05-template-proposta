package br.com.zup.proposta.api.controller.cartao.dto.response;

import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.StatusBloqueio;

public enum StatusBloqueioResponse {

	BLOQUEADO {
		@Override
		public StatusBloqueio getStatus() {
			return StatusBloqueio.EFETUADO;
		}
	},
	FALHA {
		@Override
		public StatusBloqueio getStatus() {
			return StatusBloqueio.FALHA;
		}
	};
	
	public abstract StatusBloqueio getStatus();
}
