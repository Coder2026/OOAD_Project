package view;

import java.util.List;

import controller.EventOrganizerController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;
import util.Response;

public class AddVendorGuestView {

    private EventOrganizerController ec = new EventOrganizerController();
    private Stage primaryStage;
    private String listType;
    private String eventId;

    public void show(Stage primaryStage, String listType, String eventId) {
        this.primaryStage = primaryStage;
        this.listType = listType;
        this.eventId = eventId;
        VBox root = createLayout();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Add " + listType);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLayout() {
        GridPane grid = createGridPane();
        Label titleLabel = new Label("List of " + listType);
        Label successLabel = createErrorLabel(Color.GREEN);
        Label errorLabel = createErrorLabel(Color.RED);

        ListView<CheckBox> listView = listType.equalsIgnoreCase("Vendors") ? getVendor() : getGuest();

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> primaryStage.close());

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> add(listView, successLabel, errorLabel));

        grid.add(titleLabel, 0, 0);
        grid.add(listView, 0, 1);
        grid.add(errorLabel, 0, 2);
        grid.add(successLabel, 0, 2);
        grid.add(backButton, 0, 3);
        grid.add(addButton, 0, 4);

        VBox vbox = new VBox(grid);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private void add(ListView<CheckBox> listView, Label successLabel, Label errorLabel) {
        for (CheckBox cb : listView.getItems()) {
            if (cb.isSelected()) {
                User user = (User) cb.getUserData();
                Response<String> invitationResponse = ec.sendInvitation(eventId, user.getUser_id(), user.getUser_role());
                if (invitationResponse.isSuccess()) {
                    successLabel.setText(invitationResponse.getMessage());
                    errorLabel.setVisible(false);
                } else {
                    errorLabel.setText(invitationResponse.getMessage());
                    successLabel.setVisible(false);
                }
            }
        }
    }

    private Label createErrorLabel(Color color) {
        Label label = new Label();
        label.setTextFill(color);
        return label;
    }

    private ListView<CheckBox> getGuest() {
        return createListView(ec.getGuests(), "guest");
    }

    private ListView<CheckBox> getVendor() {
        return createListView(ec.getVendors(), "vendor");
    }

    private ListView<CheckBox> createListView(Response<List<User>> response, String role) {
        ObservableList<CheckBox> items = FXCollections.observableArrayList();
        if (response.isSuccess() && response.getData() != null) {
            for (User user : response.getData()) {
                CheckBox checkBox = new CheckBox(user.getUser_name());
                user.setUser_role(role);  
                checkBox.setUserData(user);
                items.add(checkBox);
            }
        } else {
            System.out.println("Failed to fetch data: " + response.getMessage());
        }
        ListView<CheckBox> listView = new ListView<>(items);
        listView.setPrefHeight(200);
        return listView;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
}
