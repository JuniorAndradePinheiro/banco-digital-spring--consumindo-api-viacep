package br.com.bancodigital.model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Operacao {

	//private static final String DATA_HORA_PATTERN = "dd/MM/yyyy HH:mm:ss";
	private LocalDateTime data;
	private String descricao;
	private double valor;
	
	public Operacao() {
		this.data = LocalDateTime.now();
	}

	public Operacao(String descricao, double valor) {
		this.data = LocalDateTime.now();
		
		this.descricao = descricao;
		this.valor = valor;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operacao other = (Operacao) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Operacao [data=" + data + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
	
	
}
