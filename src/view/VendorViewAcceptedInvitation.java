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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Event;
import model.Invitation;
import model.User;
import util.Response;
import util.SessionManager;

public class VendorViewAcceptedInvitation{

	private TableView<Event> tableView;
	private Stage primaryStage;
	private String id;
	private VBox root;
	private Label successLabel;
    private Label errorLabel;
    private TextField nameField;
    private DatePicker datePicker;
    private TextField locationField;
    private TextField descField;

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
		
		Label eventLabel = new Label("View Accepted Events");
		GridPane.setHalignment(eventLabel, HPos.CENTER);
		
		 Button homeBtn = new Button("Home");
	        homeBtn.setOnAction(event ->{
	        	new VGHomeView().show(primaryStage);
	        });
		
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

	private TableColumn<Event, Void> createDetailColumn() {
		successLabel = createErrorLabel(Color.GREEN);
        errorLabel = createErrorLabel(Color.RED);
        
        TableColumn<Event, Void> buttonColumn = new TableColumn<>("Detail");
        buttonColumn.setCellFactory(column -> new TableCell<Event, Void>() {
            private final Button viewButton = new Button("View List");

            {
                viewButton.setOnAction(event -> {
                    Event selectedEvent = getTableView().getItems().get(getIndex());
                    if (selectedEvent != null) {
                        new VendorEventDetailView().show(primaryStage, selectedEvent.getEvent_id(), selectedEvent);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewButton);
                }
            }
        });
        return buttonColumn;
    }

	private TableView<Event> createTable() {
	        
		 tableView = new TableView<>();
		 tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	        tableView.getColumns().addAll(
	            createColumn("Event ID", "event_id"),
	            createColumn("Event Name", "event_name"),
	            createColumn("Event Date", "event_date"),
	            createColumn("Event Location", "event_location"),
	            createColumn("Event Description", "event_description"),
	            createColumn("Event Organizer", "organizer_id"),
	            createDetailColumn()
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
        Response<List<Event>> response = controller.viewAcceptedInvitations(SessionManager.getInstance().getCurrentUser().getUser_email());

        if (response.isSuccess()) {
            ObservableList<Event> eventData = FXCollections.observableArrayList(response.getData());
            tableView.setItems(eventData);

        }
    }
	
	private Label createErrorLabel(Color color) {
        Label errorLabel = new Label();
        errorLabel.setTextFill(color);
        return errorLabel;
    }
	
	
}
