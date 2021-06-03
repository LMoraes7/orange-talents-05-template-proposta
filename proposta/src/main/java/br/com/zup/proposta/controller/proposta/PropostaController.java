package br.com.zup.proposta.controller.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.controller.proposta.dto.request.PropostaRequestDto;
import br.com.zup.proposta.controller.proposta.dto.response.PropostaResponseDto;
import br.com.zup.proposta.dominio.exception.proposta.PropostaNaoEncontradaException;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.repository.PropostaRepository;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	private PropostaRepository propostaRepository;

	public PropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
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
		Proposta proposta = propostaRequest.toModel();
		this.propostaRepository.save(proposta);
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
