package br.com.zup.proposta.api.controller.cartao.viagem.evento;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CadastrarAvisoViagemEvent {

	private EntityManager manager;
	private Logger LOG = LoggerFactory.getLogger(CadastrarAvisoViagemEvent.class);

	public CadastrarAvisoViagemEvent(EntityManager manager) {
		this.manager = manager;
	}
	
	@Async
	@EventListener
	@Transactional
	public void cadastrar(SolicitacaoParaCadastroDeAvisoViagem solicitacao) throws InterruptedException {
		Thread.sleep(2000);
		LOG.info("recebendo requisição para realizar o possível cadastro do aviso de viagem");
		if(solicitacao.getStatusAvisoViagem().isAprovada()) {
			this.manager.persist(solicitacao.getAvisoViagem());
			LOG.info("salvando solicitação para aviso de viagem");
			return;
		}
		LOG.info("solicitação para aviso de viagem foi negado");
	}
}
