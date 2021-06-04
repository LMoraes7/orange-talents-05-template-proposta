package br.com.zup.proposta.api.controller.proposta.dto.response;

public class ResultadoAnaliseResponseDto {

	private String documento;
	private String nome;
	private ResultadoSolicitacao resultadoSolicitacao;
	private String idProposta;

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public void setResultadoSolicitacao(ResultadoSolicitacao resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(String idProposta) {
		this.idProposta = idProposta;
	}

	@Override
	public String toString() {
		return "ResultadoAnaliseResponseDto [documento=" + documento + ", nome=" + nome + ", resultadoSolicitacao="
				+ resultadoSolicitacao + ", idProposta=" + idProposta + "]";
	}
}
