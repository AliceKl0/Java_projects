package com.example.calc2_fx.controller;

import com.example.calc2_fx.service.HistoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.example.calc2_fx.service.CurrencyService;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CurrencyConverterController {

    @FXML
    private ComboBox<String> fromCurrency;

    @FXML
    private ComboBox<String> toCurrency;

    @FXML
    private TextField amount;

    @FXML
    private TextField result;

    private CurrencyService currencyService = new CurrencyService();

    @FXML
    public void initialize() {
        List<String> currencies = Arrays.asList("USD", "EUR", "RUB");
        fromCurrency.getItems().addAll(currencies);
        toCurrency.getItems().addAll(currencies);
    }

    @FXML
    private void handleConvert() {
        try {
            BigDecimal amountValue = new BigDecimal(amount.getText());
            String from = fromCurrency.getValue();
            String to = toCurrency.getValue();

            BigDecimal resultValue = currencyService.convert(from, to, amountValue);
            result.setText(resultValue.toString());

            // Сохраняем операцию
            String details = String.format("%s %s → %s %s",
                    amountValue, from, resultValue, to);
            HistoryService historyService = new HistoryService();
            historyService.saveOperation("CURRENCY", details);

        } catch (Exception e) {
            result.setText("Ошибка: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToMain(ActionEvent event) {
        System.out.println("Navigating to main...");
        NavigationController.navigateToMain(
                (Stage) ((Button) event.getSource()).getScene().getWindow()
        );
    }
}
