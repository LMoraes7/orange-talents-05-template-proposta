package br.com.zup.proposta.handler.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErroResponseDto {

	private Integer status;
	private String title;
	private String field;
	private String detail;

	private ErroResponseDto(Integer status, String title, String field, String detail) {
		this.status = status;
		this.title = title;
		this.field = field;
		this.detail = detail;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public String getField() {
		return field;
	}

	public String getDetail() {
		return detail;
	}

	public static class Builder {
		private final Integer status;
		private final String title;

		private String detail;
		private String field;

		public Builder(Integer status, String title) {
			this.status = status;
			this.title = title;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder field(String field) {
			this.field = field;
			return this;
		}

		public ErroResponseDto build() {
			return new ErroResponseDto(this.status, this.title, this.field, this.detail);
		}
	}
}