package br.com.zup.proposta.infraestrutura.validacao.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.proposta.dominio.exception.ValorJaCadastradoException;
import br.com.zup.proposta.infraestrutura.validacao.anotacao.ValorUnico;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

	@PersistenceContext
	private EntityManager manager;
	private String campo;
	private Class<?> classe;

	@Override
	public void initialize(ValorUnico anotacao) {
		campo = anotacao.campo();
		classe = anotacao.classe();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		String jpql = "SELECT 1 FROM " + this.classe.getName() + " x WHERE x." + this.campo + " = :value";
		List<?> resultList = this.manager.createQuery(jpql).setParameter("value", value).getResultList();
		if(resultList.isEmpty())
			return true;
		throw new ValorJaCadastradoException(campo, value);
	}
}
