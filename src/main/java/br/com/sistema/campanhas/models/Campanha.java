package br.com.sistema.campanhas.models;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public class Campanha implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ObjectId id;
	
	private String nomeCampanha;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private ObjectId idTimeCoracao;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public ObjectId getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public void setIdTimeCoracao(ObjectId idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	public Campanha gerarNovoId() {
		setId(new ObjectId());
		return this;
	}
	

}
