package view;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateEventView extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		show(primaryStage);
	}

	public void show(Stage primaryStage) {
		
		
		VBox root = createLayout(primaryStage);
		 Scene scene = new Scene(root, 400, 350);
	        primaryStage.setTitle("Create Events");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

	private VBox createLayout(Stage primaryStage) {
		
		GridPane grid = createGridLayout();
		 TextField nameField = createTextField("Enter Event Name");
		 TextField locationField = createTextField("Enter Event Location");
		 TextField descField = createTextField("Enter Event Description");
		 DatePicker datePicker = new DatePicker();
	     Label errorLabel = createErrorLabel();
	     Button submitButton = createSubmitButton(nameField, datePicker, locationField, descField, errorLabel);
		 Button homeButton = createHomebutton(primaryStage);
		 addGridRow(grid, 0, new Label("Event Name:"), nameField);
		 addGridRow(grid, 1, new Label("Event Date:"), datePicker);
	     addGridRow(grid, 2, new Label("Location:"), locationField);
	     addGridRow(grid, 3, new Label("Description:"), descField);
	     grid.add(errorLabel, 1, 4);
	     addGridRow(grid, 5, homeButton, submitButton);
		VBox vbox = new VBox(grid);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}
	
	  private Button createHomebutton(Stage primaryStage) {
		  Button homeButton = new Button("Home");
	        homeButton.setOnAction(event -> new EOHomeView().show(primaryStage));
	        return homeButton;
	}
	  
	

	private Button createSubmitButton(TextField nameField, DatePicker datePicker, TextField locationField,
			TextField descField, Label errorLabel) {
		
		  Button submitButton = new Button("Submit");
		  
		  submitButton.setOnAction(event -> {
		       
		        String name = nameField.getText();
		        LocalDate date = datePicker.getValue(); 
		        String location = locationField.getText();
		        String description = descField.getText();
		        	
		        if (name.isEmpty() || date == null || location.isEmpty() || description.isEmpty()) {
		            errorLabel.setText("Ensure all fields are filled.");
		        }else {
		        	
		        }
		      
		    });
		return submitButton;
	}

	private void addGridRow(GridPane grid, int rowIndex, Control control1, Control control2) {
	        grid.add(control1, 0, rowIndex);
	        grid.add(control2, 1, rowIndex);
	   }

	private GridPane createGridLayout() {
		
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
	 
	 	private Label createErrorLabel() {
	        Label errorLabel = new Label();
	        errorLabel.setTextFill(Color.RED);
	        return errorLabel;
	    }
	 
	

}
