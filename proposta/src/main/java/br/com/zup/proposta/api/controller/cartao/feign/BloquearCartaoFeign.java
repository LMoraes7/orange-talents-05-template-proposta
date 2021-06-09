package br.com.zup.proposta.api.controller.cartao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.proposta.api.controller.cartao.dto.request.BloquearCartaoRequestDto;
import br.com.zup.proposta.api.controller.cartao.dto.response.BloquearCartaoResponseDto;

@FeignClient(name = "apiBloquearCartoes", url = "http://localhost:8888")
public interface BloquearCartaoFeign {

	@PostMapping("/api/cartoes/{id}/bloqueios")
	BloquearCartaoResponseDto bloquear(@PathVariable("id") String id,
			@RequestBody BloquearCartaoRequestDto bloqueioRequest);
}
