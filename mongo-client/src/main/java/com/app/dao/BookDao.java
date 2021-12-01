package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Tutorial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
//import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

@Repository
public class BookDao {

	MongoClient mongoClient;
	/*
	 * MongoClient getMongoClient() {
	 * 
	 * if(mongoClient == null) { mongoClient = new MongoClient("localhost",27017); }
	 * 
	 * return mongoClient; }
	 */

	private static final String connectionString = "mongodb://localhost:27017";

	MongoClient getMongoClient() {

		if (mongoClient == null) {
			mongoClient = MongoClients.create(connectionString);
			// ("localhost", 27017);
		}

		return mongoClient;
	}

	public String addBook(Document doc) {
		MongoClient mongoClient = getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("books");
		MongoCollection<Document> collection = database.getCollection("store");

		try {
			collection.insertOne(doc);
			return "Added new Book";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "Adding book failed!!";

	}

	public String saveAll(List<Tutorial> tutorial) {
		MongoClient mongoClient = getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("books");
		MongoCollection<Document> collection = database.getCollection("store");
		Gson gson = new GsonBuilder().create();
		List<Document> docs = new ArrayList<Document>();
		tutorial.forEach(r -> {
			
			String json = gson.toJson(r);
			Document doc = Document.parse(json);
			docs.add(doc);
			
		}

		);


		try {
			collection.insertMany(docs);
			return "Added new tutorials";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "Adding failed!!";

	}

}
