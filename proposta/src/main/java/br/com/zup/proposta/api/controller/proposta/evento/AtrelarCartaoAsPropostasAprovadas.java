package br.com.zup.proposta.api.controller.proposta.evento;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoCartaoResponseDto;
import br.com.zup.proposta.api.controller.proposta.feign.AtrelarCartaoAsPropostasAprovadasFeign;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.modelo.proposta.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.dominio.modelo.proposta.Status;
import br.com.zup.proposta.dominio.repository.PropostaRepository;

@Component
public class AtrelarCartaoAsPropostasAprovadas {

	private PropostaRepository propostaRepository;
	private AtrelarCartaoAsPropostasAprovadasFeign clientFeign;
	private final Logger LOG = LoggerFactory.getLogger(AtrelarCartaoAsPropostasAprovadas.class);

	public AtrelarCartaoAsPropostasAprovadas(PropostaRepository propostaRepository,
			AtrelarCartaoAsPropostasAprovadasFeign clientFeign) {
		this.propostaRepository = propostaRepository;
		this.clientFeign = clientFeign;
	}

	@Scheduled(initialDelayString = "${delay-inicial.executa-busca-sistema-cartoes}", fixedDelayString = "${intervalo.executa-busca-sistema-cartoes}")
	public void consultarSistemasDeCartoes() {
		LOG.info("buscando propostas elegíveis que não possuem cartão cadastrado");
		Set<Proposta> propostas = this.propostaRepository.findByStatusAndCartaoIsNull(Status.ELEGIVEL);
		LOG.info("foram buscadas {} propostas aprovadas e que não possuem cartão cadastrado", propostas.size());
		for (Proposta proposta : propostas) {
			LOG.info("realizando requisição para sistema de gerador de cartões para a proposta do titular {}",
					proposta.getNome());
			var resultadoCartaoResponse = this.clientFeign.atrelarCartao(new SolicitacaoAnaliseRequestDto(proposta));
			this.atrelarCartaoAProposta(proposta, resultadoCartaoResponse);
		}
	}

	@Transactional
	private void atrelarCartaoAProposta(Proposta proposta, ResultadoCartaoResponseDto resultadoCartaoResponse) {
		if (resultadoCartaoResponse == null)
			return;
		LOG.info("atrelando cartão a proposta do titular {}", proposta.getNome());
		resultadoCartaoResponse.toModel(proposta);
		this.propostaRepository.save(proposta);
		LOG.info("salvando proposta do titular {}", proposta.getNome());
	}
}