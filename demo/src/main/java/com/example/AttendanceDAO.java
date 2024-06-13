package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class AttendanceDAO {
    private MongoCollection<Document> attendanceCollection;

    public AttendanceDAO(MongoCollection<Document> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    public void addAttendance(Attendance attendance) {
        Document doc = new Document("studentId", attendance.getStudentId())
                .append("courseId", attendance.getCourseId())
                .append("date", attendance.getDate())
                .append("present", attendance.isPresent());
        attendanceCollection.insertOne(doc);
    }

    public Attendance getAttendance(String studentId, String courseId) {
        Document doc = attendanceCollection.find(and(eq("studentId", studentId), eq("courseId", courseId))).first();
        if (doc != null) {
            Attendance attendance = new Attendance();
            attendance.setStudentId(doc.getString("studentId"));
            attendance.setCourseId(doc.getString("courseId"));
            attendance.setDate(doc.getDate("date"));
            attendance.setPresent(doc.getBoolean("present"));
            return attendance;
        }
        return null;
    }

    public void updateAttendance(Attendance attendance) {
        Document doc = new Document("date", attendance.getDate())
                .append("present", attendance.isPresent());
        UpdateResult result = attendanceCollection.updateOne(and(eq("studentId", attendance.getStudentId()), eq("courseId", attendance.getCourseId())), new Document("$set", doc));
        if (result.getMatchedCount() == 0) {
            System.out.println("No attendance record found with the given student ID and course ID.");
        }
    }

    public void deleteAttendance(String studentId, String courseId) {
        DeleteResult result = attendanceCollection.deleteOne(and(eq("studentId", studentId), eq("courseId", courseId)));
        if (result.getDeletedCount() == 0) {
            System.out.println("No attendance record found with the given student ID and course ID.");
        }
    }
}
