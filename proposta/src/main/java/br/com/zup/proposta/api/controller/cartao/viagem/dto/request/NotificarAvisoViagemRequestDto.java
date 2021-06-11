package br.com.zup.proposta.api.controller.cartao.viagem.dto.request;

import java.time.LocalDate;

public class NotificarAvisoViagemRequestDto {
	
	private String destino;
	private LocalDate validoAte;

	public NotificarAvisoViagemRequestDto(String destino, LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	@Override
	public String toString() {
		return "NotificarAvisoViagemRequestDto [destino=" + destino + ", validoAte=" + validoAte + "]";
	}
}