package br.com.zup.proposta.infraestrutura.validacao.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;

import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.infraestrutura.validacao.anotacao.ValorUnico;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

	@PersistenceContext
	private EntityManager manager;
	@Value("${criptografia.chave-secreta}")
	private String chaveSecreta;
	@Value("${criptografia.salt}")
	private String salt;
	private String campo;
	private Class<?> classe;

	@Override
	public void initialize(ValorUnico anotacao) {
		campo = anotacao.campo();
		classe = anotacao.classe();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		if (campo.equals("documento") && classe.getSimpleName().equals(Proposta.class.getSimpleName()))
			value = Encryptors.queryableText(chaveSecreta, salt).encrypt((String) value);
		String jpql = "SELECT 1 FROM " + this.classe.getName() + " x WHERE x." + this.campo + " = :value";
		List<?> resultList = this.manager.createQuery(jpql).setParameter("value", value).getResultList();
		return resultList.isEmpty();
	}
}
