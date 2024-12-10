package view;


import java.util.List;

import controller.AdminController;
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
import model.User;
import model.User;
import util.Response;

public class AdminUserView {
	
	private TableView<User> tableView;
	private Stage primaryStage;
	private String id;
	private VBox root;

	public void show(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
        this.id = id;
		VBox root = createLayout(primaryStage);
		Scene scene = new Scene (root, 1200, 800);
		primaryStage.setTitle("View All Users");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		loadData();
	}

	private VBox createLayout(Stage primaryStage) {
		
		GridPane grid = createGridPane();
		TableView<User> eventTable = createTable();
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

	

	private TableView<User> createTable() {
		
		TableColumn<User, Void> actionCol = new TableColumn<>("Action");
	    Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = 
	            new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
	                @Override
	                public TableCell<User, Void> call(final TableColumn<User, Void> param) {
	                    return new TableCell<User, Void>() {

	                        private final Button deleteBtn = new Button("Delete");

	                        {
	                        	deleteBtn.setOnAction(e -> {
	                                User selectedItem = getTableView().getItems().get(getIndex());
	                                selectedItem.deleteUser(selectedItem.getUser_id());
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
	            createColumn("User ID", "user_id"),
	            createColumn("User Name", "user_name"),
	            createColumn("User Email", "user_email"),
	            actionCol
	        );
	        
		   
		    return tableView;
	}
	
	private TableColumn<User, String> createColumn(String header, String property) {
        TableColumn<User, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }
	
	public void loadData() {
        AdminController controller = new AdminController();
        Response<List<User>> response = controller.getAllUser();

        if (response.isSuccess()) {
            ObservableList<User> userData = FXCollections.observableArrayList(response.getData());
            tableView.setItems(userData);

        }
    }
}
