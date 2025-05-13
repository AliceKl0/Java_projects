package com.example.calc2_fx.controller;

import com.example.calc2_fx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.calc2_fx.service.AuthService;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private AuthService authService = new AuthService();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = authService.authenticate(username, password);
        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/calc2_fx/fxml/main.fxml"));
                Parent root = loader.load();

                MainController mainController = loader.getController();
                mainController.setUserRole(user.getRole());

                NavigationController.setCurrentUserRole(user.getRole());

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleRegister() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/calc2_fx/fxml/register.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
