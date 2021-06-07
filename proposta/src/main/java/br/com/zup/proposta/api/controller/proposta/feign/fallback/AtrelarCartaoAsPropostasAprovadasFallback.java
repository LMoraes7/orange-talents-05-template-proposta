package br.com.zup.proposta.api.controller.proposta.feign.fallback;

import org.springframework.stereotype.Component;

import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoCartaoResponseDto;
import br.com.zup.proposta.api.controller.proposta.feign.AtrelarCartaoAsPropostasAprovadasFeign;
import br.com.zup.proposta.dominio.modelo.proposta.SolicitacaoAnaliseRequestDto;

@Component
public class AtrelarCartaoAsPropostasAprovadasFallback implements AtrelarCartaoAsPropostasAprovadasFeign{

	@Override
	public ResultadoCartaoResponseDto atrelarCartao(SolicitacaoAnaliseRequestDto propostaRequestFeignDto) {
		return null;
	}
}
