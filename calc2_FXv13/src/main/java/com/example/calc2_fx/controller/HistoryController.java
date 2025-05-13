package com.example.calc2_fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import com.example.calc2_fx.model.CalculationHistory;
import com.example.calc2_fx.service.HistoryService;
import javafx.stage.Stage;

public class HistoryController {

    @FXML
    private ListView<CalculationHistory> historyList;

    private HistoryService historyService = new HistoryService();

    @FXML
    private void initialize() {
        refreshHistory();
    }

    void refreshHistory() {
        historyList.setItems(historyService.getHistory());
    }

    @FXML
    private void handleBackToMain(ActionEvent event) {
        NavigationController.navigateToMain(
                (Stage) ((Button) event.getSource()).getScene().getWindow()
        );
    }
}
