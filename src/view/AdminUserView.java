package view;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;

public class AdminUserView extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		show(primaryStage);
	}

	public void show(Stage primaryStage) {
		// TODO Auto-generated method stub
		VBox root = createLayout(primaryStage);
		Scene scene = new Scene (root, 1200, 800);
		primaryStage.setTitle("View All User");
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
	        	new EOHomeView().show(primaryStage);
	        });
		eventLabel.setText("Organized Event View");
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
		    eventIdCol.setCellValueFactory(new PropertyValueFactory<>("eventId"));

		    TableColumn<Event, String> eventNameCol = new TableColumn<>("Event Name");
		    eventNameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));

		    TableColumn<Event, String> eventDateCol = new TableColumn<>("Event Date");
		    eventDateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));

		    TableColumn<Event, String> eventLocationCol = new TableColumn<>("Event Location");
		    eventLocationCol.setCellValueFactory(new PropertyValueFactory<>("eventLocation"));

		    tableView.getColumns().addAll(eventIdCol, eventNameCol, eventDateCol, eventLocationCol);
		    
		    return tableView;
	}

	
	
	
	
}
