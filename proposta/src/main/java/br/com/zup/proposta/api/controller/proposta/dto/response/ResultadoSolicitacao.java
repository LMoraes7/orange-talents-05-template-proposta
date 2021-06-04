package br.com.zup.proposta.api.controller.proposta.dto.response;

import br.com.zup.proposta.dominio.modelo.proposta.Status;

public enum ResultadoSolicitacao {

	COM_RESTRICAO {
		@Override
		public Status getStatus() {
			return Status.NAO_ELEGIVEL;
		}
	},
	SEM_RESTRICAO {
		@Override
		public Status getStatus() {
			return Status.ELEGIVEL;
		}
	};
	
	public abstract Status getStatus();
}
