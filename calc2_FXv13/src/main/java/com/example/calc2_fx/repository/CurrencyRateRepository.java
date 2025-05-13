package com.example.calc2_fx.repository;

import com.example.calc2_fx.model.CurrencyRate;

import java.math.BigDecimal;
import java.util.*;
import java.sql.*;

public class CurrencyRateRepository {
    private final Connection connection;

    public CurrencyRateRepository() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/calc_db",
                    "postgres",
                    "qwerty1"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }

    public CurrencyRate findByFromAndTo(String from, String to) {
        String sql = "SELECT * FROM currency_rate WHERE from_currency = ? AND to_currency = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, from);
            stmt.setString(2, to);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CurrencyRate(
                        rs.getString("from_currency"),
                        rs.getString("to_currency"),
                        rs.getBigDecimal("rate")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    public void saveOrUpdate(CurrencyRate rate) {
        String sql = "INSERT INTO currency_rate (from_currency, to_currency, rate) "
                + "VALUES (?, ?, ?) "
                + "ON CONFLICT (from_currency, to_currency) "
                + "DO UPDATE SET rate = EXCLUDED.rate";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, rate.getFromCurrency());
            stmt.setString(2, rate.getToCurrency());
            stmt.setBigDecimal(3, rate.getRate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    public void delete(String from, String to) {
        String sql = "DELETE FROM currency_rate WHERE from_currency = ? AND to_currency = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, from);
            stmt.setString(2, to);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    public List<CurrencyRate> findAll() {
        List<CurrencyRate> rates = new ArrayList<>();
        String sql = "SELECT * FROM currency_rate";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rates.add(new CurrencyRate(
                        rs.getString("from_currency"),
                        rs.getString("to_currency"),
                        rs.getBigDecimal("rate")
                ));
            }
            return rates;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    public Set<String> getAllUniqueCurrencies() {
        Set<String> currencies = new HashSet<>();
        String sql = "SELECT from_currency, to_currency FROM currency_rate";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                currencies.add(rs.getString("from_currency"));
                currencies.add(rs.getString("to_currency"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return currencies;
    }
}
