package br.com.zup.proposta.api.controller.cartao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.proposta.api.controller.cartao.dto.request.NotificarBloqueioDeCartaoParaOsSistemasRequest;
import br.com.zup.proposta.api.controller.cartao.dto.response.NotificarBloqueioDeCartaoParaOsSistemasResponse;

@FeignClient(name = "apiBloqueioDeCartoes", url = "${cartao.uri}")
public interface NotificarBloqueioDeCartaoFeign {

	@PostMapping("/api/cartoes/{id}/bloqueios")
	NotificarBloqueioDeCartaoParaOsSistemasResponse notificar(@PathVariable("id") String id,
			@RequestBody NotificarBloqueioDeCartaoParaOsSistemasRequest notificacao);

}
