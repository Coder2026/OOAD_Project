package view;

import controller.UserController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;
import util.Response;
import util.SessionManager;

public class LoginView {
    private GridPane grid;
    private TextField emailField;
    private PasswordField passwordField;
    private Label errorLabel;
    private Button loginButton;
    private Button registerButton;
    private VBox root;
    private Stage primaryStage;
    
    public void show(Stage primaryStage) {
        this.primaryStage = primaryStage;
        init();
        
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void init() {
   
        grid = createGridPane();
        emailField = createTextField("Enter Email");
        passwordField = createPasswordField("Enter Password");
        errorLabel = createErrorLabel();
        loginButton = createLoginButton();
        registerButton = createRegisterButton();


        addGridRow(grid, 0, new Label("Email:"), emailField);
        addGridRow(grid, 1, new Label("Password:"), passwordField);
        addGridRow(grid, 2, registerButton, loginButton);
        grid.add(errorLabel, 1, 3);

  
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

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createLoginButton() {
        Button loginButton = new Button("Login");
        UserController uc = new UserController();
        
        loginButton.setOnAction(event -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            
            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Please enter both email and password.");
                return;
            } 
            
            Response<String> response = uc.login(email, password);
            
            if(!response.isSuccess()) {
                errorLabel.setText(response.getMessage());
            } else {
                User currentUser = SessionManager.getInstance().getCurrentUser();
                String role = currentUser.getUser_role();
          
                if(role.equalsIgnoreCase("eventOrganizer")) {
                    new EOHomeView().show(primaryStage, currentUser.getUser_id());
                }else if(role.equalsIgnoreCase("Admin")) {
                	new AdminHomeView().show(primaryStage);
                }else if(role.equalsIgnoreCase("Guest")) {
                	new VGHomeView().show(primaryStage);
                }else if(role.equalsIgnoreCase("Vendor")) {
                	new VGHomeView().show(primaryStage);
                }
            }
        });
        return loginButton;
    }

    private Button createRegisterButton() {
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> new RegisterView().show(primaryStage));
        return registerButton;
    }

    private void addGridRow(GridPane grid, int rowIndex, Control control1, Control control2) {
        grid.add(control1, 0, rowIndex);
        grid.add(control2, 1, rowIndex);
    }
}
