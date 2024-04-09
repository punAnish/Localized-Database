package com.example.localizeddatabase;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddEmployees extends Application {

    private ResourceBundle bundle;
    private Locale locale_en = new Locale("en", "UK");
    private Locale locale_ne = new Locale("ne", "NP");
    private Locale locale_ja = new Locale("ja", "JP");

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Add Employee");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane formGrid = new GridPane();
        formGrid.setVgap(8);
        formGrid.setHgap(10);

        ComboBox<String> comboBoxLanguage = new ComboBox<>();
        comboBoxLanguage.getItems().addAll("English", "Nepali", "Japanese");
        comboBoxLanguage.setValue("English");

        Label labelChooseLanguage = new Label();
        Label labelFirstName = new Label();
        Label labelLastName = new Label();
        Label labelEmail = new Label();
        TextField inputFirstName = new TextField();
        TextField inputLastName = new TextField();
        TextField inputEmail = new TextField();
        Button button = new Button();

        comboBoxLanguage.setOnAction(event -> {
            String selectedLanguage = comboBoxLanguage.getValue();
            if (selectedLanguage.equals("Nepali")) {
                bundle = ResourceBundle.getBundle("messages", locale_ne);
            } else if (selectedLanguage.equals("Japanese")) {
                bundle = ResourceBundle.getBundle("messages", locale_ja);
            } else {
                bundle = ResourceBundle.getBundle("messages", locale_en);
            }
            updateUI(labelFirstName, labelLastName, labelEmail, button, labelChooseLanguage);
        });

        bundle = ResourceBundle.getBundle("messages", locale_en);
        updateUI(labelFirstName, labelLastName, labelEmail, button, labelChooseLanguage);

        button.setOnAction(e -> saveData(inputFirstName.getText(), inputLastName.getText(), inputEmail.getText(), comboBoxLanguage.getValue()));

        formGrid.addRow(0, new Label("Select Language:"), comboBoxLanguage);
        formGrid.addRow(1, labelFirstName, inputFirstName);
        formGrid.addRow(2, labelLastName, inputLastName);
        formGrid.addRow(3, labelEmail, inputEmail);
        formGrid.addRow(4, new HBox(button));

        root.getChildren().addAll(title, formGrid);

        Scene scene = new Scene(root, 400, 250);
        primaryStage.setTitle("Add Employee");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateUI(Label labelFirstName, Label labelLastName, Label labelEmail, Button button, Label labelChooseLanguage) {
        labelFirstName.setText(bundle.getString("label.firstName"));
        labelLastName.setText(bundle.getString("label.lastName"));
        labelEmail.setText(bundle.getString("label.email"));
        button.setText(bundle.getString("button.save"));
        labelChooseLanguage.setText(bundle.getString("label.selectLanguage"));
    }

    private void saveData(String firstName, String lastName, String email, String selectedLanguage) {
        // JDBC URL, username, and password of MySQL database
        String JDBC_URL = "jdbc:mysql://localhost:3306/employees";
        String USERNAME = "root";
        String PASSWORD = "pLUMSTEAD$1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                String tableName;
                switch (selectedLanguage) {
                    case "Nepali":
                        tableName = "employee_ne";
                        break;
                    case "Japanese":
                        tableName = "employee_ja";
                        break;
                    default:
                        tableName = "employee_en";
                        break;
                }

                String sql = "INSERT INTO " + tableName + " (first_name, last_name, email) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setString(3, email);
                    statement.executeUpdate();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText(bundle.getString("message.saved"));
                alert.showAndWait();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to save data: " + e.getMessage()); // Print exception message
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}



