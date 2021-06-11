package br.com.zup.proposta.api.controller.proposta.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;
import br.com.zup.proposta.dominio.modelo.cartao.teste.AvisoViagemDto;
import br.com.zup.proposta.dominio.modelo.cartao.teste.BloqueioDto;
import br.com.zup.proposta.dominio.modelo.cartao.teste.CarteiraDto;
import br.com.zup.proposta.dominio.modelo.cartao.teste.ParcelaDto;
import br.com.zup.proposta.dominio.modelo.cartao.teste.RenegociacaoDto;
import br.com.zup.proposta.dominio.modelo.cartao.teste.VencimentoDto;
import br.com.zup.proposta.dominio.modelo.proposta.Proposta;

public class ResultadoCartaoResponseDto {

	private String id; // número do cartão
	private LocalDateTime emitidoEm;
	private String titular;
//	Criei essas classes e atributos apenas para fins de testes
//	private List<BloqueioDto> bloqueios;
//	private List<AvisoViagemDto> avisos;
//	private List<CarteiraDto> carteiras;
//	private List<ParcelaDto> parcelas;
//	private BigDecimal limite;
//	private RenegociacaoDto renegociacao;
//	private VencimentoDto vencimento;
//	private String idProposta;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public void setEmitidoEm(LocalDateTime emitidoEm) {
		this.emitidoEm = emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Cartao toModel(Proposta proposta) {
		return new Cartao(id, emitidoEm, titular, proposta);
	}
	
	@Override
	public String toString() {
		return "ResultadoCartaoResponseDto [id=" + id + ", emitidoEm=" + emitidoEm + ", titular=" + titular + "]";
	}
}
