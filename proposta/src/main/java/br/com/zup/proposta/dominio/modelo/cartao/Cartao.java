package br.com.zup.proposta.dominio.modelo.cartao;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import br.com.zup.proposta.dominio.modelo.cartao.biometria.Biometria;
import br.com.zup.proposta.dominio.modelo.cartao.bloqueio.Bloqueio;
import br.com.zup.proposta.dominio.modelo.cartao.carteira.Carteira;
import br.com.zup.proposta.dominio.modelo.cartao.carteira.Gateway;
import br.com.zup.proposta.dominio.modelo.cartao.viagem.AvisoViagem;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String numeroDoCartao;

	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime emissao;

	@Column(nullable = false)
	private String titular;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCartao status;

	@JoinColumn(nullable = false, unique = true)
	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	private Proposta proposta;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cartao")
	private Set<Carteira> carteiras = new HashSet<Carteira>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cartao")
	private Set<Biometria> biometrias = new HashSet<Biometria>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cartao")
	private Set<Bloqueio> bloqueios = new HashSet<Bloqueio>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cartao")
	private Set<AvisoViagem> avisosViagens = new HashSet<AvisoViagem>();

	@Deprecated
	public Cartao() {
	}

	public Cartao(String numeroDoCartao, LocalDateTime emissao, String titular, Proposta proposta) {
		this.numeroDoCartao = numeroDoCartao;
		this.emissao = emissao;
		this.titular = titular;
		this.proposta = proposta;
		this.status = StatusCartao.ATIVO;
	}
	
	@PrePersist
	public void addCartaoAProposta() {
		this.proposta.addCartao(this);
	}

	public Long getId() {
		return id;
	}

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void addBiometria(Biometria biometria) {
		this.biometrias.add(biometria);
	}

	public void addAvisoViagem(AvisoViagem avisoViagem) {
		this.avisosViagens.add(avisoViagem);
	}
	
	public void addCarteira(Carteira carteira) {
		if (this.carteiras.contains(carteira))
			throw new IllegalArgumentException("Cartão já está associado a essa Carteira.");
		this.carteiras.add(carteira);
	}

	public boolean isAtivo() {
		return this.status.equals(StatusCartao.ATIVO);
	}

	public void cadastrarTentativaDeBloqueio(Bloqueio bloqueio) {
		if (!this.isAtivo())
			throw new IllegalArgumentException("Cartão já está bloqueado.");
		this.bloqueios.add(bloqueio);
	}

	public void atualizarStatus(StatusCartao status) {
		this.status = status;
	}

	public boolean jaPossuiGatewayAssociado(Gateway paypal) {
		Set<Carteira> collect = this.carteiras.stream().filter(carteira -> carteira.getGateway().equals(paypal))
				.collect(Collectors.toSet());
		if (!collect.isEmpty())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroDoCartao == null) ? 0 : numeroDoCartao.hashCode());
		result = prime * result + ((proposta == null) ? 0 : proposta.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
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
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}
}