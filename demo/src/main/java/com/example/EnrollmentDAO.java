package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class EnrollmentDAO {
    private MongoCollection<Document> enrollmentsCollection;

    public EnrollmentDAO(MongoCollection<Document> enrollmentsCollection) {
        this.enrollmentsCollection = enrollmentsCollection;
    }

    public void enrollStudent(Enrollment enrollment) {
        Document doc = new Document("studentId", enrollment.getStudentId())
                .append("courseId", enrollment.getCourseId());
        enrollmentsCollection.insertOne(doc);
    }

    public Enrollment getEnrollment(String studentId, String courseId) {
        Document doc = enrollmentsCollection.find(and(eq("studentId", studentId), eq("courseId", courseId))).first();
        if (doc != null) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudentId(doc.getString("studentId"));
            enrollment.setCourseId(doc.getString("courseId"));
            return enrollment;
        }
        return null;
    }

    public void deleteEnrollment(String studentId, String courseId) {
        DeleteResult result = enrollmentsCollection.deleteOne(and(eq("studentId", studentId), eq("courseId", courseId)));
        if (result.getDeletedCount() == 0) {
            System.out.println("No enrollment found with the given student ID and course ID.");
        }
    }
}
