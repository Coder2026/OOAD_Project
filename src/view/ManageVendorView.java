package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageVendorView extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		show(primaryStage);
	}

	public void show(Stage primaryStage) {
		// TODO Auto-generated method stub
		  VBox root = createLayout(primaryStage);
	        Scene scene = new Scene(root, 800, 600);
	        primaryStage.setTitle("Manage Vendor");
	        primaryStage.setScene(scene);
	        primaryStage.show(); 
	}

	private VBox createLayout(Stage primaryStage) {
		 GridPane grid = createGridPane();
		  Label productLabel = new Label("Product Name:");
	      Label descLabel = new Label("Product Description:");
	      TextField productField = createTextField("Enter Product Name");
	      TextField descField = createTextField("Enter Description");
	      HBox productBox = createLabelValueBox(productLabel, productField);
	      HBox descBox = createLabelValueBox(descLabel, descField);
	      Button saveButton = new Button("Save");
	        saveButton.setOnAction(event -> {
	        	save(productField, descField);
	        });
	        Button backButton = new Button("Home");
	        backButton.setOnAction(event -> {
	        	new VendorHomeView().show(primaryStage);
	        });
	        HBox buttonBox = createLabelValueBox(backButton, saveButton);
	      
	       grid.add(productBox, 0, 0);
	       grid.add(descBox, 0, 1);
	       grid.add(buttonBox, 0, 2);
		 VBox vbox = new VBox(grid);
	     vbox.setAlignment(Pos.CENTER);
	     return vbox;
	}
	
    private void save(TextField productField, TextField descField) {
		// TODO Auto-generated method stub

	}

	private TextField createTextField(String prompt) {
    	 TextField textField = new TextField();
    	 textField.setPromptText(prompt);
		return textField;
	}

	private HBox createLabelValueBox(Node... nodes) {
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER_LEFT); 
        return hbox;
    }

	
	private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
	
}
