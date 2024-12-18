package view;

import controller.VendorController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Vendor;
import util.Response;
import util.SessionManager;

public class ManageVendorView{
	
    private Label successLabel;
    private Label errorLabel;
    private GridPane grid;


	public void start(Stage primaryStage) throws Exception {
        successLabel = createLabel(Color.GREEN);
        errorLabel = createLabel(Color.RED);
        grid.add(errorLabel, 1, 4);
        grid.add(successLabel, 1, 4);
		show(primaryStage);
		
	}

	public void show(Stage primaryStage) {
		
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
	        	new VGHomeView().show(primaryStage);
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
    	String description = descField.getText();
    	String product_name = productField.getText();
    	
		VendorController vc = new VendorController();
		Response<Vendor> response = vc.manageVendor(description, product_name, SessionManager.getInstance().getCurrentUser().getUser_id());
		System.out.println(response.getMessage());
		
		if(response.isSuccess()) {
			productField.clear();
            descField.clear();
//            errorLabel.setVisible(false);
//            successLabel.setText(response.getMessage());
		}
    	else {
    		//errorLabel.setText(response.getMessage());
    	}
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
	
    private Label createLabel(Color color) {
        Label errorLabel = new Label();
        errorLabel.setTextFill(color);
        return errorLabel;
    }
}
