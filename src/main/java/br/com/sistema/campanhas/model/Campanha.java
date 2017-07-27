package br.com.sistema.campanhas.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonValue;

@Document(collection = "campanhas")
public class Campanha implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Field("nome_campanha")
	private String nomeCampanha;
	
	@Field("data_inicio")
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataInicio;
	
	@Field("data_fim")
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataFim;
	
	
	@DBRef(db="times")
	private TimeDoCoracao timeDoCoracao;
	
	
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

	public LocalDate getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}


	public LocalDate getDataFim() {
		return dataFim;
	}


	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public TimeDoCoracao getTimeDoCoracao() {
		return timeDoCoracao;
	}

	public void setTimeDoCoracao(TimeDoCoracao timeDoCoracao) {
		this.timeDoCoracao = timeDoCoracao;
	}


	

}
