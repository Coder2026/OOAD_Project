package view;

import java.time.format.DateTimeFormatter;
import java.util.List;

import controller.EventOrganizerController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Event;
import model.User;
import util.Response;

public class EventDetailsView {

	private TableView<User> tableView;
	
    public void show(Stage primaryStage, String eventId, Event selectedEvent) {
        VBox root = createLayout(primaryStage, eventId, selectedEvent);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("View Event Details");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        fetchEventDetails(eventId);
    }

    private VBox createLayout(Stage primaryStage, String eventId, Event selectedEvent) {
        GridPane grid = createGridPane();

        Label eventDetailsLabel = new Label("Event Details");
        GridPane.setHalignment(eventDetailsLabel, HPos.CENTER);

        Label eventNameLabel = new Label("Event Name:");
        Label eventDateLabel = new Label("Event Date:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 
        Label eventLocationLabel = new Label("Event Location:");
        Label eventDescriptionLabel = new Label("Event Description:");

      
        Label eventNameValue = new Label();
        Label eventDateValue = new Label();
        Label eventLocationValue = new Label();
        Label eventDescriptionValue = new Label();
        
        
    
        TableView<User> attendeeTable = createTable();

      
        Button editEventButton = new Button("Edit Event");
        editEventButton.setOnAction(event -> {
            System.out.println("Edit Event button clicked.");
            new EditEventDetailsView().show(primaryStage, eventId);
        });
        
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
        	
        	new OrganizedEventView().show(primaryStage, selectedEvent.getOrganizer_id());
         
        });
   
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(editEventButton, backButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        
        HBox eventNameBox = createLabelValueBox(eventNameLabel, eventNameValue);
        HBox eventDateBox = createLabelValueBox(eventDateLabel, eventDateValue);
        HBox eventLocationBox = createLabelValueBox(eventLocationLabel, eventLocationValue);
        HBox eventDescriptionBox = createLabelValueBox(eventDescriptionLabel, eventDescriptionValue);
    

   
       eventNameValue.setText(selectedEvent.getEvent_name());
       eventDateValue.setText(selectedEvent.getEvent_date());
       eventLocationValue.setText(selectedEvent.getEvent_location());
        eventDescriptionValue.setText(selectedEvent.getEvent_description());

        grid.add(eventDetailsLabel, 0, 0);
        grid.add(eventNameBox, 0, 1);
      
        grid.add(eventDateBox, 0, 2);
     
        grid.add(eventLocationBox, 0, 3);
   
        grid.add(eventDescriptionBox, 0, 4);
      
        grid.add(new Label("Attendee Lists (Vendor and Guest):"), 0, 5);
        grid.add(attendeeTable, 0, 6); 
        grid.add(buttonBox, 0, 7);
        
        VBox vbox = new VBox(grid);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
    
    private void fetchEventDetails(String eventId) {
		EventOrganizerController ec = new EventOrganizerController();
		 Response<List<User>> eventResponse = ec.viewOrganizedEventDetails(eventId);
		 
		 if (eventResponse.isSuccess()) {
	            ObservableList<User> eventData = FXCollections.observableArrayList(eventResponse.getData());
	            tableView.setItems(eventData);
	        }
	}

	private HBox createLabelValueBox(Node... nodes) {
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER_LEFT); 
        return hbox;
    }

	


	    private TableView<User> createTable() {
	        tableView = new TableView<>(); 
	        tableView.setPrefHeight(200);

	        TableColumn<User, String> vendorColumn = new TableColumn<>("Name");
	        vendorColumn.setCellValueFactory(new PropertyValueFactory<>("user_name"));
	        vendorColumn.setMinWidth(100);

	        TableColumn<User, String> guestColumn = new TableColumn<>("Role");
	        guestColumn.setCellValueFactory(new PropertyValueFactory<>("user_role"));
	        guestColumn.setMinWidth(100);

	        tableView.getColumns().addAll(vendorColumn, guestColumn);
	        return tableView;
	    }
	

	private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

}

