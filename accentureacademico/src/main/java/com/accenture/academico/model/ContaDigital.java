package com.accenture.academico.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "ContaDigital")
public class ContaDigital implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final double valorSaqueMinimo = 20;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idConta")
	private Long idConta;

	@Column(name = "dataCriacao")
	private LocalDate dataCriacao;

	@Column(name = "NumConta")
	private int contaNumero;

	@Column(name = "saldo")
	private double contaSaldo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_cliente_id", foreignKey = @ForeignKey(name = "fk_cliente_id"), referencedColumnName = "idCliente")
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_agencia_id", foreignKey = @ForeignKey(name = "fk_agencia_id"), referencedColumnName = "idAgencia")
	private Agencia agencia;
	
	public ContaDigital() {

	}
	
	public ContaDigital(LocalDate dataAgora, double saldo, Cliente cliente) {
		this.dataCriacao = dataAgora;
		this.contaSaldo = saldo;
		this.cliente = cliente;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public int getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(int contaNumero) {
		this.contaNumero = contaNumero;
	}

	public double getContaSaldo() {
		return contaSaldo;
	}

	public void setContaSaldo(double contaSaldo) {
		this.contaSaldo = contaSaldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static double getValorsaqueminimo() {
		return valorSaqueMinimo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
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
		ContaDigital other = (ContaDigital) obj;
		if (idConta == null) {
			if (other.idConta != null)
				return false;
		} else if (!idConta.equals(other.idConta))
			return false;
		return true;
	}
}