package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class TeacherDAO {
    private MongoCollection<Document> teachersCollection;

    public TeacherDAO(MongoCollection<Document> teachersCollection) {
        this.teachersCollection = teachersCollection;
    }

    public void addTeacher(Teacher teacher) {
        Document doc = new Document("id", teacher.getId())
                .append("name", teacher.getName())
                .append("email", teacher.getEmail())
                .append("department", teacher.getDepartment());
        teachersCollection.insertOne(doc);
    }

    public Teacher getTeacher(String id) {
        Document doc = teachersCollection.find(eq("id", id)).first();
        if (doc != null) {
            Teacher teacher = new Teacher();
            teacher.setId(doc.getString("id"));
            teacher.setName(doc.getString("name"));
            teacher.setEmail(doc.getString("email"));
            teacher.setDepartment(doc.getString("department"));
            return teacher;
        }
        return null;
    }

    public void updateTeacher(Teacher teacher) {
        Document doc = new Document("name", teacher.getName())
                .append("email", teacher.getEmail())
                .append("department", teacher.getDepartment());
        UpdateResult result = teachersCollection.updateOne(eq("id", teacher.getId()), new Document("$set", doc));
        if (result.getMatchedCount() == 0) {
            System.out.println("No teacher found with the given ID.");
        }
    }

    public void deleteTeacher(String id) {
        DeleteResult result = teachersCollection.deleteOne(eq("id", id));
        if (result.getDeletedCount() == 0) {
            System.out.println("No teacher found with the given ID.");
        }
    }
}
