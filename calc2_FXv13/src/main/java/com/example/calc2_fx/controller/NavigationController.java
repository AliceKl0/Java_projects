package com.example.calc2_fx.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationController {
    private static String currentUserRole;

    public static void navigateTo(String fxmlFile, Stage stage) {
        navigateTo(fxmlFile, stage, null);
    }

    public static void navigateTo(String fxmlFile, Stage stage, String requiredRole) {
        try {
            if (requiredRole != null && !requiredRole.equals(currentUserRole)) {
                showAccessDeniedAlert();
                return;
            }

            Parent root = FXMLLoader.load(NavigationController.class.getResource(fxmlFile));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showLoadErrorAlert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setCurrentUserRole(String role) {
        currentUserRole = role;
    }

    private static void showAccessDeniedAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка доступа");
        alert.setHeaderText("Доступ запрещен");
        alert.setContentText("У вас нет прав для доступа к этому разделу");
        alert.showAndWait();
    }

    private static void showLoadErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка загрузки");
        alert.setHeaderText("Не удалось загрузить страницу");
        alert.setContentText("Попробуйте позже или обратитесь к администратору");
        alert.showAndWait();
    }

    public static void navigateToMain(Stage stage) {
        System.out.println("Navigating to main page...");
        try {
            FXMLLoader loader = new FXMLLoader(
                    NavigationController.class.getResource("/com/example/calc2_fx/fxml/main.fxml")
            );
            Parent root = loader.load();

            // Устанавливаем роль пользователя
            MainController controller = loader.getController();
            controller.setUserRole(currentUserRole);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showLoadErrorAlert();
        }
    }

    public static void navigateToHistory(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    NavigationController.class.getResource("/com/example/calc2_fx/fxml/history.fxml")
            );
            Parent root = loader.load();

            HistoryController controller = loader.getController();
            controller.refreshHistory();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showLoadErrorAlert();
        }
    }
}
