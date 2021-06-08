package br.com.zup.proposta.api.controller.proposta.evento;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.api.controller.proposta.dto.request.SolicitacaoDeAtualizacaoDeStatusDaPropostaRequest;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.repository.PropostaRepository;

@Component
public class AtualizarStatusProposta {

	private PropostaRepository propostaRepository;
	private final Logger LOG = LoggerFactory.getLogger(ConsultarStatusProposta.class);

	public AtualizarStatusProposta(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@Async
	@EventListener
	@Transactional
	public void atualizar(SolicitacaoDeAtualizacaoDeStatusDaPropostaRequest solicitacao) throws InterruptedException {
		LOG.info("solicitação para atualização de proposta recebida com sucesso");
		Proposta proposta = this.propostaRepository.findById(solicitacao.getId()).get();
		proposta.atualizarStatus(solicitacao.getStatus());
		LOG.info("atualizando proposta ...");
		Thread.sleep(5000);
		this.propostaRepository.save(proposta);
		LOG.info("proposta atualizada com sucesso");
	}
}