package br.com.zup.proposta.handler.dto.response;

import java.time.LocalDateTime;

public class ErroResponseDto {

	private LocalDateTime instante;
	private String message;

	public ErroResponseDto(String message) {
		this.message = message;
		this.instante = LocalDateTime.now();
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ErroResponseDto [instante=" + instante + ", message=" + message + "]";
	}
}