package br.com.zup.proposta.dominio.modelo.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Status status;

	@Deprecated
	public Proposta() {
	}

	public Proposta(String documento, String email, Endereco endereco, String nome, BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.endereco = endereco;
		this.nome = nome;
		this.salario = salario;
		this.status = Status.EM_ANALISE;
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
	
	public Status getStatus() {
		return status;
	}

	public void atualizarStatus(Status status) {
		if (!this.status.equals(Status.EM_ANALISE))
			throw new IllegalArgumentException("A proposta referenciada já foi analisada pelo sistema.");
		this.status = status;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", salario="
				+ salario + ", endereco=" + endereco + ", status=" + status + "]";
	}
}
