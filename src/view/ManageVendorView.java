package view;

import controller.VendorController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Vendor;
import util.Response;
import util.SessionManager;

public class ManageVendorView {

    private Label successLabel;
    private Label errorLabel;
    private TextField productField;
    private TextField descField;
    private GridPane grid;

    public void start(Stage primaryStage) throws Exception {
        successLabel = createLabel(Color.GREEN);
        errorLabel = createLabel(Color.RED);
        grid = createGridPane();
        grid.add(errorLabel, 1, 4);
        grid.add(successLabel, 1, 4);
        show(primaryStage);
    }

    public void show(Stage primaryStage) {
        VBox root = createLayout(primaryStage);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Manage Vendor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLayout(Stage primaryStage) {
        grid = createGridPane();
        errorLabel = new Label();
        errorLabel.setVisible(false);
        errorLabel.setStyle("-fx-text-fill: red;");
        
        successLabel = new Label();
        successLabel.setVisible(false);
        successLabel.setStyle("-fx-text-fill: green;");
        Label productLabel = new Label("Product Name:");
        Label descLabel = new Label("Product Description:");
        productField = createTextField("Enter Product Name");
        descField = createTextField("Enter Description");
        HBox productBox = createLabelValueBox(productLabel, productField);
        HBox descBox = createLabelValueBox(descLabel, descField);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> save(productField, descField));
        Button backButton = new Button("Home");
        backButton.setOnAction(event -> new VGHomeView().show(primaryStage));
        HBox buttonBox = createLabelValueBox(backButton, saveButton);

        grid.add(productBox, 0, 0);
        grid.add(descBox, 0, 1);
        grid.add(buttonBox, 0, 2);
        VBox vbox = new VBox(grid);
        vbox.setAlignment(Pos.CENTER);

        loadVendorData();

        return vbox;
    }

    private void loadVendorData() {
        if (productField == null || descField == null) {
            return;
        }

        VendorController vc = new VendorController();
        if (SessionManager.getInstance().getCurrentUser() == null) {
            return;
        }
        
        String userId = SessionManager.getInstance().getCurrentUser().getUser_id();
        if (userId == null) {
            return;
        }

        Response<Vendor> response = vc.getVendor(userId);
        if (response == null) {
            return;
        }

        Vendor vendor = response.getData();
        if (vendor != null) {
            productField.setText(vendor.getProduct_name());
            descField.setText(vendor.getProduct_desc());
        }
    }


    private void save(TextField productField, TextField descField) {
        if (productField == null || descField == null) {
            errorLabel.setText("Fields cannot be null");
            errorLabel.setVisible(true);
            successLabel.setVisible(false);
            return;
        }

        String description = descField.getText();
        String productName = productField.getText();
        
        // Add null check for current user
        if (SessionManager.getInstance().getCurrentUser() == null) {
            errorLabel.setText("No user logged in");
            errorLabel.setVisible(true);
            successLabel.setVisible(false);
            return;
        }
        
        String userId = SessionManager.getInstance().getCurrentUser().getUser_id();
        if (userId == null) {
            errorLabel.setText("Invalid user ID");
            errorLabel.setVisible(true);
            successLabel.setVisible(false);
            return;
        }

        VendorController vc = new VendorController();
        try {
            Response<Vendor> response = vc.manageVendor(description, productName, userId);
            System.out.println(response.getMessage());

            if (response.isSuccess()) {
                successLabel.setText(response.getMessage());
                successLabel.setVisible(true);
                errorLabel.setVisible(false);
            } else {
                errorLabel.setText(response.getMessage());
                errorLabel.setVisible(true);
                successLabel.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("An error occurred while updating vendor data.");
            errorLabel.setVisible(true);
            successLabel.setVisible(false);
        }
    }

    private TextField createTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        return textField;
    }

    private HBox createLabelValueBox(Node... nodes) {
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private Label createLabel(Color color) {
        Label label = new Label();
        label.setTextFill(color);
        return label;
    }
}
