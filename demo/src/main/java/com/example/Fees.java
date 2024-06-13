package com.example;

public class Fees {
    private String studentId;
    private String courseId;
    private double amount;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
