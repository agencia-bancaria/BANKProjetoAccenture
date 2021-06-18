package com.accenture.academico.model;

import java.io.Serializable;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity(name = "Agencia")
@Data
public class Agencia implements Serializable{
	
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idAgencia")
	private Long idAgencia;
	
	@NotEmpty
	@Column(name = "nomeAgencia")
	private String nomeAgencia;
	
	@NotEmpty
	@Column(name="numeroAgencia")
	private String numeroAgencia;
	
	@NotEmpty
	@Column(name="telefoneAgencia")
	private String telefoneAgencia;
	
	@Column(name="dataAbertura")
	private LocalDate  dataAbertura;
	
	public Agencia() {
		
	}

	public Agencia(String nomeAgencia, String numeroAgencia, String telefoneAgencia, LocalDate  dataAbertura) {
		this.nomeAgencia = nomeAgencia;
		this.numeroAgencia = numeroAgencia;
		this.telefoneAgencia = telefoneAgencia;
		this.dataAbertura = dataAbertura;
	}
	
	public Agencia(long l, String nomeAgencia, String numeroAgencia, String telefoneAgencia, LocalDate dataAbertura) {
		this.idAgencia = l;
		this.nomeAgencia = nomeAgencia;
		this.numeroAgencia = numeroAgencia;
		this.telefoneAgencia = telefoneAgencia;
		this.dataAbertura = dataAbertura;
	}

	public LocalDate  getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate  dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getTelefoneAgencia() {
		return telefoneAgencia;
	}

	public void setTelefoneAgencia(String telefoneAgencia) {
		this.telefoneAgencia = telefoneAgencia;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	
	//Foi usado anteriormente
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgencia == null) ? 0 : idAgencia.hashCode());
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
		Agencia other = (Agencia) obj;
		if (idAgencia == null) {
			if (other.idAgencia != null)
				return false;
		} else if (!idAgencia.equals(other.idAgencia))
			return false;
		return true;
	}	

}
