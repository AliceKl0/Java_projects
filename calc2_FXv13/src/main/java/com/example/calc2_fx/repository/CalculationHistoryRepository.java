package com.example.calc2_fx .repository;

import com.example.calc2_fx .model.CalculationHistory;

import java.util.ArrayList;
import java.util.List;

public class CalculationHistoryRepository {

    private List<CalculationHistory> history = new ArrayList<>();

    public List<CalculationHistory> findAll() {
        return history;
    }
}
