package br.com.zup.proposta.dominio.modelo.cartao.teste;

import java.time.LocalDateTime;

public class CarteiraDto {

	private String id;
	private String email;
	private LocalDateTime associadaEm;
	private String emissor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public void setAssociadaEm(LocalDateTime associadaEm) {
		this.associadaEm = associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	@Override
	public String toString() {
		return "CarteiraDto [id=" + id + ", email=" + email + ", associadaEm=" + associadaEm + ", emissor=" + emissor
				+ "]";
	}

}
