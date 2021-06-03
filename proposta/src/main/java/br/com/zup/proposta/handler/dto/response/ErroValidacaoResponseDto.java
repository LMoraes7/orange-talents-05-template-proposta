package br.com.zup.proposta.handler.dto.response;

import java.time.LocalDateTime;

public class ErroValidacaoResponseDto {

	private LocalDateTime instante;
	private String campo;
	private String mensagem;

	public ErroValidacaoResponseDto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
		this.instante = LocalDateTime.now();
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	@Override
	public String toString() {
		return "ErroValidacaoResponseDto [instante=" + instante + ", campo=" + campo + ", mensagem=" + mensagem + "]";
	}
}
