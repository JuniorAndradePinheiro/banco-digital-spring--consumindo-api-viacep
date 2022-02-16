package br.com.bancodigital.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OperacaoDto {
	
	private Long contaNumero;
	private String contaClienteNome;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime data;
	private String descricao;
	@JsonFormat(pattern = "R$ %.2f")
	private double valor;
	

	public Long getContaNumero() {
		return contaNumero;
	}
	public void setContaNumero(Long contaNumero) {
		this.contaNumero = contaNumero;
	}
	public String getContaClienteNome() {
		return contaClienteNome;
	}
	public void setContaClienteNome(String contaCliente) {
		this.contaClienteNome = contaCliente;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
