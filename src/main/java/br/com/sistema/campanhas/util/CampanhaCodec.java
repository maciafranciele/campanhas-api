package br.com.sistema.campanhas.util;

import java.util.Date;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.mongodb.DBRef;

import br.com.sistema.campanhas.models.Campanha;

public class CampanhaCodec implements CollectibleCodec<Campanha>{
	
	private Codec<Document> codec;

    public CampanhaCodec(Codec<Document> codec) {
        this.codec = codec;
    }

	@Override
	public void encode(BsonWriter writer, Campanha campanha, EncoderContext encoderContext) {
		Document document = new Document();

        ObjectId id = campanha.getId();
        String nome = campanha.getNomeCampanha();
        Date dataInicio = campanha.getDataInicio();
        Date dataFim = campanha.getDataFim();
        ObjectId idTimeCoracao = campanha.getIdTimeCoracao();
        
        document.put("_id", id);
        document.put("nome_campanha", nome);
        document.put("data_inicio", dataInicio);
        document.put("data_fim", dataFim);
        document.put("time_coracao", idTimeCoracao);
        
        codec.encode(writer, document, encoderContext);
		
	}

	@Override
	public Class<Campanha> getEncoderClass() {
		return Campanha.class;
	}

	@Override
	public Campanha decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = codec.decode(reader, decoderContext);

		Campanha campanha = new Campanha();

        if(document.getObjectId("_id") != null){campanha.setId(document.getObjectId("_id"));}
        if(document.getString("nome_campanha") != null){campanha.setNomeCampanha(document.getString("nome_campanha"));}
        if(document.getDate("data_inicio") != null){campanha.setDataInicio(document.getDate("data_inicio"));}
        if(document.getDate("data_fim") != null){campanha.setDataFim(document.getDate("data_fim"));}
        
        if(document.get("time_coracao") != null){ 
        	DBRef timeDoCoracao = (DBRef) document.get("time_coracao");
        	campanha.setIdTimeCoracao((ObjectId) timeDoCoracao.getId());
        }
        
		return campanha;
	}

	@Override
	public boolean documentHasId(Campanha campanha) {
		return campanha.getId() != null;
	}

	@Override
	public Campanha generateIdIfAbsentFromDocument(Campanha campanha) {
		return documentHasId(campanha) ? campanha : campanha.gerarNovoId();
    }


	@Override
	public BsonValue getDocumentId(Campanha campanha) {
		if (!documentHasId(campanha)) {
            throw new IllegalStateException("Campanha não tem um _id");
        }

        return new BsonString(campanha.getId().toHexString());
	}

}
