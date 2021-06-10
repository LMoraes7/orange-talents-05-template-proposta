package br.com.zup.proposta.api.controller.cartao.event;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.Bloqueio;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.StatusBloqueio;

@Component
public class AtualizarStatusDoCartaoEvent {

	private EntityManager manager;
	private final Logger LOG = LoggerFactory.getLogger(AtualizarStatusDoCartaoEvent.class);

	public AtualizarStatusDoCartaoEvent(EntityManager manager) {
		this.manager = manager;
	}

	@Async
	@EventListener
	@Transactional
	public void atualizar(SolicitacaoParaAtualizacaoDeStatusDoCartao solicitacao) throws InterruptedException {
		Thread.sleep(2000);
		LOG.info("recebendo requisição para efetuar a atualização de status do cartão {}",
				solicitacao.getCartao().getId());
		StatusBloqueio statusBloqueio = solicitacao.getResultado().getStatus();
		Bloqueio bloqueio = solicitacao.getBloqueio();
		bloqueio.atualizarStatus(statusBloqueio);
		LOG.info("atualizando status de bloqueio do cartão {}", solicitacao.getCartao().getId());
		this.manager.merge(bloqueio);
		LOG.info("atualização de status de bloqueio do cartão {} foi concluída com sucesso",
				solicitacao.getCartao().getId());
	}
}
