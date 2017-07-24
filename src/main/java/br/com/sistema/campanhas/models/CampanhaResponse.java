package br.com.sistema.campanhas.models;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampanhaResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String nomeCampanha;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Calendar dataInicio;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Calendar dataFim;
	
	private String idTimeCoracao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public void setIdTimeCoracao(String idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

}
