package br.com.zup.proposta.dominio.modelo.cartao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.zup.proposta.dominio.modelo.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String numeroDoCartao;
	
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime emissao;
	
	@Column(nullable = false)
	private String titular;

	@JoinColumn(nullable = false, unique = true)
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Proposta proposta;

	@Deprecated
	public Cartao() {
	}

	public Cartao(String numeroDoCartao, LocalDateTime emissao, String titular, Proposta proposta) {
		this.numeroDoCartao = numeroDoCartao;
		this.emissao = emissao;
		this.titular = titular;
		this.proposta = proposta;
		this.proposta.addCartao(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroDoCartao == null) ? 0 : numeroDoCartao.hashCode());
		result = prime * result + ((proposta == null) ? 0 : proposta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroDoCartao == null) {
			if (other.numeroDoCartao != null)
				return false;
		} else if (!numeroDoCartao.equals(other.numeroDoCartao))
			return false;
		if (proposta == null) {
			if (other.proposta != null)
				return false;
		} else if (!proposta.equals(other.proposta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numeroDoCartao=" + numeroDoCartao + ", emissao=" + emissao + ", titular="
				+ titular + ", proposta=" + proposta + "]";
	}
}