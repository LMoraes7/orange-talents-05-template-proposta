package br.com.zup.proposta.api.controller.proposta.feign.fallback;

import org.springframework.stereotype.Component;

import br.com.zup.proposta.api.controller.proposta.dto.request.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoCartaoResponseDto;
import br.com.zup.proposta.api.controller.proposta.feign.AtrelarCartaoAsPropostasAprovadasFeign;

@Component
public class AtrelarCartaoAsPropostasAprovadasFallback implements AtrelarCartaoAsPropostasAprovadasFeign{

	@Override
	public ResultadoCartaoResponseDto atrelarCartao(SolicitacaoAnaliseRequestDto propostaRequestFeignDto) {
		return null;
	}
}
