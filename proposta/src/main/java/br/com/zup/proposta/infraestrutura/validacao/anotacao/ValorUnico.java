package br.com.zup.proposta.infraestrutura.validacao.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zup.proposta.infraestrutura.validacao.validator.ValorUnicoValidator;

@Constraint(validatedBy = {ValorUnicoValidator.class})
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

	String message() default "Valor informado já está cadastrado no sistema. Por favor informe outro valor.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
    String campo();
    
    Class<?> classe();
}
