package br.com.zup.proposta.api.controller.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.api.controller.proposta.dto.request.PropostaRequestDto;
import br.com.zup.proposta.api.controller.proposta.dto.response.PropostaResponseDto;
import br.com.zup.proposta.dominio.exception.proposta.PropostaNaoEncontradaException;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.modelo.proposta.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.dominio.repository.PropostaRepository;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	private PropostaRepository propostaRepository;
	private ApplicationEventPublisher eventPublisher;
	private final Logger LOG = LoggerFactory.getLogger(PropostaController.class);

	public PropostaController(PropostaRepository propostaRepository, ApplicationEventPublisher eventPublisher) {
		this.propostaRepository = propostaRepository;
		this.eventPublisher = eventPublisher;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponseDto> consultarProposta(@PathVariable("id") Long id) {
		Proposta proposta = this.propostaRepository.findById(id)
				.orElseThrow(() -> new PropostaNaoEncontradaException(id));
		return ResponseEntity.ok(new PropostaResponseDto(proposta));
	}

	@PostMapping
	public ResponseEntity<Object> cadastrarProposta(@RequestBody @Valid PropostaRequestDto propostaRequest,
			UriComponentsBuilder uriBuilder) {
		LOG.info("recebendo requisição de uma nova proposta");
		Proposta proposta = propostaRequest.toModel();
		this.propostaRepository.save(proposta);
		LOG.info("salvando a nova proposta");
		this.eventPublisher.publishEvent(new SolicitacaoAnaliseRequestDto(proposta));
		LOG.info("proposta liberada para análise");
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		LOG.info("retornando URI da nova proposta");
		return ResponseEntity.created(uri).build();
	}
}
