package com.example;

import java.util.Date;

public class Attendance {
    private String studentId;
    private String courseId;
    private Date date;
    private boolean present;

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", date=" + date +
                ", present=" + present +
                '}';
    }
}

