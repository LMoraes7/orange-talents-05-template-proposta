package br.com.zup.proposta.dominio.modelo.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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

	@Deprecated
	public Proposta() {
	}

	public Proposta(String documento, String email, Endereco endereco, String nome, BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.endereco = endereco;
		this.nome = nome;
		this.salario = salario;
	}
	
	public Long getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}
}
