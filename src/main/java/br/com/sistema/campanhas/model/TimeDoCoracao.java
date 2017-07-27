package br.com.sistema.campanhas.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "times")
public class TimeDoCoracao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Field("nome_time")
	private String nomeTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getNomeTime(){
		return nomeTime;
	}


	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	
}
