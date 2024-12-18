package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.EventController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Response;

public class CreateEventView {

    private VBox root;
    private GridPane grid;
    private TextField nameField;
    private DatePicker datePicker;
    private TextField locationField;
    private TextField descField;
    private Label successLabel;
    private Label errorLabel;
    private Button submitButton;
    private Button homeButton;
    private Stage primaryStage;
    private String id;

    public void show(Stage primaryStage, String id) {
        this.primaryStage = primaryStage;
        this.id = id;
        init();

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Create Events");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void init() {
        grid = createGridLayout();
        nameField = createTextField("Enter Event Name");
        locationField = createTextField("Enter Event Location");
        descField = createTextField("Enter Event Description");
        datePicker = new DatePicker();
        successLabel = createLabel(Color.GREEN);
        errorLabel = createLabel(Color.RED);
        submitButton = createSubmitButton();
        homeButton = createHomeButton();

        addGridRow(grid, 0, new Label("Event Name:"), nameField);
        addGridRow(grid, 1, new Label("Event Date:"), datePicker);
        addGridRow(grid, 2, new Label("Location:"), locationField);
        addGridRow(grid, 3, new Label("Description:"), descField);
        grid.add(errorLabel, 1, 4);
        grid.add(successLabel, 1, 4);
        addGridRow(grid, 5, homeButton, submitButton);

        root = new VBox(grid);
        root.setAlignment(Pos.CENTER);
    }

    private Button createHomeButton() {
        Button homeButton = new Button("Home");
        homeButton.setOnAction(event -> new EOHomeView().show(primaryStage, id));
        return homeButton;
    }

    private Button createSubmitButton() {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = datePicker.getValue();
            String formattedDate = "";
            if (date != null) {
                formattedDate = formatter.format(date);
            } 
            String location = locationField.getText();
            String description = descField.getText();

            EventController ec = new EventController();
            Response<String> response = ec.createEvent(name, formattedDate, location, description, id);

            if (response.isSuccess()) {
                nameField.clear();
                datePicker.setValue(null);
                locationField.clear();
                descField.clear();
                errorLabel.setVisible(false);
                successLabel.setText(response.getMessage());
            } else {
                errorLabel.setText(response.getMessage());
            }
        });
        return submitButton;
    }

    private void addGridRow(GridPane grid, int rowIndex, Control control1, Control control2) {
        grid.add(control1, 0, rowIndex);
        grid.add(control2, 1, rowIndex);
    }

    private GridPane createGridLayout() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private TextField createTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        return textField;
    }

    private Label createLabel(Color color) {
        Label errorLabel = new Label();
        errorLabel.setTextFill(color);
        return errorLabel;
    }
}
