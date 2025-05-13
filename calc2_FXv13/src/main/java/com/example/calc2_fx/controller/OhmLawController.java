package com.example.calc2_fx.controller;

import com.example.calc2_fx.service.AuthService;
import com.example.calc2_fx.service.HistoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class OhmLawController {
    @FXML private TextField voltageField;
    @FXML private TextField currentField;
    @FXML private TextField resistanceField;

    @FXML
    private void handleCalculate() {
        try {
            int filledFields = 0;
            double v = parseField(voltageField);
            double i = parseField(currentField);
            double r = parseField(resistanceField);

            if (r == 0 && !Double.isNaN(v) && !Double.isNaN(i)) {
                throw new ArithmeticException("Сопротивление не может быть нулевым");
            }

            if (i == 0 && !Double.isNaN(v) && !Double.isNaN(r)) {
                throw new ArithmeticException("Сила тока не может быть нулевой");
            }

            if (Double.isNaN(v) && !Double.isNaN(i) && !Double.isNaN(r)) {
                v = i * r;
                voltageField.setText(String.format("%.2f", v));
            } else if (Double.isNaN(i) && !Double.isNaN(v) && !Double.isNaN(r)) {
                i = v / r;
                currentField.setText(String.format("%.2f", i));
            } else if (Double.isNaN(r) && !Double.isNaN(v) && !Double.isNaN(i)) {
                r = v / i;
                resistanceField.setText(String.format("%.2f", r));
            } else {
                showErrorAlert("Ошибка", "Заполните ровно два поля!");
            }

            // После успешного расчета
            if (AuthService.getCurrentUser() != null) {
                String details;
                if (!Double.isNaN(v)) {
                    details = String.format("V = I * R = %.2f * %.2f = %.2f", i, r, v);
                } else if (!Double.isNaN(i)) {
                    details = String.format("I = V / R = %.2f / %.2f = %.2f", v, r, i);
                } else {
                    details = String.format("R = V / I = %.2f / %.2f = %.2f", v, i, r);
                }
                new HistoryService().saveOperation("OHM_LAW", details);
            } else {
                showErrorAlert("Ошибка", "Пользователь не авторизован!");
            }

        } catch (NumberFormatException | ArithmeticException e) {
            showErrorAlert("Ошибка ввода", e.getMessage());
        }
    }

    private double parseField(TextField field) {
        return field.getText().isEmpty() ?
                Double.NaN :
                Double.parseDouble(field.getText());
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToMain(ActionEvent event) {
        NavigationController.navigateToMain(
                (Stage) ((Button) event.getSource()).getScene().getWindow()
        );
    }
}
