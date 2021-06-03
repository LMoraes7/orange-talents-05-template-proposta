package br.com.zup.proposta.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.proposta.dominio.exception.EntidadeNaoEncontradaException;
import br.com.zup.proposta.dominio.exception.NegocioException;
import br.com.zup.proposta.dominio.exception.ValorJaCadastradoException;
import br.com.zup.proposta.handler.dto.response.ErroResponseDto;
import br.com.zup.proposta.handler.dto.response.ErroValidacaoResponseDto;

@RestControllerAdvice
public class ControllerHandler {

	private MessageSource messageSource;

	public ControllerHandler(MessageSource message) {
		this.messageSource = message;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErroValidacaoResponseDto>> methodArgumentNotValidExceptionHandle(
			MethodArgumentNotValidException ex) {
		List<ErroValidacaoResponseDto> errosResponse = new ArrayList<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.forEach(erro -> {
			String message = this.messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			errosResponse.add(new ErroValidacaoResponseDto(erro.getField(), message));
		});
		return ResponseEntity.badRequest().body(errosResponse);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<ErroResponseDto> entidadeNaoEncontradaExceptionHandle(EntidadeNaoEncontradaException ex) {
		ErroResponseDto erroResponseDto = new ErroResponseDto(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponseDto);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ErroResponseDto> negocioException(NegocioException ex) {
		ErroResponseDto erroResponseDto = new ErroResponseDto(ex.getMessage());
		return ResponseEntity.badRequest().body(erroResponseDto);
	}
	
	@ExceptionHandler(ValorJaCadastradoException.class)
	public ResponseEntity<ErroResponseDto> valorJaCadastradoException(ValorJaCadastradoException ex) {
		ErroResponseDto erroResponseDto = new ErroResponseDto(ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroResponseDto);
	}
}
