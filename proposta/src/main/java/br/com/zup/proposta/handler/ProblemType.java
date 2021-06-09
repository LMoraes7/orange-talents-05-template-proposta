package br.com.zup.proposta.handler;

public enum ProblemType {
	
	OPERACAO_INVALIDA("Manipulação de recurso é inválida"),
	NEGOCIO("Requisição é inválida"),
	ENTIDADE_NAO_ENCONTRADA("Recurso não encontrado");
	
	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
