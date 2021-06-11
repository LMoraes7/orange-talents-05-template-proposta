package br.com.zup.proposta.dominio.modelo.cartao.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String destino;

	@Column(nullable = false, columnDefinition = "date")
	private LocalDate termino;

	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private LocalDateTime instanteDoAviso;

	@Column(nullable = false)
	private String ipCliente;

	@Column(nullable = false)
	private String userAgent;

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
	private Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(String destino, LocalDate termino, String ipCliente, String userAgent, Cartao cartao) {
		this.destino = destino;
		this.termino = termino;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartao == null) ? 0 : cartao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instanteDoAviso == null) ? 0 : instanteDoAviso.hashCode());
		result = prime * result + ((ipCliente == null) ? 0 : ipCliente.hashCode());
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
		AvisoViagem other = (AvisoViagem) obj;
		if (cartao == null) {
			if (other.cartao != null)
				return false;
		} else if (!cartao.equals(other.cartao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instanteDoAviso == null) {
			if (other.instanteDoAviso != null)
				return false;
		} else if (!instanteDoAviso.equals(other.instanteDoAviso))
			return false;
		if (ipCliente == null) {
			if (other.ipCliente != null)
				return false;
		} else if (!ipCliente.equals(other.ipCliente))
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
		return "AvisoViagem [id=" + id + ", destino=" + destino + ", termino=" + termino + ", instanteDoAviso="
				+ instanteDoAviso + ", ipCliente=" + ipCliente + ", userAgent=" + userAgent + ", cartao=" + cartao
				+ "]";
	}

}
