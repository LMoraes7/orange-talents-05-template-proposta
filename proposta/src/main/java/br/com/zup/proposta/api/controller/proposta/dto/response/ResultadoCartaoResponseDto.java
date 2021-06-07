package br.com.zup.proposta.api.controller.proposta.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;

public class ResultadoCartaoResponseDto {

	private String id; // número do cartão
	private LocalDateTime emitidoEm;
	private String titular;

	@JsonCreator
	public ResultadoCartaoResponseDto(String id, LocalDateTime emitidoEm, String titular) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public void setEmitidoEm(LocalDateTime emitidoEm) {
		this.emitidoEm = emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	@Override
	public String toString() {
		return "ResultadoCartaoResponseDto [id=" + id + ", emitidoEm=" + emitidoEm + ", titular=" + titular + "]";
	}

	public Cartao toModel(Proposta proposta) {
		return new Cartao(id, emitidoEm, titular, proposta);
	}

}
