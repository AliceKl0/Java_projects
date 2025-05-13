package com.example.calc2_fx.controller;

import com.example.calc2_fx.model.User;
import com.example.calc2_fx.repository.UserRepository;
import com.example.calc2_fx.service.AuthService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserManagementController {

    @FXML private TableView<User> usersTable;
    @FXML private Button deleteButton;

    private UserRepository userRepository = new UserRepository();

    @FXML
    private void initialize() {
        refreshUsers();

        // Добавляем слушатель выбора в таблице
        usersTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        User currentUser = AuthService.getCurrentUser();
                        boolean isSelf = newSelection.getUsername().equals(currentUser.getUsername());
                        deleteButton.setDisable(isSelf);
                    }
                });
    }

    private void refreshUsers() {
        usersTable.setItems(FXCollections.observableArrayList(userRepository.findAll()));
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) return;

        User currentUser = AuthService.getCurrentUser();

        // Проверяем, не пытается ли админ удалить себя
        if (selectedUser.getUsername().equals(currentUser.getUsername())) {
            return;
        }

        // Подтверждение удаления
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Удаление пользователя: " + selectedUser.getUsername());
        alert.setContentText("Все операции пользователя также будут удалены. Продолжить?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            if (userRepository.delete(selectedUser.getUsername())) {
                refreshUsers();
            }
        }
    }

    @FXML
    private void handleBackToMain(ActionEvent event) {
        NavigationController.navigateToMain(
                (Stage) ((Button) event.getSource()).getScene().getWindow()
        );
    }
}
