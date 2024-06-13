package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class StudentDAO {
    private MongoCollection<Document> studentsCollection;

    public StudentDAO(MongoCollection<Document> studentsCollection) {
        this.studentsCollection = studentsCollection;
    }

    public void addStudent(Student student) {
        Document doc = new Document("id", student.getId())
                .append("name", student.getName())
                .append("email", student.getEmail());
        studentsCollection.insertOne(doc);
    }

    public Student getStudent(String id) {
        Document doc = studentsCollection.find(eq("id", id)).first();
        if (doc != null) {
            Student student = new Student();
            student.setId(doc.getString("id"));
            student.setName(doc.getString("name"));
            student.setEmail(doc.getString("email"));
            return student;
        }
        return null;
    }

    public void updateStudent(Student student) {
        Document doc = new Document("name", student.getName())
                .append("email", student.getEmail());
        UpdateResult result = studentsCollection.updateOne(eq("id", student.getId()), new Document("$set", doc));
        if (result.getMatchedCount() == 0) {
            System.out.println("No student found with the given ID.");
        }
    }

    public void deleteStudent(String id) {
        DeleteResult result = studentsCollection.deleteOne(eq("id", id));
        if (result.getDeletedCount() == 0) {
            System.out.println("No student found with the given ID.");
        }
    }
}
