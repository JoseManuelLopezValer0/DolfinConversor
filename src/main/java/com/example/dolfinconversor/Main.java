package com.example.dolfinconversor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dolphin RVZ - ISO Conversor");
//        Icono de la aplicación: dolphin.40.png
        stage.getIcons().add(new Image("file:src/main/java/com/example/dolfinconversor/dolphin.40.png"));
        stage.setScene(scene);
        stage.show();
    }
}