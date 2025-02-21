package com.amazingname.client.controller;

import com.amazingname.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminConnectionPageController {

    private static final String PASSWORD = "admin";  // define password manually

    @FXML private PasswordField passwordField;

    @FXML
    public void handleLogin(ActionEvent event) {
        String password = passwordField.getText();
        if (PASSWORD.equals(password)) {
            try {
                Stage stage = (Stage) passwordField.getScene().getWindow();
                Main.navigateTo(stage, "AdminPageEmployee.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Connexion échouée");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe incorrect.");
            alert.showAndWait();
        }
    }
}
