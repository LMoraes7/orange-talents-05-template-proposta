package br.com.zup.proposta.api.controller.biometria.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.zup.proposta.dominio.modelo.biometria.Biometria;
import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.infraestrutura.validacao.anotacao.Base64;
import br.com.zup.proposta.infraestrutura.validacao.anotacao.ValorUnico;

public class BiometriaRequestDto {

	@NotBlank
	@Base64
	@ValorUnico(campo = "biometria", classe = Biometria.class)
	private String biometria;

	public String getBiometria() {
		return biometria;
	}

	public void setBiometria(String biometria) {
		this.biometria = biometria;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(biometria, cartao);
	}
}