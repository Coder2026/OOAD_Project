package view;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Event;

public class VendorViewAcceptedInvitation{

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		show(primaryStage);
	}

	public void show(Stage primaryStage) {
		// TODO Auto-generated method stub
		VBox root = createLayout(primaryStage);
		Scene scene = new Scene (root, 1200, 800);
		primaryStage.setTitle("View Accepted Events");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private VBox createLayout(Stage primaryStage) {
		// TODO Auto-generated method stub
		GridPane grid = createGridPane();
		TableView<Event> eventTable = createTable();
		Label eventLabel = new Label();
		 Button homeBtn = new Button("Home");
	        homeBtn.setOnAction(event ->{
	        	new VendorHomeView().show(primaryStage);
	        });
		eventLabel.setText("View Accepted Events");
		GridPane.setHalignment(eventLabel, HPos.CENTER);
		grid.add(eventLabel, 0, 0);
		grid.add(eventTable, 0, 1);
		grid.add(homeBtn, 0, 2);
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

	

	private TableView<Event> createTable() {
	        
		 TableView<Event> tableView = new TableView<>();

		  TableColumn<Event, String> eventIdCol = new TableColumn<>("Event ID");
		    eventIdCol.setCellValueFactory(new PropertyValueFactory<>("event_id"));

		    TableColumn<Event, String> eventNameCol = new TableColumn<>("Event Name");
		    eventNameCol.setCellValueFactory(new PropertyValueFactory<>("event_name"));

		    TableColumn<Event, String> eventDateCol = new TableColumn<>("Event Date");
		    eventDateCol.setCellValueFactory(new PropertyValueFactory<>("event_date"));

		    TableColumn<Event, String> eventLocationCol = new TableColumn<>("Event Location");
		    eventLocationCol.setCellValueFactory(new PropertyValueFactory<>("event_location"));
		    
		    TableColumn<Event, String> eventDescriptionCol = new TableColumn<>("Event Description");
		    eventDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("event_description"));
		    
		    TableColumn<Event, String> eventOrganizerCol = new TableColumn<>("Event Organizer");
		    eventOrganizerCol.setCellValueFactory(new PropertyValueFactory<>("event_organizer"));
		    
		    

		    tableView.getColumns().addAll(eventIdCol, eventNameCol, eventDateCol, eventLocationCol, eventDescriptionCol, eventOrganizerCol);
		    
		    return tableView;
	}

	
	
	
	
}