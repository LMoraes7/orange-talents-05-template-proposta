package br.com.zup.proposta.api.controller.cartao.viagem.evento;

import br.com.zup.proposta.api.controller.cartao.viagem.dto.response.StatusAvisoViagem;
import br.com.zup.proposta.dominio.modelo.cartao.viagem.AvisoViagem;

public class SolicitacaoParaCadastroDeAvisoViagem {

	private AvisoViagem avisoViagem;
	private StatusAvisoViagem statusAvisoViagem;

	public SolicitacaoParaCadastroDeAvisoViagem(AvisoViagem avisoViagem, StatusAvisoViagem statusAvisoViagem) {
		this.avisoViagem = avisoViagem;
		this.statusAvisoViagem = statusAvisoViagem;
	}

	public AvisoViagem getAvisoViagem() {
		return avisoViagem;
	}

	public StatusAvisoViagem getStatusAvisoViagem() {
		return statusAvisoViagem;
	}

	@Override
	public String toString() {
		return "SolicitacaoParaCadastroDeAvisoViagem [avisoViagem=" + avisoViagem + ", statusAvisoViagem="
				+ statusAvisoViagem + "]";
	}
}
