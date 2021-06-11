package br.com.zup.proposta.api.controller.cartao.viagem.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.proposta.api.controller.cartao.viagem.dto.request.NotificarAvisoViagemRequestDto;
import br.com.zup.proposta.api.controller.cartao.viagem.dto.response.ResultadoAvisoViagemResponseDto;

@FeignClient(name = "postAvisos", url = "http://localhost:8888")
public interface NotificarAvisoViagemSistemaBancarioFeign {

	@RequestMapping(value="/api/cartoes/{id}/avisos", method= RequestMethod.POST, consumes = "application/json")
	ResultadoAvisoViagemResponseDto notificar(@PathVariable("id") String id,
			@RequestBody NotificarAvisoViagemRequestDto avisoViagemRequest);
}
