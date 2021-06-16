package br.com.zup.proposta.dominio.modelo.proposta;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class DocumentoLimpo {

	private String documento;

	public DocumentoLimpo(String documento, String chaveSecreta, String salt) {
		@SuppressWarnings("deprecation")
		TextEncryptor textEncryptor = Encryptors.queryableText(chaveSecreta, salt);
		this.documento = textEncryptor.encrypt(documento);
	}

	public String getDocumento() {
		return documento;
	}
}