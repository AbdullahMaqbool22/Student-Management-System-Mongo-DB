package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class CourseDAO {
    private MongoCollection<Document> coursesCollection;

    public CourseDAO(MongoCollection<Document> coursesCollection) {
        this.coursesCollection = coursesCollection;
    }

    public void addCourse(Course course) {
        Document doc = new Document("id", course.getId())
                .append("name", course.getName())
                .append("description", course.getDescription());
        coursesCollection.insertOne(doc);
    }

    public Course getCourse(String id) {
        Document doc = coursesCollection.find(eq("id", id)).first();
        if (doc != null) {
            Course course = new Course();
            course.setId(doc.getString("id"));
            course.setName(doc.getString("name"));
            course.setDescription(doc.getString("description"));
            return course;
        }
        return null;
    }

    public void updateCourse(Course course) {
        Document doc = new Document("name", course.getName())
                .append("description", course.getDescription());
        UpdateResult result = coursesCollection.updateOne(eq("id", course.getId()), new Document("$set", doc));
        if (result.getMatchedCount() == 0) {
            System.out.println("No course found with the given ID.");
        }
    }

    public void deleteCourse(String id) {
        DeleteResult result = coursesCollection.deleteOne(eq("id", id));
        if (result.getDeletedCount() == 0) {
            System.out.println("No course found with the given ID.");
        }
    }
}
