package com.amazingname.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void navigateTo(Stage stage, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/" + fxmlFile));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Trouver un collaborateur");
        navigateTo(primaryStage, "MainSearchPage.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
