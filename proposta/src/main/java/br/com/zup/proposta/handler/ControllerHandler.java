package br.com.zup.proposta.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.zup.proposta.dominio.exception.EntidadeNaoEncontradaException;
import br.com.zup.proposta.handler.dto.response.ErroResponseDto;
import br.com.zup.proposta.infraestrutura.validacao.anotacao.ValorUnico;

@RestControllerAdvice
public class ControllerHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	public ControllerHandler(MessageSource message) {
		this.messageSource = message;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErroResponseDto> errosDto = new ArrayList<ErroResponseDto>();

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		List<FieldError> filterCollection = this.filtrarCollection(fieldErrors,
				erro -> !erro.getCode().equals(ValorUnico.class.getSimpleName()));

		String title = ProblemType.NEGOCIO.getTitle();

		this.preencherListaErrosDto(errosDto, filterCollection, HttpStatus.BAD_REQUEST, title);

		filterCollection = this.filtrarCollection(fieldErrors,
				erro -> erro.getCode().equals(ValorUnico.class.getSimpleName()));

		if (!filterCollection.isEmpty()) {
			status = HttpStatus.UNPROCESSABLE_ENTITY;
			this.preencherListaErrosDto(errosDto, filterCollection, HttpStatus.UNPROCESSABLE_ENTITY, title);
		}

		return this.handleExceptionInternal(ex, errosDto, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> entidadeNaoEncontradaExceptionHandle(EntidadeNaoEncontradaException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroResponseDto erroResponseDto = this
				.erroResponseDtoBuilder(status, ProblemType.ENTIDADE_NAO_ENCONTRADA.getTitle(), null, ex.getMessage())
				.build();
		return this.handleExceptionInternal(ex, erroResponseDto, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null)
			body = new ErroResponseDto.Builder(status.value(), status.getReasonPhrase()).build();
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private ErroResponseDto.Builder erroResponseDtoBuilder(HttpStatus status, String title, String field,
			String detail) {
		return new ErroResponseDto.Builder(status.value(), title).field(field).detail(detail);
	}

	private void preencherListaErrosDto(List<ErroResponseDto> errosDto, List<FieldError> filterCollection,
			HttpStatus status, String title) {
		filterCollection.forEach(erro -> {
			String detail = this.messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			errosDto.add(this.erroResponseDtoBuilder(status, title, erro.getField(), detail).build());
		});
	}

	private List<FieldError> filtrarCollection(List<FieldError> fieldErrors, Predicate<FieldError> predicate) {
		List<FieldError> filterCollection = fieldErrors.stream().filter(predicate).collect(Collectors.toList());
		return filterCollection;
	}
}