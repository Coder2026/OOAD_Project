package view;

import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EventDetailsView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        show(primaryStage, "event123");
    }

    public void show(Stage primaryStage, String eventId) {
        VBox root = createLayout(primaryStage, eventId);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("View Event Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLayout(Stage primaryStage, String eventId) {
        GridPane grid = createGridPane();

        Label eventDetailsLabel = new Label("Event Details");
        GridPane.setHalignment(eventDetailsLabel, HPos.CENTER);

        Label eventNameLabel = new Label("Event Name:");
        Label eventDateLabel = new Label("Event Date:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
 
        Label eventLocationLabel = new Label("Event Location:");
        Label eventDescriptionLabel = new Label("Event Description:");

      
        Label eventNameValue = new Label();
        Label eventDateValue = new Label();
        Label eventLocationValue = new Label();
        Label eventDescriptionValue = new Label();
        
        
        TableView<String> attendeeTable = createTable();

      
        Button editEventButton = new Button("Edit Event");
        editEventButton.setOnAction(event -> {
            System.out.println("Edit Event button clicked.");
            new EditEventDetailsView().show(primaryStage, "event123");
        });
        
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
        	
        	new OrganizedEventView().show(primaryStage);
         
        });
   
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(editEventButton, backButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        
        HBox eventNameBox = createLabelValueBox(eventNameLabel, eventNameValue);
        HBox eventDateBox = createLabelValueBox(eventDateLabel, eventDateValue);
        HBox eventLocationBox = createLabelValueBox(eventLocationLabel, eventLocationValue);
        HBox eventDescriptionBox = createLabelValueBox(eventDescriptionLabel, eventDescriptionValue);
    
//        Event event = fetchEventDetails(eventId);

   
//        eventNameValue.setText(event.getName());
//        eventDateValue.setText(event.getDate());
//        eventLocationValue.setText(event.getLocation());
//        eventDescriptionValue.setText(event.getDescription());

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
    
    private HBox createLabelValueBox(Node... nodes) {
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER_LEFT); 
        return hbox;
    }

    private TableView<String> createTable() {
    	 TableView<String> table = new TableView<>();
         table.setPrefHeight(200); 

         TableColumn<String, String> vendorColumn = new TableColumn<>("Vendor");
         vendorColumn.setMinWidth(100);

         TableColumn<String, String> guestColumn = new TableColumn<>("Guest");
         guestColumn.setMinWidth(100);

         table.getColumns().addAll(vendorColumn, guestColumn);
         return table;
	}

	private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

}

