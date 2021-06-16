package br.com.zup.proposta.api.controller.proposta;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.api.controller.proposta.dto.request.PropostaRequestDto;
import br.com.zup.proposta.api.controller.proposta.dto.request.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.api.controller.proposta.dto.response.PropostaResponseDto;
import br.com.zup.proposta.dominio.exception.proposta.PropostaNaoEncontradaException;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.repository.PropostaRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

	@Value("${criptografia.chave-secreta}")
	private String chaveSecreta;
	@Value("${criptografia.salt}")
	private String salt;
	private PropostaRepository propostaRepository;
	private ApplicationEventPublisher eventPublisher;
	private Counter counterPropostasCriadas;
	private final MeterRegistry meterRegistry;
	private final Logger LOG = LoggerFactory.getLogger(PropostaController.class);
	private final Tracer tracer;

	public PropostaController(PropostaRepository propostaRepository, ApplicationEventPublisher eventPublisher,
			MeterRegistry meterRegistry, Tracer tracer) {
		this.propostaRepository = propostaRepository;
		this.eventPublisher = eventPublisher;
		this.meterRegistry = meterRegistry;
		this.tracer = tracer;
	}

	@PostConstruct
	public void initMetricas() {
		Collection<Tag> tags = new ArrayList<Tag>();
//		tags.add(Tag.of("emissora", "Mastercard"));
//	    tags.add(Tag.of("banco", "Itaú"));
//		No exemplo acima eu estou definindo a criação das LABEL's
//		this.counterPropostasCriadasParaOBancoItauComABandeiraMasterCard = this.meterRegistry.counter("propostas_criadas", tags);
		this.counterPropostasCriadas = this.meterRegistry.counter("propostas_criadas", tags);
//		Devemos passar o nome da métrica(NAME) e as LABEL's que serão as tags
//		propostas_criadas é o nome da MÉTRICA
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponseDto> consultarProposta(@PathVariable("id") Long id) {
		LOG.info("recebendo requisição para busca de proposta {}", id);
		Proposta proposta = this.propostaRepository.findById(id)
				.orElseThrow(() -> new PropostaNaoEncontradaException(id));
		LOG.info("retornando resultado da busca de proposta {}", id);
		return ResponseEntity.ok(new PropostaResponseDto(proposta));
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Object> cadastrarProposta(@RequestBody @Valid PropostaRequestDto propostaRequest,
			UriComponentsBuilder uriBuilder) {
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("user.email", propostaRequest.getEmail());
		activeSpan.setBaggageItem("user.email", propostaRequest.getEmail());
		activeSpan.log("Criação de proposta para o e-mail " + propostaRequest.getEmail());
		LOG.info("recebendo requisição de uma nova proposta");
		Proposta proposta = propostaRequest.toModel(this.chaveSecreta, this.salt);
		this.propostaRepository.save(proposta);
		this.counterPropostasCriadas.increment();
		LOG.info("salvando a nova proposta");
		this.eventPublisher.publishEvent(new SolicitacaoAnaliseRequestDto(proposta));
		LOG.info("proposta liberada para análise");
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		LOG.info("retornando URI da nova proposta");
		return ResponseEntity.created(uri).build();
	}
}