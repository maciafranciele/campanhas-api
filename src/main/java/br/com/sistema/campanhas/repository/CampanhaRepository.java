package br.com.sistema.campanhas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.sistema.campanhas.model.Campanha;

public interface CampanhaRepository extends MongoRepository<Campanha, String> {
	
	@Query("{ 'data_Fim' : { $gt: ?0 } }")
    public List<Campanha> findAllVigentes(LocalDate data);
	
	public Campanha findById(String id);
	
	
}
