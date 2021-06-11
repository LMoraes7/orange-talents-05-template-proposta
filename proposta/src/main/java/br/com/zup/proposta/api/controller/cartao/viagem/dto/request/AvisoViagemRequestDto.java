package br.com.zup.proposta.api.controller.cartao.viagem.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.viagem.AvisoViagem;

public class AvisoViagemRequestDto {

	@NotBlank
	private String destino;

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate termino;

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDate getTermino() {
		return termino;
	}

	public void setTermino(LocalDate termino) {
		this.termino = termino;
	}

	public AvisoViagem toModel(String ipCliente, String userAgent, Cartao cartao) {
		return new AvisoViagem(this.destino, this.termino, ipCliente, userAgent, cartao);
	}

	@Override
	public String toString() {
		return "AvisoViagemRequestDto [destino=" + destino + ", termino=" + termino + "]";
	}
}
