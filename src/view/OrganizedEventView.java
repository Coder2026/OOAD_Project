package view;

import java.util.List;

import controller.EventOrganizerController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;
import util.Response;

public class OrganizedEventView {

    private TableView<Event> tableView;
    private Stage primaryStage;
    private String id;
    private VBox root;

    public void show(Stage primaryStage, String id) {
        this.primaryStage = primaryStage;
        this.id = id;
        init();
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("View Organized Events");
        primaryStage.setScene(scene);
        primaryStage.show();

        loadData(id);
    }

    private void init() {
        GridPane grid = createGridPane();
        tableView = createTable();

        Label eventLabel = new Label("Organized Event View");
        GridPane.setHalignment(eventLabel, HPos.CENTER);

        Button homeBtn = new Button("Home");
        homeBtn.setOnAction(event -> new EOHomeView().show(primaryStage, id));

        TableColumn<Event, Void> buttonColumn = createActionColumn();

        tableView.getColumns().add(buttonColumn);

        grid.add(eventLabel, 0, 0);
        grid.add(tableView, 0, 1);
        grid.add(homeBtn, 0, 2);

        root = new VBox(grid);
        root.setAlignment(Pos.CENTER);
    }

    private TableColumn<Event, Void> createActionColumn() {
        TableColumn<Event, Void> buttonColumn = new TableColumn<>("Actions");
        buttonColumn.setCellFactory(column -> new TableCell<Event, Void>() {
            private final Button viewButton = new Button("View List");

            {
                viewButton.setOnAction(event -> {
                    Event selectedEvent = getTableView().getItems().get(getIndex());
                    if (selectedEvent != null) {
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
        });
        return buttonColumn;
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
        tableView.getColumns().addAll(
            createColumn("Event ID", "event_id"),
            createColumn("Event Name", "event_name"),
            createColumn("Event Date", "event_date"),
            createColumn("Location", "event_location"),
            createColumn("Description", "event_description"),
            createColumn("Organizer ID", "organizer_id")
        );
        return tableView;
    }

    private TableColumn<Event, String> createColumn(String header, String property) {
        TableColumn<Event, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }

    public void loadData(String userId) {
        EventOrganizerController controller = new EventOrganizerController();
        Response<List<Event>> response = controller.viewOrganizedEvents(userId);

        if (response.isSuccess()) {
            ObservableList<Event> eventData = FXCollections.observableArrayList(response.getData());
            tableView.setItems(eventData);
        }
    }
}
