package br.com.zup.proposta.dominio.modelo.cartao.teste;

import java.math.BigDecimal;

public class ParcelaDto {

	private String id;
	private Integer quantidade;
	private BigDecimal valor;

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

	@Override
	public String toString() {
		return "ParcelaDto [id=" + id + ", quantidade=" + quantidade + ", valor=" + valor + "]";
	}
}
