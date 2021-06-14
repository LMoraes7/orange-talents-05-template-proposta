package br.com.zup.proposta.api.controller.cartao.carteira;

import java.net.URI;

import javax.persistence.EntityManager;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.api.controller.cartao.carteira.dto.request.SolicitacaoInclusaoCarteiraRequestDto;
import br.com.zup.proposta.api.controller.cartao.carteira.dto.response.ResultadoCarteiraResponseDto;
import br.com.zup.proposta.api.controller.cartao.carteira.feign.CarteiraSistemaExternoFeign;
import br.com.zup.proposta.dominio.exception.cartao.CartaoNaoEncontradoException;
import br.com.zup.proposta.dominio.exception.cartao.CartaoOperacaoInvalidaException;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.carteira.Carteira;
import br.com.zup.proposta.dominio.modelo.cartao.carteira.Gateway;
import br.com.zup.proposta.dominio.repository.CartaoRepository;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {

	private CartaoRepository cartaoRepository;
	private CarteiraSistemaExternoFeign clientFeign;
	private EntityManager manager;

	public CarteiraController(CartaoRepository cartaoRepository, CarteiraSistemaExternoFeign clientFeign,
			EntityManager manager) {
		this.cartaoRepository = cartaoRepository;
		this.clientFeign = clientFeign;
		this.manager = manager;
	}

	@PostMapping("/{idDoCartao}/paypal")
	@Transactional
	public ResponseEntity<Object> cadastrarPaypal(@PathVariable("idDoCartao") Long idDoCartao,
			UriComponentsBuilder uriBuilder) {
		Gateway gateway = Gateway.PAYPAL;
		Cartao cartao = this.verificarSeCartaoExisteESeJaPossuiGatewayInformado(idDoCartao, gateway);
		var resultadoCarteiraResponseDto = this
				.consultarSistemasDeCartaoEVerificarSeFoiPossivelAdicionarGateway(gateway, cartao);
		Carteira carteira = this.instanciarESalvaCarteira(gateway, cartao, resultadoCarteiraResponseDto);
		return this.gerarAURLDoRecursoERetornarRespostaAoCliente(uriBuilder, carteira);
	}

	@PostMapping("/{idDoCartao}/samsung-pay")
	@Transactional
	public ResponseEntity<Object> cadastrarSamsungPay(@PathVariable("idDoCartao") Long idDoCartao,
			UriComponentsBuilder uriBuilder) {
		Gateway gateway = Gateway.SAMSUNG_PAY;
		Cartao cartao = this.verificarSeCartaoExisteESeJaPossuiGatewayInformado(idDoCartao, gateway);
		var resultadoCarteiraResponseDto = this
				.consultarSistemasDeCartaoEVerificarSeFoiPossivelAdicionarGateway(gateway, cartao);
		Carteira carteira = this.instanciarESalvaCarteira(gateway, cartao, resultadoCarteiraResponseDto);
		return this.gerarAURLDoRecursoERetornarRespostaAoCliente(uriBuilder, carteira);
	}

	private Cartao verificarSeCartaoExisteESeJaPossuiGatewayInformado(Long idDoCartao, Gateway gateway) {
		Cartao cartao = this.cartaoRepository.findByIdJoinPropostaAndCarteiras(idDoCartao)
				.orElseThrow(() -> new CartaoNaoEncontradoException(idDoCartao));
		if (cartao.jaPossuiGatewayAssociado(gateway))
			throw new CartaoOperacaoInvalidaException(
					"Cartão de id " + idDoCartao + "já está associado a carteira do " + gateway.toString());
		return cartao;
	}

	private ResultadoCarteiraResponseDto consultarSistemasDeCartaoEVerificarSeFoiPossivelAdicionarGateway(
			Gateway gateway, Cartao cartao) {
		var resultadoCarteiraResponseDto = this.clientFeign.consultar(cartao.getNumeroDoCartao(),
				new SolicitacaoInclusaoCarteiraRequestDto(cartao.getProposta().getEmail(), gateway.toString()));
		if (!resultadoCarteiraResponseDto.getResultado().isAprovada())
			throw new CartaoOperacaoInvalidaException("Não foi possível associar a carteira de pagamentos "
					+ gateway.toString() + "ao cartão de id " + cartao.getId() + ". Tente novamente mais tarde.");
		return resultadoCarteiraResponseDto;
	}

	@Transactional
	private Carteira instanciarESalvaCarteira(Gateway gateway, Cartao cartao,
			ResultadoCarteiraResponseDto resultadoCarteiraResponseDto) {
		Carteira carteira = new Carteira(resultadoCarteiraResponseDto.getId(), cartao, gateway);
		carteira = this.manager.merge(carteira);
		return carteira;
	}

	private ResponseEntity<Object> gerarAURLDoRecursoERetornarRespostaAoCliente(UriComponentsBuilder uriBuilder,
			Carteira carteira) {
		URI uri = uriBuilder.path("/api/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}