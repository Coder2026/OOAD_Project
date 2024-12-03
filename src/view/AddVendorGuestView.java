package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddVendorGuestView extends Application {

	@Override
	   public void start(Stage primaryStage) throws Exception {
        show(primaryStage, "Vendors");
    }

    public void show(Stage primaryStage, String listType) {
    	   VBox root = createLayout(primaryStage, listType);
	        Scene scene = new Scene(root, 800, 600);
	        primaryStage.setTitle("Add" + listType);
	        primaryStage.setScene(scene);
	        primaryStage.show();
    }

	private VBox createLayout(Stage primaryStage, String listType) {
		  GridPane grid = createGridPane();
		
		  	Label titleLabel = new Label("List of " + listType);
		  	
		  	 ListView<CheckBox> listView;  
		     if (listType.equalsIgnoreCase("Vendors")) {  
		         listView = getVendor();  
		     } else {  
		         listView = getGuest();  
		     }  
	        Button backButton = new Button("Back");
	        backButton.setOnAction(event -> {
	          
	            new EditEventDetailsView().show(primaryStage, "event123");
	        });
	        Button addButton = new Button("Add");
	        addButton.setOnAction(event -> {
	          
	
	        });
	        grid.add(titleLabel, 0, 0);
	        grid.add(listView, 0, 1);
	        grid.add(backButton, 0, 3);
	        grid.add(addButton, 0, 2);
	        VBox vbox = new VBox(grid);
	        vbox.setAlignment(Pos.CENTER);
	        return vbox;
	}

	private ListView<CheckBox> getGuest() {
		// TODO Auto-generated method stub
		  ObservableList<CheckBox> guestItems = FXCollections.observableArrayList(  
			        new CheckBox("Guest 1"),  
			        new CheckBox("Guest 2"),  
			        new CheckBox("Guest 3")  
			    );  

			    ListView<CheckBox> listView = new ListView<>(guestItems);  
			    listView.setPrefHeight(200);  
			    return listView; 
		
		
	}

	private ListView<CheckBox> getVendor() {
		// TODO Auto-generated method stub

		 		ObservableList<CheckBox> vendorItems = FXCollections.observableArrayList(  
			        new CheckBox("Vendor 1"),  
			        new CheckBox("Vendor 2"),  
			        new CheckBox("Vendor 3")  
			    );  

			    ListView<CheckBox> listView = new ListView<>(vendorItems);  
			    listView.setPrefHeight(200);  
			    return listView;
		
	}

	private GridPane createGridPane() {
		   GridPane grid = new GridPane();
	        grid.setVgap(10);
	        grid.setHgap(10);
	        grid.setAlignment(Pos.CENTER);
	        return grid;
	}

}
