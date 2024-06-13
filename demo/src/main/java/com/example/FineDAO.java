package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class FineDAO {
    private MongoCollection<Document> finesCollection;

    public FineDAO(MongoCollection<Document> finesCollection) {
        this.finesCollection = finesCollection;
    }

    public void addFine(Fine fine) {
        Document doc = new Document("studentId", fine.getStudentId())
                .append("description", fine.getDescription())
                .append("amount", fine.getAmount());
        finesCollection.insertOne(doc);
    }

    public Fine getFine(String studentId) {
        Document doc = finesCollection.find(eq("studentId", studentId)).first();
        if (doc != null) {
            Fine fine = new Fine();
            fine.setStudentId(doc.getString("studentId"));
            fine.setDescription(doc.getString("description"));
            fine.setAmount(doc.getDouble("amount"));
            return fine;
        }
        return null;
    }

    public void updateFine(Fine fine) {
        Document doc = new Document("description", fine.getDescription())
                .append("amount", fine.getAmount());
        UpdateResult result = finesCollection.updateOne(eq("studentId", fine.getStudentId()), new Document("$set", doc));
        if (result.getMatchedCount() == 0) {
            System.out.println("No fine found with the given student ID.");
        }
    }

    public void deleteFine(String studentId) {
        DeleteResult result = finesCollection.deleteOne(eq("studentId", studentId));
        if (result.getDeletedCount() == 0) {
            System.out.println("No fine found with the given student ID.");
        }
    }
}
