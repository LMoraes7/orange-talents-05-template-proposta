package br.com.zup.proposta.api.controller.proposta.feign.fallback;

import br.com.zup.proposta.api.controller.proposta.dto.request.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoAnaliseResponseDto;
import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoSolicitacao;
import br.com.zup.proposta.api.controller.proposta.feign.ConsultarStatusPropostaFeign;

public class ConsultarStatusPropostaFallback implements ConsultarStatusPropostaFeign {

	@Override
	public ResultadoAnaliseResponseDto consultarStatus(SolicitacaoAnaliseRequestDto proposta) {
		return new ResultadoAnaliseResponseDto(proposta.getDocumento(), proposta.getNome(),
				ResultadoSolicitacao.COM_RESTRICAO, proposta.getId().toString());
	}
}