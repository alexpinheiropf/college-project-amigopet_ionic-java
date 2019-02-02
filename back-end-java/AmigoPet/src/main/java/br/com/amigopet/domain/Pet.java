package br.com.amigopet.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Pet extends GenericDomain {

	@Column(length = 80, nullable = false)
	private String nome;

	@Column(length = 30, nullable = false)
	private String sexo;

	@Column(length = 50, nullable = false)
	private String especie;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datanasc;

	@Column(length = 30, nullable = false)
	private String raca;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

}
