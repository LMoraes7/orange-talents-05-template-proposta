package br.com.zup.proposta.api.controller.proposta.evento;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.proposta.api.controller.proposta.dto.request.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.api.controller.proposta.dto.response.ResultadoCartaoResponseDto;
import br.com.zup.proposta.api.controller.proposta.feign.AtrelarCartaoAsPropostasAprovadasFeign;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.modelo.proposta.StatusProposta;

@Component
public class AtrelarCartaoAsPropostasAprovadas {

	private EntityManager manager;
	private AtrelarCartaoAsPropostasAprovadasFeign clientFeign;
	private final Logger LOG = LoggerFactory.getLogger(AtrelarCartaoAsPropostasAprovadas.class);

	public AtrelarCartaoAsPropostasAprovadas(EntityManager manager,
			AtrelarCartaoAsPropostasAprovadasFeign clientFeign) {
		this.manager = manager;
		this.clientFeign = clientFeign;
	}

	@Transactional
	@Scheduled(initialDelayString = "${delay-inicial.executa-busca-sistema-cartoes}", fixedDelayString = "${intervalo.executa-busca-sistema-cartoes}")
	public void consultarSistemasDeCartoes() throws InterruptedException {
		LOG.info("buscando propostas elegíveis que não possuem cartão cadastrado");
		String jpql = "SELECT p FROM Proposta p LEFT JOIN FETCH p.cartao c WHERE (p.status = :status AND c.id is null)";
		List<Proposta> propostas = this.manager.createQuery(jpql, Proposta.class)
				.setParameter("status", StatusProposta.ELEGIVEL).getResultList();
		LOG.info("foram buscadas {} propostas aprovadas e que não possuem cartão cadastrado", propostas.size());
		for (Proposta proposta : propostas)
			this.realizarRequisicaoAoSistemaExternoDeCartoes(proposta);
	}

	@Transactional
	private void realizarRequisicaoAoSistemaExternoDeCartoes(Proposta proposta) throws InterruptedException {
		LOG.info("realizando requisição para sistema de gerador de cartões para a proposta do titular {}",
				proposta.getNome());
		var resultadoCartaoResponse = this.clientFeign.atrelarCartao(new SolicitacaoAnaliseRequestDto(proposta));
		this.atrelarCartaoAProposta(proposta, resultadoCartaoResponse);
	}

	@Transactional
	private void atrelarCartaoAProposta(Proposta proposta, ResultadoCartaoResponseDto resultadoCartaoResponse) {
		if (resultadoCartaoResponse == null)
			return;
		LOG.info("atrelando cartão a proposta do titular {}", proposta.getNome());
		Cartao cartao = resultadoCartaoResponse.toModel(proposta);
		this.manager.merge(cartao);
		LOG.info("salvando proposta do titular {}", proposta.getNome());
	}
}