package br.com.zup.proposta.dominio.modelo.cartao.teste;

import java.time.LocalDate;

public class AvisoViagemDto {

	private LocalDate validoAte;
	private String destino;

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public void setValidoAte(LocalDate validoAte) {
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "AvisoViagemDto [validoAte=" + validoAte + ", destino=" + destino + "]";
	}

}
