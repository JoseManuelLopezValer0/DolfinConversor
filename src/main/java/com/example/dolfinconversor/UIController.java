package com.example.dolfinconversor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UIController {
    public TextField inputFilePath;
    public TextField outputFilePath;

    public String FileName;
    public ProgressIndicator progressIndicator;

    @FXML
    protected void close() {
        System.exit(0);
    }

    @FXML
    protected void about() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("About.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Acerca de");
        stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/com/clasproyect/clasproyect/servidor.png"));
        stage.setScene(scene);
        stage.show();
    }

    public void openInputFilePath() {
        selectFile(inputFilePath);
    }

    public void openOutputFilePath() {
        selectPath(outputFilePath);
    }

    private void selectPath(TextField outputFilePath) {
        javafx.stage.DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
        directoryChooser.setTitle("Seleccionar carpeta");
        javafx.stage.Stage stage = (javafx.stage.Stage) outputFilePath.getScene().getWindow();
        java.io.File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            outputFilePath.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void selectFile(TextField inputFilePath) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter("Archivos de RVZ", "*.rvz"),
                new javafx.stage.FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        javafx.stage.Stage stage = (javafx.stage.Stage) inputFilePath.getScene().getWindow();
        java.io.File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            inputFilePath.setText(selectedFile.getAbsolutePath());
        }
        if (selectedFile != null) {
            FileName = selectedFile.getName();
            FileName = FileName.substring(0, FileName.lastIndexOf("."));
        }
    }


    public void convert() {
        progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        String inputFilePath = this.inputFilePath.getText();
        String outputFilePath = this.outputFilePath.getText();
        String command = "DolphinTool.exe convert -i \"" + inputFilePath + "\" -o \"" + outputFilePath + "\\" + FileName + ".iso\" -f iso";
        if (inputFilePath.isEmpty() || outputFilePath.isEmpty()) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Debe seleccionar un archivo de entrada y una carpeta de salida.");
            alert.showAndWait();
            progressIndicator.setProgress(0);
        } else {
            try {
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor();
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Conversión exitosa");
                alert.setHeaderText("Conversión exitosa");
                alert.setContentText("El archivo se ha convertido exitosamente.");
                alert.showAndWait();
                progressIndicator.setProgress(1);
            } catch (IOException | InterruptedException e) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Ha ocurrido un error al convertir el archivo.\n" + e.getMessage());
                alert.showAndWait();
                progressIndicator.setProgress(0);
            }
        }
    }
}