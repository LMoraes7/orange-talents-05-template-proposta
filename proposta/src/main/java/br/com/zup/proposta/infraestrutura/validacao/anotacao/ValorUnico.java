package br.com.zup.proposta.infraestrutura.validacao.anotacao;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zup.proposta.infraestrutura.validacao.validator.ValorUnicoValidator;

@Constraint(validatedBy = {ValorUnicoValidator.class})
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

	String message() default "Valor informado já está cadastrado no sistema. Por favor informe outro valor.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
    String campo();
    
    Class<?> classe();
}
