package com.example.calc2_fx.service;

import com.example.calc2_fx.model.CalculationHistory;
import com.example.calc2_fx.model.User;
import com.example.calc2_fx.repository.CalculationHistoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/calc_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty1";

    public ObservableList<CalculationHistory> getHistory() {
        List<CalculationHistory> history = new ArrayList<>();
        User currentUser = AuthService.getCurrentUser();

        String sql = "SELECT * FROM operations";
        if (!"ROLE_ADMIN".equals(currentUser.getRole())) {
            sql += " WHERE username = ?";
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (!"ROLE_ADMIN".equals(currentUser.getRole())) {
                stmt.setString(1, currentUser.getUsername());
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                history.add(new CalculationHistory(
                        rs.getString("operation_type"),
                        rs.getString("details"),
                        rs.getString("username")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return FXCollections.observableArrayList(history);
    }

    public void saveOperation(String operationType, String details) {
        String sql = "INSERT INTO operations (operation_type, details, username) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, operationType);
            stmt.setString(2, details);
            stmt.setString(3, AuthService.getCurrentUser().getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
