package br.com.zup.proposta.api.controller.cartao;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.dominio.exception.cartao.CartaoNaoEncontradoException;
import br.com.zup.proposta.dominio.exception.cartao.CartaoOperacaoInvalidaException;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.Bloqueio;
import br.com.zup.proposta.dominio.repository.CartaoRepository;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	private EntityManager manager;
	private CartaoRepository cartaoRepository;

	public CartaoController(EntityManager manager, CartaoRepository cartaoRepository) {
		this.manager = manager;
		this.cartaoRepository = cartaoRepository;
	}

	@PutMapping("/{id}/bloquear")
	@Transactional
	public ResponseEntity<Object> bloquear(@PathVariable("id") Long id, HttpServletRequest request) {
		Cartao cartao = this.cartaoRepository.findById(id).orElseThrow(() -> new CartaoNaoEncontradoException(id));
		if (!cartao.isAtivo())
			throw new CartaoOperacaoInvalidaException("Cartão de id " + id + " já está bloqueado");
		Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao);
		this.manager.merge(bloqueio);
		return ResponseEntity.ok().build();
	}
}