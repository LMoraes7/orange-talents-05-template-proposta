package br.com.zup.proposta.api.controller.cartao.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.api.controller.cartao.dto.request.AvisoBloqueioDeCartaoParaOsSistemas;
import br.com.zup.proposta.api.controller.cartao.dto.request.NotificarBloqueioDeCartaoParaOsSistemasRequest;
import br.com.zup.proposta.api.controller.cartao.feign.NotificarBloqueioDeCartaoFeign;

@Component
public class NotificarBloqueioDeCartaoEvent {

	private NotificarBloqueioDeCartaoFeign clientFeign;
	private ApplicationEventPublisher eventPublisher;
	private Logger LOG = LoggerFactory.getLogger(NotificarBloqueioDeCartaoEvent.class);

	public NotificarBloqueioDeCartaoEvent(NotificarBloqueioDeCartaoFeign clientFeign,
			ApplicationEventPublisher eventPublisher) {
		this.clientFeign = clientFeign;
		this.eventPublisher = eventPublisher;
	}

	@Async
	@EventListener
	public void notificar(AvisoBloqueioDeCartaoParaOsSistemas aviso) throws InterruptedException {
		Thread.sleep(2000);
		LOG.info("recebendo requisição para notificar sistemas externos para bloquear o cartão {}",
				aviso.getCartao().getId());
		var notificarBloqueioRequest = new NotificarBloqueioDeCartaoParaOsSistemasRequest(
				aviso.getSistemaResponsavel());
		LOG.info("notificando sistema externo...");
		var notificarBloqueioResponse = this.clientFeign.notificar(aviso.getCartao().getNumeroDoCartao(),
				notificarBloqueioRequest);
		this.eventPublisher.publishEvent(new SolicitacaoParaAtualizacaoDeStatusDoCartao(aviso.getCartao(),
				aviso.getBloqueio(), notificarBloqueioResponse.getResultado()));
		LOG.info("requisição de bloqueio liberada para atualização de status do cartão {}", aviso.getCartao().getId());
	}
}
