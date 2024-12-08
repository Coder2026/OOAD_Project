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

public class AdminUserView extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		show(primaryStage);
	}

	public void show(Stage primaryStage) {
		
		VBox root = createLayout(primaryStage);
		Scene scene = new Scene (root, 1200, 800);
		primaryStage.setTitle("View All Users");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private VBox createLayout(Stage primaryStage) {
		
		GridPane grid = createGridPane();
		TableView<Event> eventTable = createTable();
		Label eventLabel = new Label();
		 Button homeBtn = new Button("Home");
	        homeBtn.setOnAction(event ->{
	        	new AdminHomeView().show(primaryStage);
	        });
		eventLabel.setText("Admin User View");
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

		  TableColumn<Event, String> userIdCol = new TableColumn<>("User ID");
		    userIdCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));

		    TableColumn<Event, String> userNameCol = new TableColumn<>("User Name");
		    userNameCol.setCellValueFactory(new PropertyValueFactory<>("user_name"));

		    TableColumn<Event, String> userEmailCol = new TableColumn<>("User Email");
		    userEmailCol.setCellValueFactory(new PropertyValueFactory<>("user_email"));
		    
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

		    tableView.getColumns().addAll(userIdCol, userNameCol, userEmailCol, actionCol);
		    
		    return tableView;
	}
}
