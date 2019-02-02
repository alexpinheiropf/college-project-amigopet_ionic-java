package br.com.amigopet.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Promocao extends GenericDomain {

	@Column(length = 80, nullable = false)
	private String titulo;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Servico servico;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date validade;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
}
