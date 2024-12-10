package view;

import java.util.List;

import controller.EventOrganizerController;
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
import model.User;
import util.Response;

public class AddVendorGuestView {

	EventOrganizerController ec;

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
	          
	          //  new EditEventDetailsView().show(primaryStage, "event123", "123");
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
		
		ec = new EventOrganizerController();
		Response<List<User>> response = ec.getGuests();
		ObservableList<CheckBox> guestItems = FXCollections.observableArrayList();
		 if (response.isSuccess() && response.getData() != null) {

		        for (User guest : response.getData()) {
		      
		            CheckBox checkBox = new CheckBox(guest.getUser_name());
		            
		            checkBox.setUserData(guest);
		            
		            guestItems.add(checkBox);
		        }
		    } else {
		     
		        System.out.println("Failed to fetch guests: " + response.getMessage());
		    }

			    ListView<CheckBox> listView = new ListView<>(guestItems);  
			    listView.setPrefHeight(200);  
			    return listView; 
		
		
	}

	private ListView<CheckBox> getVendor() {
		
		ec = new EventOrganizerController();
		Response<List<User>> response = ec.getVendors();
		ObservableList<CheckBox> vendorItems = FXCollections.observableArrayList();
		 if (response.isSuccess() && response.getData() != null) {

		        for (User vendor : response.getData()) {
		      
		            CheckBox checkBox = new CheckBox(vendor.getUser_name());
	
		            checkBox.setUserData(vendor);
		          
		            vendorItems.add(checkBox);
		        }
		    } else {
		     
		        System.out.println("Failed to fetch guests: " + response.getMessage());
		    }

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
