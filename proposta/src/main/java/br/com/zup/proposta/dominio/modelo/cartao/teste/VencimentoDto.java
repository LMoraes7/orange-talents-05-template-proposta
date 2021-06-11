package br.com.zup.proposta.dominio.modelo.cartao.teste;

import java.time.LocalDateTime;

public class VencimentoDto {

	private String id;
	private Integer dia;
	private LocalDateTime dataCriacao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "VencimentoDto [id=" + id + ", dia=" + dia + ", dataCriacao=" + dataCriacao + "]";
	}
}
