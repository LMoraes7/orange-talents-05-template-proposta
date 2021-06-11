package br.com.zup.proposta.api.controller.cartao.viagem;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.api.controller.cartao.viagem.dto.request.AvisoViagemRequestDto;
import br.com.zup.proposta.dominio.exception.cartao.CartaoNaoEncontradoException;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.repository.CartaoRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

@RestController
@RequestMapping("/api/viagens")
public class ViagemController {

	private CartaoRepository cartaoRepository;
	private EntityManager manager;
	private Counter counterAvisoViagensTotal;
	private final MeterRegistry meterRegister;
	private final Logger LOG = LoggerFactory.getLogger(ViagemController.class);

	public ViagemController(CartaoRepository cartaoRepository, MeterRegistry meterRegister, EntityManager manager) {
		this.cartaoRepository = cartaoRepository;
		this.meterRegister = meterRegister;
		this.manager = manager;
	}

	@PostConstruct
	public void initMetricas() {
		Collection<Tag> tags = new ArrayList<Tag>();
		this.counterAvisoViagensTotal = this.meterRegister.counter("aviso_viagens_total", tags);
	}

	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<Object> cadastrar(@PathVariable("idCartao") Long idCartao,
			@RequestBody @Valid AvisoViagemRequestDto avisoRequest, HttpServletRequest request) {
		Cartao cartao = this.cartaoRepository.findById(idCartao)
				.orElseThrow(() -> new CartaoNaoEncontradoException(idCartao));
		LOG.info("recebendo requisição de aviso de viagem para o cartão {}", idCartao);
		var avisoViagem = avisoRequest.toModel(request.getLocalAddr(), request.getHeader("User-Agent"), cartao);
		this.manager.merge(avisoViagem);
		this.counterAvisoViagensTotal.increment();
		LOG.info("aviso de viagem para o cartão {} foi cadastrado com sucesso", idCartao);
		return ResponseEntity.ok().build();
	}
}
