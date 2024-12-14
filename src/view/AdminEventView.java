package view;


import java.util.List;

import controller.AdminController;
import controller.EventOrganizerController;
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
import util.Response;

public class AdminEventView{
	
	private TableView<Event> tableView;
	private Stage primaryStage;
	private VBox root;

	public void show(Stage primaryStage) {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
        
		VBox root = createLayout(primaryStage);
		Scene scene = new Scene (root, 1200, 800);
		primaryStage.setTitle("View All Events");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		loadData();
	}

	private VBox createLayout(Stage primaryStage) {
		// TODO Auto-generated method stub
		
		GridPane grid = createGridPane();
		TableView<Event> eventTable = createTable();
		Label eventLabel = new Label();
		 Button homeBtn = new Button("Home");
	        homeBtn.setOnAction(event ->{
	        	new AdminHomeView().show(primaryStage);
	        });
		eventLabel.setText("Admin Event View");
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
		
		TableColumn<Event, Void> actionCol = new TableColumn<>("Action");
	    Callback<TableColumn<Event, Void>, TableCell<Event, Void>> cellFactory = 
	            new Callback<TableColumn<Event, Void>, TableCell<Event, Void>>() {
	                @Override
	                public TableCell<Event, Void> call(final TableColumn<Event, Void> param) {
	                    return new TableCell<Event, Void>() {

	                        private final Button deleteBtn = new Button("Delete");

	                        {
	                            deleteBtn.setOnAction(e -> {
	                                Event selectedItem = getTableView().getItems().get(getIndex());
	                                selectedItem.deleteEvent(selectedItem.getEvent_id());
	                                tableView.getItems().remove(selectedItem);
	                            });

	                        }

	                        @Override
	                        protected void updateItem(Void item, boolean empty) {
	                            super.updateItem(item, empty);
	                            if (empty) {
	                                setGraphic(null);
	                            } else {
	                                setGraphic(deleteBtn);
	                            }
	                        }
	                    
	                   };
	               }
	    };
	        
		 tableView = new TableView<>();
	        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	        actionCol.setCellFactory(cellFactory);
	        tableView.getColumns().addAll(
	            createColumn("Event ID", "event_id"),
	            createColumn("Event Name", "event_name"),
	            createColumn("Event Date", "event_date"),
	            createColumn("Location", "event_location"),
	            createColumn("Description", "event_description"),
	            createColumn("Organizer ID", "organizer_id"),
	            actionCol
	        );
	        
 
		    return tableView;
	}
	
    private TableColumn<Event, String> createColumn(String header, String property) {
        TableColumn<Event, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }
	
	public void loadData() {
        AdminController controller = new AdminController();
        Response<List<Event>> response = controller.viewAllEvents();

        if (response.isSuccess()) {
            ObservableList<Event> eventData = FXCollections.observableArrayList(response.getData());
            tableView.setItems(eventData);
        }
    }
	
	
}
