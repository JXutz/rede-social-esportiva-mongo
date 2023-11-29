package com.faesa.app.dao;

import com.faesa.app.connection.MongoConnection;
import com.faesa.app.model.Evento;
import com.faesa.app.model.Esporte;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventoDAOMongo {
	
	private final MongoDatabase connection;
    private final MongoCollection<Document> eventoCollection;
    
    public EventoDAOMongo() {
    	this.connection = MongoConnection.getConnection();
        this.eventoCollection = connection.getCollection("Evento");
    }
    
    private Evento fillObject(Document doc) {
        Evento e = new Evento();
        e.setUid(doc.getString("uid"));
        e.setNome(doc.getString("nome"));
        e.setDescricao(doc.getString("descricao"));
        e.setData(doc.getDate("dt_evento"));
        e.setLocal(doc.getString("local"));

        Esporte esporte = new Esporte();
        esporte.setNome(doc.getString("nome_esporte"));
        e.setEsporte(esporte);

        return e;
    }

    
    public int getTotalRegistros() {
        return (int) eventoCollection.countDocuments();
    }

    
    public Evento selectByUId(String uid) throws Exception {
        Document query = new Document("uid", uid);
        Document result = eventoCollection.find(query).first();
        return (result != null) ? fillObject(result) : null;
    }

    
    public List<Evento> selectAll() throws Exception {
        List<Evento> eventos = new ArrayList<>();
        eventoCollection.find().iterator().forEachRemaining(doc -> eventos.add(fillObject(doc)));
        return eventos;
    }

    
    public boolean insert(Evento e) throws Exception {
        Document doc = new Document("nome_esporte", e.getEsporte().getNome())
        		.append("uid", UUID.randomUUID().toString())
        		.append("uid_esporte", e.getUidEsporte())
                .append("nome", e.getNome())
                .append("descricao", e.getDescricao())
                .append("dt_evento", e.getData())
                .append("local", e.getLocal());

        eventoCollection.insertOne(doc);
        return true;
    }

    
    public boolean update(Evento e) throws Exception {
        Document query = new Document("uid", e.getUid());
        Document update = new Document("$set",
                new Document("nome", e.getNome())
                        .append("descricao", e.getDescricao())
                        .append("dt_evento", e.getData())
                        .append("local", e.getLocal()));

        eventoCollection.updateOne(query, update);
        return true;
    }

    
    public boolean delete(String uid) throws Exception {
        Document query = new Document("uid", uid);
        eventoCollection.deleteOne(query);
        return true;
    }

   
}

