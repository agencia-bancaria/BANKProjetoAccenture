package com.accenture.academico.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ForeignKey;

@Entity(name = "Analista")
public class Analista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAnalista")
	private Long idAnalista;
	
	@Column(name = "nomeAnalista")
	private String nomeAnalista;
	
	@Column(name = "cpfAnalista")
	private String cpfAnalista;
	
	@Column(name="telefoneAnalista")
	private String telefoneAnalista;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_agencia_id", foreignKey = @ForeignKey(name = "fk_agencia_id"), referencedColumnName = "idAgencia")
	private Agencia agencia;
	
	public Analista() {
		
	}
	
	public Analista(String nomeAnalista, String cpfAnalista, String telefoneAnalista, Agencia agencia) {
		super();
		
		this.nomeAnalista = nomeAnalista;
		this.cpfAnalista = cpfAnalista;
		this.telefoneAnalista = telefoneAnalista;
		this.agencia = agencia;
	}
	
	public Analista(Long idAnalista, String nomeAnalista, String cpfAnalista, 
			String telefoneAnalista, Agencia agencia) {
		super();
		this.idAnalista = idAnalista;
		this.nomeAnalista = nomeAnalista;
		this.cpfAnalista = cpfAnalista;
		this.telefoneAnalista = telefoneAnalista;
		this.agencia = agencia;
	}
	
	public Long getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(Long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getNomeAnalista() {
		return nomeAnalista;
	}

	public void setNomeAnalista(String nomeAnalista) {
		this.nomeAnalista = nomeAnalista;
	}

	public String getCpfAnalista() {
		return cpfAnalista;
	}

	public void setCpfAnalista(String cpfAnalista) {
		this.cpfAnalista = cpfAnalista;
	}

	public String getTelefoneAnalista() {
		return telefoneAnalista;
	}

	public void setTelefoneAnalista(String telefoneAnalista) {
		this.telefoneAnalista = telefoneAnalista;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAnalista == null) ? 0 : idAnalista.hashCode());
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
		Analista other = (Analista) obj;
		if (idAnalista == null) {
			if (other.idAnalista != null)
				return false;
		} else if (!idAnalista.equals(other.idAnalista))
			return false;
		return true;
	}
}