package br.com.zup.proposta.api.controller.proposta.dto.response;

import br.com.zup.proposta.dominio.modelo.proposta.StatusProposta;

public enum ResultadoSolicitacao {

	COM_RESTRICAO {
		@Override
		public StatusProposta getStatus() {
			return StatusProposta.NAO_ELEGIVEL;
		}
	},
	SEM_RESTRICAO {
		@Override
		public StatusProposta getStatus() {
			return StatusProposta.ELEGIVEL;
		}
	};
	
	public abstract StatusProposta getStatus();
}
