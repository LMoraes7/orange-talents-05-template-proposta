package br.com.zup.proposta.api.controller.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoAnaliseResponseDto;
import br.com.zup.proposta.dominio.modelo.proposta.SolicitacaoAnaliseRequestDto;

@FeignClient(name = "apiConsultaStatusProposta", url = "http://localhost:9999")
public interface ConsultarStatusPropostaFeign {

	@PostMapping("/api/solicitacao")
	ResultadoAnaliseResponseDto consultarStatus(@RequestBody SolicitacaoAnaliseRequestDto propostaRequestFeignDto);
}
