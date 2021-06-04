package br.com.zup.proposta.dominio.modelo.proposta;

public class SolicitacaoAnaliseRequestDto {

	private String documento;
	private String nome;
	private Long id;

	public SolicitacaoAnaliseRequestDto(Proposta proposta) {
		documento = proposta.getDocumento();
		nome = proposta.getNome();
		id = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "SolicitacaoAnaliseRequestDto [documento=" + documento + ", nome=" + nome + ", id=" + id + "]";
	}
}
