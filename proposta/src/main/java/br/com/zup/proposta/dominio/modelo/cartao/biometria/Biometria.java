package br.com.zup.proposta.dominio.modelo.cartao.biometria;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String biometria;

	@JoinColumn(nullable = false)
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
	private Cartao cartao;

	@Deprecated
	public Biometria() {
	}

	public Biometria(String biometria, Cartao cartao) {
		this.biometria = biometria;
		this.cartao = cartao;
	}
	
	@PrePersist
	public void addBiometriaNoCartao() {
		this.cartao.addBiometria(this);
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((biometria == null) ? 0 : biometria.hashCode());
		result = prime * result + ((cartao == null) ? 0 : cartao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Biometria other = (Biometria) obj;
		if (biometria == null) {
			if (other.biometria != null)
				return false;
		} else if (!biometria.equals(other.biometria))
			return false;
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
		return true;
	}

	@Override
	public String toString() {
		return "Biometria [id=" + id + ", biometria=" + biometria + ", cartao=" + cartao + "]";
	}
}