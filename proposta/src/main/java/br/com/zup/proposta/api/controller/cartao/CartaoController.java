package br.com.zup.proposta.api.controller.cartao;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.api.controller.cartao.dto.request.AvisoBloqueioDeCartaoParaOsSistemas;
import br.com.zup.proposta.dominio.exception.cartao.CartaoNaoEncontradoException;
import br.com.zup.proposta.dominio.exception.cartao.CartaoOperacaoInvalidaException;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.Bloqueio;
import br.com.zup.proposta.dominio.repository.CartaoRepository;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {

	private EntityManager manager;
	private CartaoRepository cartaoRepository;
	private ApplicationEventPublisher eventPublisher;
	private final Logger LOG = LoggerFactory.getLogger(CartaoController.class);

	public CartaoController(EntityManager manager, CartaoRepository cartaoRepository,
			ApplicationEventPublisher eventPublisher) {
		this.manager = manager;
		this.cartaoRepository = cartaoRepository;
		this.eventPublisher = eventPublisher;
	}

	@PutMapping("/{id}/bloquear")
	@Transactional
	public ResponseEntity<Object> bloquear(@PathVariable("id") Long id, HttpServletRequest request) {
		LOG.info("recebendo requisição para bloquear cartão {}", id);
		Cartao cartao = this.cartaoRepository.findById(id).orElseThrow(() -> new CartaoNaoEncontradoException(id));
		if (!cartao.isAtivo())
			throw new CartaoOperacaoInvalidaException("Cartão de id " + id + " já está bloqueado");
		Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao);
		bloqueio = this.manager.merge(bloqueio);
		LOG.info("salvando tentativa de bloqueio para o cartão {}", id);
		this.eventPublisher
				.publishEvent(new AvisoBloqueioDeCartaoParaOsSistemas("API-propostas", cartao, bloqueio));
		LOG.info("liberando tentativa de bloqueio para o cartão {} para sistemas externos de bloqueo de cartão", id);
		return ResponseEntity.ok().build();
	}
}