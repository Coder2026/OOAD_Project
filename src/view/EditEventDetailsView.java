package view;

import java.time.format.DateTimeFormatter;
import java.util.List;

import controller.EventOrganizerController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Event;
import model.Guest;
import model.User;
import model.Vendor;
import util.Response;

public class EditEventDetailsView {

    private VBox root;
    private Stage primaryStage;
    private String eventId;
    private Event selectedEvent;
    private TableView<User> tableView;
    private EventOrganizerController ec;

    public void show(Stage primaryStage, String eventId, Event selectedEvent) {
        this.primaryStage = primaryStage;
        this.eventId = eventId;
        this.selectedEvent = selectedEvent;
        init();
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Edit Event Details");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        fetchEventDetails(eventId);
    }

    private void init() {
        GridPane grid = createGridPane();

        Label eventDetailsLabel = new Label("Event Details");
        GridPane.setHalignment(eventDetailsLabel, HPos.CENTER);

        Label eventNameLabel = new Label("Event Name:");
        Label eventDateLabel = new Label("Event Date:");
        Label successLabel = createErrorLabel(Color.GREEN);
        Label errorLabel = createErrorLabel(Color.RED);
        Label eventLocationLabel = new Label("Event Location:");
        Label eventDescriptionLabel = new Label("Event Description:");

        TextField editName = new TextField();
        editName.setPromptText("Change event name here!");
        Label eventDateValue = new Label(selectedEvent.getEvent_date());
        Label eventLocationValue = new Label(selectedEvent.getEvent_location());
        Label eventDescriptionValue = new Label(selectedEvent.getEvent_description());

        tableView = createTable();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> save(eventId, editName, successLabel, errorLabel));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> new OrganizedEventView().show(primaryStage, selectedEvent.getOrganizer_id()));

        Button addVendorButton = new Button("Add Vendors");
        addVendorButton.setOnAction(event -> new AddVendorGuestView().show(primaryStage, "Vendors", eventId));

        Button addGuestButton = new Button("Add Guests");
        addGuestButton.setOnAction(event -> new AddVendorGuestView().show(primaryStage, "Guests", eventId));

        HBox buttonBox = new HBox(10, addVendorButton, addGuestButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        HBox eventNameBox = createLabelValueBox(eventNameLabel, editName, saveButton);
        HBox eventDateBox = createLabelValueBox(eventDateLabel, eventDateValue);
        HBox eventLocationBox = createLabelValueBox(eventLocationLabel, eventLocationValue);
        HBox eventDescriptionBox = createLabelValueBox(eventDescriptionLabel, eventDescriptionValue);

        grid.add(eventDetailsLabel, 0, 0);
        grid.add(eventNameBox, 0, 1);
        grid.add(eventDateBox, 0, 2);
        grid.add(eventLocationBox, 0, 3);
        grid.add(eventDescriptionBox, 0, 4);
        grid.add(successLabel, 0, 5);
        grid.add(errorLabel, 0, 5);
        grid.add(new Label("Attendee Lists (Vendor and Guest):"), 0, 6);
        grid.add(tableView, 0, 7, 2, 1); 
        grid.add(buttonBox, 0, 8);
        grid.add(backButton, 0, 9);

        root = new VBox(grid);
        root.setAlignment(Pos.CENTER);
    }

    private Label createErrorLabel(Color color) {
        Label errorLabel = new Label();
        errorLabel.setTextFill(color);
        return errorLabel;
    }

    private HBox createLabelValueBox(Node... nodes) {
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER_LEFT); 
        return hbox;
    }

    private void save(String eventId, TextField editName, Label successLabel, Label errorLabel) {
        String name = editName.getText();
        ec = new EventOrganizerController();
        Response<String> eventResponse = ec.editEventName(eventId, name);
        
        if (eventResponse.isSuccess()) {
            successLabel.setText(eventResponse.getMessage());
        } else {
            errorLabel.setText(eventResponse.getMessage());
        }
    }

    private void fetchEventDetails(String eventId) {
        ec = new EventOrganizerController();
        Response<List<User>> eventResponse = ec.viewOrganizedEventDetails(eventId);
        
        if (eventResponse.isSuccess()) {
            ObservableList<User> eventData = FXCollections.observableArrayList(eventResponse.getData());
            tableView.setItems(eventData);
        }
    }

    private TableView<User> createTable() {
        tableView = new TableView<>();
        tableView.setPrefHeight(200);

        TableColumn<User, String> guestColumn = new TableColumn<>("Guest Name");
        guestColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        guestColumn.setCellFactory(column -> new TableCell<User, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || !(getTableRow().getItem() instanceof Guest)) {
                    setText(null);
                } else {
                    setText(item);
                }
            }
        });
        guestColumn.setMinWidth(100);

        TableColumn<User, String> vendorColumn = new TableColumn<>("Vendor Name");
        vendorColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        vendorColumn.setCellFactory(column -> new TableCell<User, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || !(getTableRow().getItem() instanceof Vendor)) {
                    setText(null);
                } else {
                    setText(item);
                }
            }
        });
        vendorColumn.setMinWidth(100);

        tableView.getColumns().addAll(guestColumn, vendorColumn);
        return tableView;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
}
