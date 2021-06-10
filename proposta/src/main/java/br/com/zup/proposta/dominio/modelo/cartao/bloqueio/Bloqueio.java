package br.com.zup.proposta.dominio.modelo.cartao.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String enderecoIp;

	@Column(nullable = false)
	private String userAgent;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime instanteBloqueio;

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	private Cartao cartao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusBloqueio status;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(String enderecoIp, String userAgent, Cartao cartao) {
		this.enderecoIp = enderecoIp;
		this.userAgent = userAgent;
		this.cartao = cartao;
		this.status = StatusBloqueio.EM_ANALISE;
	}

	@PrePersist
	public void bloquearCartao() {
		this.cartao.cadastrarTentativaDeBloqueio(this);
	}
	
	public void atualizarStatus(StatusBloqueio status) {
		if(!this.status.equals(StatusBloqueio.EM_ANALISE))
			throw new IllegalArgumentException("Status desse bloqueio não pode ser alterado.");
		this.status = status;
		this.cartao.atualizarStatus(status.getStatusCartao());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartao == null) ? 0 : cartao.hashCode());
		result = prime * result + ((enderecoIp == null) ? 0 : enderecoIp.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instanteBloqueio == null) ? 0 : instanteBloqueio.hashCode());
		result = prime * result + ((userAgent == null) ? 0 : userAgent.hashCode());
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
		Bloqueio other = (Bloqueio) obj;
		if (cartao == null) {
			if (other.cartao != null)
				return false;
		} else if (!cartao.equals(other.cartao))
			return false;
		if (enderecoIp == null) {
			if (other.enderecoIp != null)
				return false;
		} else if (!enderecoIp.equals(other.enderecoIp))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instanteBloqueio == null) {
			if (other.instanteBloqueio != null)
				return false;
		} else if (!instanteBloqueio.equals(other.instanteBloqueio))
			return false;
		if (userAgent == null) {
			if (other.userAgent != null)
				return false;
		} else if (!userAgent.equals(other.userAgent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bloqueio [id=" + id + ", enderecoIp=" + enderecoIp + ", userAgent=" + userAgent + ", instanteBloqueio="
				+ instanteBloqueio + ", cartao=" + cartao + "]";
	}

}