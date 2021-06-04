package br.com.zup.proposta.api.controller.proposta.evento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.api.controller.proposta.feign.ConsultarStatusPropostaFeign;
import br.com.zup.proposta.dominio.modelo.proposta.SolicitacaoAnaliseRequestDto;
import br.com.zup.proposta.dominio.modelo.proposta.SolicitacaoDeAtualizacaoDeStatusDaProposta;

@Component
public class ConsultarStatusProposta {

	private ConsultarStatusPropostaFeign feign;
	private ApplicationEventPublisher eventPublisher;
	private final Logger LOG = LoggerFactory.getLogger(ConsultarStatusProposta.class);

	public ConsultarStatusProposta(ConsultarStatusPropostaFeign feign, ApplicationEventPublisher eventPublisher) {
		this.feign = feign;
		this.eventPublisher = eventPublisher;
	}

	@Async
	@EventListener
	public void consultar(SolicitacaoAnaliseRequestDto propostaEvent) throws InterruptedException {
		LOG.info("solicitação para análise de proposta recebida com sucesso");
		var resultadoAnaliseResponseDto = this.feign.consultarStatus(propostaEvent);
		LOG.info("consultando sistema de análise de proposta ...");
		Thread.sleep(5000);
		LOG.info("resultado da análise retornado som sucesso");
		var solicitacaoAtt = new SolicitacaoDeAtualizacaoDeStatusDaProposta(
				resultadoAnaliseResponseDto.getResultadoSolicitacao().getStatus(), propostaEvent.getId());
		this.eventPublisher.publishEvent(solicitacaoAtt);
		LOG.info("proposta liberada para atualização de status");
	}
}
