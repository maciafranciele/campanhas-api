package br.com.sistema.campanhas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.sistema.campanhas.model.TimeDoCoracao;

public interface TimeRepository extends MongoRepository<TimeDoCoracao,String >{
	
	TimeDoCoracao findById(String id);
}
