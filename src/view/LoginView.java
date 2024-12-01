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

public class LoginView extends Application{
	
	   @Override
	    public void start(Stage primaryStage) {
	   
	        show(primaryStage);
	    }

	    public void show(Stage primaryStage) {

	        VBox root = createLayout(primaryStage);

	        Scene scene = new Scene(root, 400, 300);
	        primaryStage.setTitle("Login");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    private VBox createLayout(Stage primaryStage) {
	    
	        GridPane grid = createGridPane();
	        
	        TextField emailField = createTextField("Enter Email");
	        PasswordField passwordField = createPasswordField("Enter Password");
	        Label errorLabel = createErrorLabel();

	        addGridRow(grid, 0, new Label("Email:"), emailField);
	        addGridRow(grid, 1, new Label("Password:"), passwordField);

	        Button loginButton = createLoginButton(emailField, passwordField, errorLabel);
	        Button registerButton = createRegisterButton(primaryStage);

	        addGridRow(grid, 2, registerButton, loginButton);
	        grid.add(errorLabel, 1, 3);

	     
	        VBox vbox = new VBox(grid);
	        vbox.setAlignment(Pos.CENTER);

	        return vbox;
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

	    private Button createLoginButton(TextField emailField, PasswordField passwordField, Label errorLabel) {
	        Button loginButton = new Button("Login");
	      	UserController uc = new UserController();
	        loginButton.setOnAction(event -> {
	            String email = emailField.getText();
	            String password = passwordField.getText();

	            if (email.isEmpty() || password.isEmpty()) {
	                errorLabel.setText("Please enter both email and password.");
	            } else {
	            	
	            	User user = uc.login(email, password);
	            	if(user == null) {
	            		errorLabel.setText("please enter correct email or password");
	            	}else {
	            		
	            	}
	            }
	        });
	        return loginButton;
	    }

	    private Button createRegisterButton(Stage primaryStage) {
	        Button registerButton = new Button("Register");
	        registerButton.setOnAction(event -> new RegisterView().show(primaryStage));
	        return registerButton;
	    }

	    private void addGridRow(GridPane grid, int rowIndex, Control control1, Control control2) {
	        grid.add(control1, 0, rowIndex);
	        grid.add(control2, 1, rowIndex);
	    }

	
	
}
