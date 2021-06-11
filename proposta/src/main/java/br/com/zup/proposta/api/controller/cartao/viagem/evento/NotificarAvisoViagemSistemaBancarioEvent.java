package br.com.zup.proposta.api.controller.cartao.viagem.evento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.api.controller.cartao.viagem.dto.request.NotificarAvisoViagemRequestDto;
import br.com.zup.proposta.api.controller.cartao.viagem.feign.NotificarAvisoViagemSistemaBancarioFeign;

@Component
public class NotificarAvisoViagemSistemaBancarioEvent {

	private NotificarAvisoViagemSistemaBancarioFeign clientFeign;
	private ApplicationEventPublisher eventPublisher;
	private Logger LOG = LoggerFactory.getLogger(NotificarAvisoViagemSistemaBancarioEvent.class);

	public NotificarAvisoViagemSistemaBancarioEvent(NotificarAvisoViagemSistemaBancarioFeign clientFeign,
			ApplicationEventPublisher eventPublisher) {
		this.clientFeign = clientFeign;
		this.eventPublisher = eventPublisher;
	}

	@Async
	@EventListener
	public void notificar(NotificacaoAvisoViagemSistemaBancario notificacao) throws InterruptedException {
		Thread.sleep(2000);
		LOG.info("recebendo requisição para notificar sistema bancarário de aviso de viagem");
		var avisoViagemRequestDto = new NotificarAvisoViagemRequestDto(notificacao.getAvisoViagem().getDestino(),
				notificacao.getAvisoViagem().getTermino());
		var resultadoAvisoViagemResponseDto = this.clientFeign.notificar(notificacao.getCartao().getNumeroDoCartao(),
				avisoViagemRequestDto);
		LOG.info("notificando sistema bancário...");
		Thread.sleep(2000);
		this.eventPublisher.publishEvent(new SolicitacaoParaCadastroDeAvisoViagem(notificacao.getAvisoViagem(),
				resultadoAvisoViagemResponseDto.getResultado()));
		LOG.info("liberando aviso de viagem para possível cadastro");
	}
}
