package br.com.sistema.campanhas.repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import br.com.sistema.campanhas.models.Campanha;
import br.com.sistema.campanhas.util.CampanhaCodec;

@Repository
public class CampanhaRepository{
	
	private MongoClient cliente;
	private MongoDatabase bancoDados;
	
	public void getConnection(){
		
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		
		CampanhaCodec campanhaCodec = new CampanhaCodec(codec);
		
		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(campanhaCodec));
		MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();
		
		this.cliente = new MongoClient("localhost:27017",opcoes);
		
		this.bancoDados = cliente.getDatabase("test");
	}
	
	
	public List<Campanha> findAllCampanhas(){
		
		getConnection();
		
		MongoCollection<Campanha> campanhas = this.bancoDados.getCollection("campanhas", Campanha.class);
		MongoCursor<Campanha> resultado = campanhas.find().iterator();
		
		List<Campanha> campanhasEncontradas = new ArrayList<>();
		
		while(resultado.hasNext()){
			Campanha campanha = resultado.next();
			campanhasEncontradas.add(campanha);
		}
		
		this.cliente.close();
		
		return campanhasEncontradas;
	}


	public void salvaCampanha(Campanha campanha) {
		// TODO Auto-generated method stub
		
	}

}
