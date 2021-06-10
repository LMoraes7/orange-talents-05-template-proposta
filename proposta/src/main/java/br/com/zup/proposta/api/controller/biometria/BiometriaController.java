package br.com.zup.proposta.api.controller.biometria;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.api.controller.biometria.dto.request.BiometriaRequestDto;
import br.com.zup.proposta.api.controller.biometria.dto.response.BiometriaResponseDto;
import br.com.zup.proposta.dominio.exception.cartao.CartaoNaoEncontradoException;
import br.com.zup.proposta.dominio.exception.cartao.biometria.BiometriaNaoEncontradaException;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.biometria.Biometria;
import br.com.zup.proposta.dominio.repository.BiometriaRepository;
import br.com.zup.proposta.dominio.repository.CartaoRepository;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

	private CartaoRepository cartaoRepository;
	private BiometriaRepository biometriaRepository;
	private final Logger LOG = LoggerFactory.getLogger(BiometriaController.class);

	public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
		this.cartaoRepository = cartaoRepository;
		this.biometriaRepository = biometriaRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<BiometriaResponseDto> consultarPorId(@PathVariable("id") Long id) {
		LOG.info("recebendo requisição para busca de biometria {}", id);
		Biometria biometria = this.biometriaRepository.findById(id)
				.orElseThrow(() -> new BiometriaNaoEncontradaException(id));
		LOG.info("enviando resposta para a requição de busca de biometria {}", id);
		return ResponseEntity.ok(new BiometriaResponseDto(biometria));
	}

	@Transactional
	@PostMapping("/{idDoCartao}/cartao")
	public ResponseEntity<Object> cadastrar(@PathVariable("idDoCartao") Long idDoCartao,
			@RequestBody @Valid BiometriaRequestDto biometriaRequest, UriComponentsBuilder uriBuilder) {
		LOG.info("recebendo requisição para cadastro de biometria para o cartão {}", idDoCartao);
		Cartao cartao = this.cartaoRepository.findById(idDoCartao)
				.orElseThrow(() -> new CartaoNaoEncontradoException(idDoCartao));
		Biometria biometria = biometriaRequest.toModel(cartao);
		this.biometriaRepository.save(biometria);
		LOG.info("biometria salva para o cartão {}", idDoCartao);
		URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
		LOG.info("retornando a URL da nova biometria");
		return ResponseEntity.created(uri).build();
	}
}