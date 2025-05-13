package com.example.calc2_fx.controller;

import javafx.event.ActionEvent; // Добавьте этот импорт
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
    private String userRole;

    @FXML
    private VBox buttonsContainer;

    public void setUserRole(String role) {
        this.userRole = role;
        initializeButtons();
    }

    private void initializeButtons() {
        buttonsContainer.getChildren().clear();

        addButton("Конвертер валют", this::handleCurrencyConverter);
        addButton("Калькулятор закона Ома", this::handleOhmLawCalculator);
        addButton("История операций", this::handleHistory);

        if ("ROLE_ADMIN".equals(userRole)) {
            addButton("Управление курсами валют", this::handleCurrencyManagement);
            addButton("Управление пользователями", this::handleUserManagement);
        }

        addButton("Выйти", this::handleLogout);
    }

    private void addButton(String text, EventHandler<ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setOnAction(handler);
        buttonsContainer.getChildren().add(btn);
    }


    @FXML
    private void handleCurrencyConverter(ActionEvent event) {
        NavigationController.navigateTo(
                "/com/example/calc2_fx/fxml/currency_converter.fxml",
                (Stage) ((Button) event.getSource()).getScene().getWindow(),
                null
        );
    }

    @FXML
    private void handleOhmLawCalculator(ActionEvent event) {
        NavigationController.navigateTo(
                "/com/example/calc2_fx/fxml/ohm_law.fxml",
                (Stage) ((Button) event.getSource()).getScene().getWindow(),
                null
        );
    }

    @FXML
    private void handleCurrencyManagement(ActionEvent event) {
        NavigationController.navigateTo(
                "/com/example/calc2_fx/fxml/currency_management.fxml",
                (Stage) ((Button) event.getSource()).getScene().getWindow(),
                "ROLE_ADMIN"
        );
    }

    @FXML
    private void handleUserManagement(ActionEvent event) {
        NavigationController.navigateTo(
                "/com/example/calc2_fx/fxml/user_management.fxml",
                (Stage) ((Button) event.getSource()).getScene().getWindow(),
                "ROLE_ADMIN"
        );
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        NavigationController.navigateTo(
                "/com/example/calc2_fx/fxml/login.fxml",
                (Stage) ((Button) event.getSource()).getScene().getWindow(),
                null
        );
    }

    @FXML
    private void handleHistory(ActionEvent event) {
        NavigationController.navigateToHistory(
                (Stage) ((Button) event.getSource()).getScene().getWindow()
        );
    }
}
