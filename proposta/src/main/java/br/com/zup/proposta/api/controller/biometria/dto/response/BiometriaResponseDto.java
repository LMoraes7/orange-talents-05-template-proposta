package br.com.zup.proposta.api.controller.biometria.dto.response;

import br.com.zup.proposta.dominio.modelo.cartao.biometria.Biometria;

public class BiometriaResponseDto {

	private Long id;

	public BiometriaResponseDto(Biometria biometria) {
		id = biometria.getId();
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "BiometriaResponseDto [id=" + id + "]";
	}

}
