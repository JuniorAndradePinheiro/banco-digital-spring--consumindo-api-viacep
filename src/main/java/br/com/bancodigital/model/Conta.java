package br.com.bancodigital.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long numero;
	@ManyToOne
	protected Cliente cliente;
	//@OneToMany
	protected Long agencia;
	protected TipoConta tipo;
	protected double saldo;
	@Embedded
	@Nullable
	protected Operacao operacao;
//	protected List<Operacao> extrato = new ArrayList<>();
	
	public Conta() {
		
	}

//	public Conta(Cliente cliente, Long angecia, double saldo, Operacao operacao, List<Operacao> extrato) {
//
//		this.cliente = cliente;
//		this.angecia = angecia;
//		this.saldo = saldo;
//		this.operacao = operacao;
//		
//	}
	
	public Conta(Cliente cliente, Long angecia, double saldo) {

		this.cliente = cliente;
		this.agencia = angecia;
		this.saldo = saldo;
		this.operacao = operacao;
		
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getAngecia() {
		return agencia;
	}

	public void setAngecia(Long angecia) {
		this.agencia = angecia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	

	

	public Long getNumero() {
		return numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((operacao == null) ? 0 : operacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(saldo);
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
		Conta other = (Conta) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (operacao == null) {
			if (other.operacao != null)
				return false;
		} else if (!operacao.equals(other.operacao))
			return false;
		if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", cliente=" + cliente + ", angecia=" + agencia + ", saldo=" + saldo
				+ ", operacao=" + operacao + "]";
	}
	
	
}
