package view;

import java.util.List;

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
import model.Event;
import util.Response;

public class OrganizedEventView {
    
    private TableView<Event> tableView;

    public void show(Stage primaryStage, String id) {
        VBox root = createLayout(primaryStage, id);
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("View Organized Events");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        loadData(id);
    }

    private VBox createLayout(Stage primaryStage, String id) {
        GridPane grid = createGridPane();
        tableView = createTable(); 
        
        Label eventLabel = new Label("Organized Event View");
        GridPane.setHalignment(eventLabel, HPos.CENTER);
        
        Button homeBtn = new Button("Home");
        homeBtn.setOnAction(event -> {
            new EOHomeView().show(primaryStage, id);
        });

       
        TableColumn<Event, Void> buttonColumn = new TableColumn<>("Actions");
        buttonColumn.setCellFactory(column -> {
            return new TableCell<Event, Void>() {
                private final Button viewButton = new Button("View");
                
                {
                    viewButton.setOnAction(event -> {
                       
                        Event selectedEvent = getTableView().getItems().get(getIndex());
                        
                        if (selectedEvent != null) {
                            System.out.println("Selected Event before sending: " + selectedEvent); 
                            System.out.println("Event ID: " + selectedEvent.getEvent_id());
                            System.out.println("Event Name: " + selectedEvent.getEvent_name());
                            new EventDetailsView().show(primaryStage, selectedEvent.getEvent_id(), selectedEvent);
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
            };
        });

        
        tableView.getColumns().add(buttonColumn);
        
        grid.add(eventLabel, 0, 0);
        grid.add(tableView, 0, 1);
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
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<Event, String> eventIdColumn = new TableColumn<>("Event ID");
        eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        
        TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
    
        TableColumn<Event, String> eventDateColumn = new TableColumn<>("Event Date");
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("event_date"));
        
        TableColumn<Event, String> eventLocationColumn = new TableColumn<>("Location");
        eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("event_location"));
        
        TableColumn<Event, String> eventDescriptionColumn = new TableColumn<>("Description");
        eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("event_description"));
    
        TableColumn<Event, String> organizerIdColumn = new TableColumn<>("Organizer ID");
        organizerIdColumn.setCellValueFactory(new PropertyValueFactory<>("organizer_id"));
        
        tableView.getColumns().addAll(
            eventIdColumn, 
            eventNameColumn, 
            eventDateColumn, 
            eventLocationColumn, 
            eventDescriptionColumn, 
            organizerIdColumn
        );
        
        return tableView;
    }
    
    public void loadData(String userId) {
        EventOrganizerController controller = new EventOrganizerController();
        Response<List<Event>> response = controller.viewOrganizedEvents(userId);
        
        if (response.isSuccess()) {
            ObservableList<Event> eventData = FXCollections.observableArrayList(response.getData());
            tableView.setItems(eventData);
        }
    }

    public void refreshTable(String userId) {
        loadData(userId);
    }
}
