package br.com.zup.proposta.dominio.modelo.cartao.bloqueio;

import br.com.zup.proposta.dominio.modelo.cartao.StatusCartao;

public enum StatusBloqueio {

	EM_ANALISE {
		@Override
		public StatusCartao getStatusCartao() {
			return StatusCartao.ATIVO;
		}
	},
	EFETUADO {
		@Override
		public StatusCartao getStatusCartao() {
			return StatusCartao.BLOQUEADO;
		}
	},
	FALHA {
		@Override
		public StatusCartao getStatusCartao() {
			return StatusCartao.ATIVO;
		}
	};
	
	public abstract StatusCartao getStatusCartao();
}
