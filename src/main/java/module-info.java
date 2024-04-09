module com.example.localizeddatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.localizeddatabase to javafx.fxml;
    exports com.example.localizeddatabase;
}