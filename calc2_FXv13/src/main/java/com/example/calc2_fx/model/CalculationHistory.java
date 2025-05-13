package com.example.calc2_fx.model;

public class CalculationHistory {

    private String operationType;
    private String details;
    private String username;

    public CalculationHistory(String operationType, String details, String username) {
        this.operationType = operationType;
        this.details = details;
        this.username = username;
    }

    // Геттеры и сеттеры


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getDetails() {
        return details;
    }

    public String getOperationType() {
        return operationType;
    }

    @Override
    public String toString() {
        return username + ": " + operationType + ": " + details;
    }
}