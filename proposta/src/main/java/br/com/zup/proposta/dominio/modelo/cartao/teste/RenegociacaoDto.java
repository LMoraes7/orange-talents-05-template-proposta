package br.com.zup.proposta.dominio.modelo.cartao.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoDto {

	private String id;
	private Integer quantidade;
	private BigDecimal valor;
	private LocalDateTime dataDeCriacao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	@Override
	public String toString() {
		return "RenegociacaoDto [id=" + id + ", quantidade=" + quantidade + ", valor=" + valor + ", dataDeCriacao="
				+ dataDeCriacao + "]";
	}
}
