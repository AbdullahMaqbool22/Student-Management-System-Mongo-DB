package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.AggregateIterable;
import java.util.Arrays;
import java.util.List;

public class GradeDAO {
    private MongoCollection<Document> gradesCollection;

    public GradeDAO(MongoCollection<Document> gradesCollection) {
        this.gradesCollection = gradesCollection;
    }

    public void addGrade(Grade grade) {
        Document doc = new Document("studentId", grade.getStudentId())
                .append("courseId", grade.getCourseId())
                .append("grade", grade.getGrade());
        gradesCollection.insertOne(doc);
    }

    public Grade getGrade(String studentId, String courseId) {
        Document doc = gradesCollection.find(and(eq("studentId", studentId), eq("courseId", courseId))).first();
        if (doc != null) {
            Grade grade = new Grade();
            grade.setStudentId(doc.getString("studentId"));
            grade.setCourseId(doc.getString("courseId"));
            grade.setGrade(doc.getString("grade"));
            return grade;
        }
        return null;
    }

    public void updateGrade(Grade grade) {
        Document doc = new Document("grade", grade.getGrade());
        UpdateResult result = gradesCollection.updateOne(and(eq("studentId", grade.getStudentId()), eq("courseId", grade.getCourseId())), new Document("$set", doc));
        if (result.getMatchedCount() == 0) {
            System.out.println("No grade record found with the given student ID and course ID.");
        }
    }

    public void deleteGrade(String studentId, String courseId) {
        DeleteResult result = gradesCollection.deleteOne(and(eq("studentId", studentId), eq("courseId", courseId)));
        if (result.getDeletedCount() == 0) {
            System.out.println("No grade record found with the given student ID and course ID.");
        }
    }

    public void getGradesDistribution() {
        List<Document> pipeline = Arrays.asList(
            new Document("$group", new Document("_id", "$grade")
                    .append("count", new Document("$sum", 1)))
        );

        AggregateIterable<Document> result = gradesCollection.aggregate(pipeline);

        for (Document doc : result) {
            System.out.println("Grade: " + doc.getString("_id") + " - Count: " + doc.getInteger("count"));
        }
    }
}
