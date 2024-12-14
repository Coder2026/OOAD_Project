package view;

import controller.UserController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Response;

public class RegisterView {

    private GridPane grid;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField emailField;
    private ComboBox<String> roleComboBox;
    private Label errorLabel;
    private Button registerButton;
    private Button loginButton;
    private VBox root;
    private Stage primaryStage;

    public void show(Stage primaryStage) {
        this.primaryStage = primaryStage;
        init();
        
        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void init() {
        grid = createGridPane();
        usernameField = createTextField("Enter username");
        passwordField = createPasswordField("Enter password");
        emailField = createTextField("Enter email");
        roleComboBox = createComboBox("Select Role");
        errorLabel = createErrorLabel();
        registerButton = createRegisterButton();
        loginButton = createLoginButton();

        addGridRow(grid, 0, new Label("Email:"), emailField);
        addGridRow(grid, 1, new Label("Username:"), usernameField);
        addGridRow(grid, 2, new Label("Password:"), passwordField);
        addGridRow(grid, 3, new Label("Role:"), roleComboBox);
        addGridRow(grid, 4, loginButton, registerButton);
        grid.add(errorLabel, 1, 5);

        root = new VBox(grid);
        root.setAlignment(Pos.CENTER);
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private TextField createTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        return textField;
    }

    private PasswordField createPasswordField(String prompt) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(prompt);
        return passwordField;
    }

    private ComboBox<String> createComboBox(String prompt) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Admin", "EventOrganizer", "Guest", "Vendor");
        comboBox.setPromptText(prompt);
        return comboBox;
    }

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createRegisterButton() {
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String role = roleComboBox.getValue();

            UserController uc = new UserController();
            Response<String> response = uc.register(email, username, password, role);
            
            if (response.isSuccess()) {
                new LoginView().show(primaryStage);
            } else {
                errorLabel.setText(response.getMessage());
            }
        });
        return registerButton;
    }

    private Button createLoginButton() {
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> new LoginView().show(primaryStage));
        return loginButton;
    }

    private void addGridRow(GridPane grid, int rowIndex, Control control1, Control control2) {
        grid.add(control1, 0, rowIndex);
        grid.add(control2, 1, rowIndex);
    }
}
