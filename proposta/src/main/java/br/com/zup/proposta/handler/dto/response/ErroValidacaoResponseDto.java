package br.com.zup.proposta.handler.dto.response;

public class ErroValidacaoResponseDto {

	private String campo;
	private String mensagem;

	public ErroValidacaoResponseDto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	@Override
	public String toString() {
		return "ErroValidacaoResponseDto [campo=" + campo + ", mensagem=" + mensagem + "]";
	}

}
