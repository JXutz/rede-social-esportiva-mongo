package com.faesa.app.dao;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Arrays;

import com.faesa.app.connection.MongoConnection;
import com.faesa.app.model.Esporte;
import com.faesa.app.model.dto.EventosEsporteDTO;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.CountOptions;

import org.bson.*;

public class EsporteDAOMongo {
	
	private final MongoDatabase connection;
	private final MongoCollection<Document> esporteCollection;

    public EsporteDAOMongo() {
    	this.connection = MongoConnection.getConnection();
        this.esporteCollection = connection.getCollection("Esporte");
    }
    
    private Esporte fillObject(Document doc) {
        Esporte e = new Esporte();
        e.setUid(doc.getString("uid"));
        e.setNome(doc.getString("nome"));
        //e.setDtInsert(doc.getDate("dt_insert"));
        return e;
    }
    
	
	public int getTotalRegistros() {

		return (int) esporteCollection.countDocuments();
	}

	
	public Esporte selectByUId(String uid) throws Exception {
		Document query = new Document("uid", uid);
        Document result = esporteCollection.find(query).first();
        return (result != null) ? fillObject(result) : null;
	}

	
	public Esporte selectByNome(String nome) throws Exception {
		Document query = new Document("nome", nome.toUpperCase());
        Document result = esporteCollection.find(query).first();
        return (result != null) ? fillObject(result) : null;
	}

	
	public List<Esporte> selectAll() throws Exception {
		List<Esporte> esportes = new ArrayList<>();
        esporteCollection.find().iterator().forEachRemaining(doc -> esportes.add(fillObject(doc)));
        //for(Esporte e: esportes)
        //	System.out.println("- " + e.getNome() + "; " + e.getUid() == null ? "null" : e.getId());
        return esportes;
	}

	
	public List<EventosEsporteDTO> selectQtdEventosEsporte() throws Exception {

        MongoCollection<Document> esporteCollection = connection.getCollection("esporte");

        /*
        AggregateIterable<Document> queryResult = esporteCollection.aggregate(Arrays.asList(
                new Document("$lookup", new Document("from", "evento")
                        .append("localField", "uid")
                        .append("foreignField", "uid_esporte")
                        .append("as", "eventos")),
                new Document("$project", new Document("ID_ESPORTE", "$_uid")
                        .append("NOME_ESPORTE", "$nome")
                        .append("QTD_EVENTOS", new Document("$size", "$eventos"))),
                new Document("$sort", new Document("NOME_ESPORTE", 1).append("ID_ESPORTE", 1))
        ));

        List<EventosEsporteDTO> eventosEsportes = new ArrayList<>();

        for (Document document : queryResult) {
            EventosEsporteDTO ee = new EventosEsporteDTO();
            ee.setIdEsporte(document.getInteger("ID_ESPORTE"));
            ee.setNomeEsporte(document.getString("NOME_ESPORTE"));
            ee.setQtdEventos(document.getInteger("QTD_EVENTOS"));
            eventosEsportes.add(ee);
        }
        */
        
        /*
        List<Document> pipeline = new ArrayList<>();
        pipeline.add(new Document("$lookup", new Document("from", "Evento")
                .localField("uid")
                .foreignField("uid_esporte")
                .as("eventos")));
        pipeline.add(new Document("$unwind", "$eventos"));
        pipeline.add(new Document("$group", new Document("_id", "$nome")
                .append("totalEventos", new Document("$sum", 1))));

        // Executar a agregação
        AggregateIterable<Document> aggregate = esporteCollection.aggregate(pipeline);

        // Criar a lista de resultados
        List<EventosEsporteDTO> resultados = new ArrayList<>();
        for (Document document : aggregate) {
            String nomeEsporte = document.getString("_id");
            int totalEventos = document.getInteger("totalEventos");
            resultados.add(new EventosEsporteDTO(null, nomeEsporte, totalEventos));
        }
        */
        
        /*
        List<Document> pipeline = new ArrayList<Document>();
        pipeline.add(Aggregates.lookup("Evento", "uid", "uid_esporte", "eventos"));
        pipeline.add(Aggregates.unwind("$eventos"));
        pipeline.add(Aggregates.group("$nome", Aggregates.sum("totalEventos", 1)));

        // Executar a agregação
        List<Resultado> resultados = new ArrayList<>();
        esporteCollection.aggregate(pipeline).forEach(document -> {
            String nomeEsporte = document.getString("_id");
            int totalEventos = document.getInteger("totalEventos");
            resultados.add(new Resultado(nomeEsporte, totalEventos));
        });

        return eventosEsportes;
        */
        
        return new ArrayList<>();
	}

	
	public boolean insert(Esporte e) throws Exception {
		Document doc = new Document("nome", e.getNome())
			.append("uid", UUID.randomUUID().toString());
        esporteCollection.insertOne(doc);
        return true; // Adaptar conforme necessário
	}

	
	public boolean update(Esporte e) throws Exception {
		Document query = new Document("uid", e.getUid());
        Document update = new Document("$set", new Document("nome", e.getNome()));
        esporteCollection.updateOne(query, update);
        return true; // Adaptar conforme necessário
	}

	
	public boolean delete(String uid) throws Exception {
		Document query = new Document("uid", uid);
        esporteCollection.deleteOne(query);
        return true; // Adaptar conforme necessário
	}

}
