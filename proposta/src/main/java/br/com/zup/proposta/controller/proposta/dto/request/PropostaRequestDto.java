package br.com.zup.proposta.controller.proposta.dto.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.proposta.dominio.modelo.proposta.Endereco;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.infraestrutura.validacao.anotacao.CPForCNPJ;
import br.com.zup.proposta.infraestrutura.validacao.anotacao.ValorUnico;

public class PropostaRequestDto {

	@NotBlank
	@CPForCNPJ
	@ValorUnico(campo = "documento", classe = Proposta.class)
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotNull
	@Valid
	private EnderecoRequestDto endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnderecoRequestDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRequestDto endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "PropostaRequestDto [documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}

	public Proposta toModel() {
		return new Proposta(this.documento, this.email,
				new Endereco(this.endereco.getLogradouro(), this.endereco.getNumero(), this.endereco.getComplemento()),
				this.nome, this.salario);
	}
}
