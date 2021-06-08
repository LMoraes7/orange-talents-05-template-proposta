package br.com.zup.proposta.infraestrutura.validacao.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.proposta.infraestrutura.validacao.anotacao.Base64;

public class Base64Validator implements ConstraintValidator<Base64, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			java.util.Base64.getDecoder().decode(value.getBytes());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
