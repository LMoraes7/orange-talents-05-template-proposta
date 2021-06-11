package br.com.zup.proposta.dominio.modelo.cartao.carteira;

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
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String numeroDeIdentificacao;

	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private LocalDateTime associadaEm;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gateway gateway;

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
	private Cartao cartao;

	@Deprecated
	public Carteira() {
	}

	public Carteira(String numeroDeIdentificacao, Cartao cartao, Gateway gateway) {
		this.numeroDeIdentificacao = numeroDeIdentificacao;
		this.cartao = cartao;
		this.gateway = gateway;
	}

	@PrePersist
	public void addCarteira() {
		this.cartao.addCarteira(this);
	}
	
	public Long getId() {
		return id;
	}

	public Gateway getGateway() {
		return gateway;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
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
		Carteira other = (Carteira) obj;
		if (gateway != other.gateway)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carteira [id=" + id + ", numeroDeIdentificacao=" + numeroDeIdentificacao + ", associadaEm="
				+ associadaEm + ", cartao=" + cartao + ", gateway=" + gateway + "]";
	}
}