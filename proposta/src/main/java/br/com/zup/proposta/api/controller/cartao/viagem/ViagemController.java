package br.com.zup.proposta.api.controller.cartao.viagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.api.controller.cartao.viagem.dto.request.AvisoViagemRequestDto;
import br.com.zup.proposta.api.controller.cartao.viagem.evento.NotificacaoAvisoViagemSistemaBancario;
import br.com.zup.proposta.dominio.exception.cartao.CartaoNaoEncontradoException;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.repository.CartaoRepository;

@RestController
@RequestMapping("/api/viagens")
public class ViagemController {

	private CartaoRepository cartaoRepository;
	private ApplicationEventPublisher eventPublisher;
	private final Logger LOG = LoggerFactory.getLogger(ViagemController.class);

	public ViagemController(CartaoRepository cartaoRepository, ApplicationEventPublisher eventPublisher) {
		this.cartaoRepository = cartaoRepository;
		this.eventPublisher = eventPublisher;
	}

	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<Object> cadastrar(@PathVariable("idCartao") Long idCartao,
			@RequestBody @Valid AvisoViagemRequestDto avisoRequest, HttpServletRequest request) {
		Cartao cartao = this.cartaoRepository.findById(idCartao)
				.orElseThrow(() -> new CartaoNaoEncontradoException(idCartao));
		LOG.info("recebendo requisição de aviso de viagem para o cartão {}", idCartao);
		var avisoViagem = avisoRequest.toModel(request.getLocalAddr(), request.getHeader("User-Agent"), cartao);
		this.eventPublisher.publishEvent(new NotificacaoAvisoViagemSistemaBancario(avisoViagem, cartao));
		LOG.info("liberando aviso de viagem para notificaçao ao sistema bancário");
		return ResponseEntity.ok().build();
	}
}
