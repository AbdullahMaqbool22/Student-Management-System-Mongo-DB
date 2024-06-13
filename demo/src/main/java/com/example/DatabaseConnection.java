package com.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseConnection(String connectionString, String dbName) {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(dbName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        mongoClient.close();
    }
}