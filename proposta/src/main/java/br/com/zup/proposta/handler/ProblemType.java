package br.com.zup.proposta.handler;

public enum ProblemType {
	
	NEGOCIO("Requisição inválida"),
	ENTIDADE_NAO_ENCONTRADA("Recurso não encontrado");
	
	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
