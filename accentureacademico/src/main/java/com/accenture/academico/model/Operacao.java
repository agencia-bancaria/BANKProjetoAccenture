package com.accenture.academico.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity(name = "Operacao")
public class Operacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOperacao")
	private Long idOperacao;
	

	@Column(name = "dataHoraOperacao")
	private String dataHoraOperacao;
	
	
	@Column(name = "tipoOperacao")
	private TipoOperacao tipoOperacao;
	

	@Column(name = "valor")
	private double valor;
	
	@NotNull()
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_conta_id", foreignKey = @ForeignKey(name = "fk_conta_id"), referencedColumnName = "idConta")
	private ContaDigital conta;
	
	public Operacao() {
		
	}
	
	public Operacao(String dataHoraOperacao, TipoOperacao tipoOperacao, double valor, ContaDigital conta) {
		this.dataHoraOperacao = dataHoraOperacao;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.conta = conta;
	}
	public Long getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(Long idOperacao) {
		this.idOperacao = idOperacao;
	}

	public String getDataHoraOperacao() {
		return dataHoraOperacao;
	}

	public void setDataHoraOperacao(String dataHoraOperacao) {
		this.dataHoraOperacao = dataHoraOperacao;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ContaDigital getConta() {
		return conta;
	}

		
}
