package com.example;

public class Fine {
    private String studentId;
    private String description;
    private double amount;

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "studentId='" + studentId + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
