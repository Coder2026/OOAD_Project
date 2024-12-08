package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.EventController;
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
import util.Response;

public class CreateEventView{

	public void show(Stage primaryStage, String id) {
		// TODO Auto-generated method stub
		VBox root = createLayout(primaryStage, id);

		 Scene scene = new Scene(root, 400, 350);
	        primaryStage.setTitle("Create Events");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

	private VBox createLayout(Stage primaryStage, String id) {
		// TODO Auto-generated method stub
  
		GridPane grid = createGridLayout();
		 TextField nameField = createTextField("Enter Event Name");
		 TextField locationField = createTextField("Enter Event Location");
		 TextField descField = createTextField("Enter Event Description");
		 DatePicker datePicker = new DatePicker();
	     Label successLabel = createErrorLabel(Color.GREEN);
	     Label errorLabel = createErrorLabel(Color.RED);
	     Button submitButton = createSubmitButton(nameField, datePicker, locationField, descField, errorLabel, successLabel, id);
		 Button homeButton = createHomebutton(primaryStage, id);
		 addGridRow(grid, 0, new Label("Event Name:"), nameField);
		 addGridRow(grid, 1, new Label("Event Date:"), datePicker);
	     addGridRow(grid, 2, new Label("Location:"), locationField);
	     addGridRow(grid, 3, new Label("Description:"), descField);
	     grid.add(errorLabel, 1, 4);
	     grid.add(successLabel, 1, 4);
	     
	     addGridRow(grid, 5, homeButton, submitButton);
		VBox vbox = new VBox(grid);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}
	
	  private Button createHomebutton(Stage primaryStage, String id) {
		  Button homeButton = new Button("Home");
	        homeButton.setOnAction(event -> new EOHomeView().show(primaryStage, id));
	        return homeButton;
	}
	  
	

	private Button createSubmitButton(TextField nameField, DatePicker datePicker, TextField locationField,

			TextField descField, Label errorLabel, Label successLabel, String id) {
		  Button submitButton = new Button("Submit");
		  submitButton.setOnAction(event -> {
		       
		        String name = nameField.getText();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate date = datePicker.getValue(); 
		        String formattedDate = "";
		        if (date != null) {
		            formattedDate = formatter.format(date);
		        } else {
		            errorLabel.setText("Please select a date");
		            return; 
		        }
		        String location = locationField.getText();
		        String description = descField.getText();
		        
		        EventController ec = new EventController();
		      
		       
		            
		            
		            Response<String> response = ec.createEvent(name, formattedDate, location, description, id);
			        
			        if (response.isSuccess()) {
			        	nameField.clear();
				        datePicker.setValue(null);
				        locationField.clear();
				        descField.clear();
			        	errorLabel.setVisible(false);
			            successLabel.setText(response.getMessage());
			        }else {
			        	 errorLabel.setText(response.getMessage());
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
	 
	 private Label createErrorLabel(Color color) {
		    Label errorLabel = new Label();
		    errorLabel.setTextFill(color);
		    return errorLabel;
		}

}
