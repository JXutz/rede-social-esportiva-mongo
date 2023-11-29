package com.faesa.app.connection;

import com.mongodb.client.*;


public class MongoConnection {

	//private static MongoClient client = null;
	private static MongoDatabase connection = null;
	
	static {
		try {

			MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
			MongoDatabase database = mongoClient.getDatabase("test");
			
			System.out.println("Conexão Efetuada!");
			//client = mongoClient;
			connection = database;
			
		} catch(Exception e) {
			System.out.println("Erro de Conexão: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static MongoDatabase getConnection()
	{
		return connection;
	}
	
	/*public static void main(String[] args) {
		try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
			
			MongoDatabase database = mongoClient.getDatabase("Rede_Social_Esportiva");
			System.out.println("Conexão Efetuada!");
			
		}catch(Exception e) {
			System.out.println("Erro de Conexão: "+ e.getMessage());
			e.printStackTrace();
		}
	}*/

}
