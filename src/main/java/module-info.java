module com.example.dolfinconversor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.dolfinconversor to javafx.fxml;
    exports com.example.dolfinconversor;
}