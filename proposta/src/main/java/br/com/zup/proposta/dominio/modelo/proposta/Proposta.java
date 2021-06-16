package br.com.zup.proposta.dominio.modelo.proposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String documento;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private BigDecimal salario;

	@Embedded
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private StatusProposta status;

	@OneToOne(mappedBy = "proposta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Cartao cartao;

	@Deprecated
	public Proposta() {
	}

	public Proposta(DocumentoLimpo documento, String email, Endereco endereco, String nome, BigDecimal salario) {
		this.documento = documento.getDocumento();
		this.email = email;
		this.endereco = endereco;
		this.nome = nome;
		this.salario = salario;
		this.status = StatusProposta.EM_ANALISE;
	}

	public Long getId() {
		return this.id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void atualizarStatus(StatusProposta status) {
		if (!this.status.equals(StatusProposta.EM_ANALISE))
			throw new IllegalArgumentException("A proposta referenciada já foi analisada pelo sistema.");
		this.status = status;
	}

	public void addCartao(Cartao cartao) {
		if (this.cartao != null)
			throw new IllegalArgumentException("A proposta referenciada já está atrelada a um cartão.");
		this.cartao = cartao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Proposta other = (Proposta) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", salario="
				+ salario + ", endereco=" + endereco + ", status=" + status + "]";
	}
}
