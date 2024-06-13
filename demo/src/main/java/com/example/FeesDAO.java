package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class FeesDAO {
    private MongoCollection<Document> feesCollection;

    public FeesDAO(MongoCollection<Document> feesCollection) {
        this.feesCollection = feesCollection;
    }

    public void addFees(Fees fees) {
        Document doc = new Document("studentId", fees.getStudentId())
                .append("courseId", fees.getCourseId())
                .append("amount", fees.getAmount());
        feesCollection.insertOne(doc);
    }

    public Fees getFees(String studentId, String courseId) {
        Document doc = feesCollection.find(and(eq("studentId", studentId), eq("courseId", courseId))).first();
        if (doc != null) {
            Fees fees = new Fees();
            fees.setStudentId(doc.getString("studentId"));
            fees.setCourseId(doc.getString("courseId"));
            fees.setAmount(doc.getDouble("amount"));
            return fees;
        }
        return null;
    }

    public void updateFees(Fees fees) {
        Document doc = new Document("amount", fees.getAmount());
        UpdateResult result = feesCollection.updateOne(and(eq("studentId", fees.getStudentId()), eq("courseId", fees.getCourseId())), new Document("$set", doc));
        if (result.getMatchedCount() == 0) {
            System.out.println("No fees record found with the given student ID and course ID.");
        }
    }

    public void deleteFees(String studentId, String courseId) {
        DeleteResult result = feesCollection.deleteOne(and(eq("studentId", studentId), eq("courseId", courseId)));
        if (result.getDeletedCount() == 0) {
            System.out.println("No fees record found with the given student ID and course ID.");
        }
    }
}
