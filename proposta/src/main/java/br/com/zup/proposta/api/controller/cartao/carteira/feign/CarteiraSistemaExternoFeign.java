package br.com.zup.proposta.api.controller.cartao.carteira.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.api.controller.cartao.carteira.dto.request.SolicitacaoInclusaoCarteiraRequestDto;
import br.com.zup.proposta.api.controller.cartao.carteira.dto.response.ResultadoCarteiraResponseDto;

@FeignClient(url = "${cartao.uri}", name = "carteira")
public interface CarteiraSistemaExternoFeign {

	@RequestMapping(value="/api/cartoes/{id}/carteiras", method= RequestMethod.POST, consumes = "application/json")
	ResultadoCarteiraResponseDto consultar(@PathVariable("id") String id,
			@RequestBody SolicitacaoInclusaoCarteiraRequestDto solicitacaoRequest);
}
