package br.com.zup.proposta.api.controller.proposta.evento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.api.controller.proposta.dto.request.SolicitacaoDeAtualizacaoDeStatusDaPropostaRequest;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.modelo.proposta.StatusProposta;
import br.com.zup.proposta.dominio.repository.PropostaRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

@Component
public class AtualizarStatusPropostaEvent {

	private PropostaRepository propostaRepository;
	private final MeterRegistry meterRegistry;
	private final Logger LOG = LoggerFactory.getLogger(ConsultarStatusPropostaEvent.class);
	private Counter counterPropostasElegivel;
	private Counter counterPropostasNaoElegivel;

	public AtualizarStatusPropostaEvent(PropostaRepository propostaRepository, MeterRegistry meterRegistry) {
		this.propostaRepository = propostaRepository;
		this.meterRegistry = meterRegistry;
	}
	
	@PostConstruct
	public void initMetricas() {
		List<Tag> tags = new ArrayList<Tag>();
		this.counterPropostasElegivel = this.meterRegistry.counter("proposta_elegivel", tags);
		this.counterPropostasNaoElegivel = this.meterRegistry.counter("proposta_nao_elegivel", tags);
	}

	@Async
	@EventListener
	@Transactional
	public void atualizar(SolicitacaoDeAtualizacaoDeStatusDaPropostaRequest solicitacao) throws InterruptedException {
		Thread.sleep(2000);
		LOG.info("solicitação para atualização de proposta recebida com sucesso");
		Proposta proposta = this.propostaRepository.findById(solicitacao.getId()).get();
		proposta.atualizarStatus(solicitacao.getStatus());
		LOG.info("atualizando proposta ...");
		Thread.sleep(5000);
		this.propostaRepository.save(proposta);
		incrementarMetricas(solicitacao.getStatus());
		LOG.info("proposta atualizada com sucesso");
	}

	private void incrementarMetricas(StatusProposta statusProposta) {
		if(statusProposta.equals(StatusProposta.ELEGIVEL))
			this.counterPropostasElegivel.increment();
		else
			this.counterPropostasNaoElegivel.increment();
	}
}