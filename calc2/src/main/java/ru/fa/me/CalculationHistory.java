package ru.fa.me;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class CalculationHistory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private Timestamp timestamp;
    private String operationType; // "CURRENCY" или "OHM_LAW"
    private String details;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
