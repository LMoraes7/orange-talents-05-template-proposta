package br.com.zup.proposta.api.controller.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.proposta.api.controller.proposta.dto.request.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoCartaoResponseDto;
import br.com.zup.proposta.api.controller.proposta.feign.fallback.AtrelarCartaoAsPropostasAprovadasFallback;

@FeignClient(name = "apiAtrelarCartaoAsPropostasAprovadas", url = "http://localhost:8888", fallback = AtrelarCartaoAsPropostasAprovadasFallback.class)
public interface AtrelarCartaoAsPropostasAprovadasFeign {

	@PostMapping("/api/cartoes")
	ResultadoCartaoResponseDto atrelarCartao(@RequestBody SolicitacaoAnaliseRequestDto propostaRequestFeignDto);
}
