package view;


import java.util.List;

import controller.AdminController;
import controller.VendorController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Event;
import model.User;
import util.Response;

public class VendorViewAcceptedInvitation{

	private TableView<Event> tableView;
	private Stage primaryStage;
	private String id;
	private VBox root;

	public void show(Stage primaryStage, String Id) {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.id = id;
		VBox root = createLayout(primaryStage);
		Scene scene = new Scene (root, 1200, 800);
		primaryStage.setTitle("View Accepted Events");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		loadData(Id);
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

	        tableView.getColumns().addAll(
	            createColumn("Event ID", "event_id"),
	            createColumn("Event Name", "event_name"),
	            createColumn("Event Date", "event_date"),
	            createColumn("Event Location", "event_location"),
	            createColumn("Event Description", "event_description"),
	            createColumn("Event Organizer", "event_organizer")
	        );

		    return tableView;
	}

	private TableColumn<Event, String> createColumn(String header, String property) {
        TableColumn<Event, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }
	
	public void loadData(String eventId) {
        VendorController controller = new VendorController();
        //Response<List<User>> response = controller.getAllAcceptedEvent(eventId);

//        if (response.isSuccess()) {
//            ObservableList<User> eventData = FXCollections.observableArrayList(response.getData());
//            //tableView.setItems(eventData);
//
//        }
    }
	
	
	
}
