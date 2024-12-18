package view;

import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Response;

public class ChangeProfileView {

    private VBox root;
    private GridPane grid;
    private TextField nameField;
    private TextField emailField;
    private PasswordField oldPasswordField;
    private PasswordField newPasswordField;
    private Label successLabel;
    private Label errorLabel;
    private Button saveButton;
    private Stage primaryStage;
    private UserController uc = new UserController(); 
    
    public void show(Stage primaryStage) {
        this.primaryStage = primaryStage;

        init();

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Change Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void init() {
        grid = createGridLayout();
        nameField = new TextField();
        emailField = new TextField();
        oldPasswordField = new PasswordField();
        newPasswordField = new PasswordField();
        successLabel = createLabel(Color.GREEN);
        errorLabel = createLabel(Color.RED);
        saveButton = new Button("Save Changes");
      

        nameField.setPromptText("Enter your name");
        emailField.setPromptText("Enter your email");
        oldPasswordField.setPromptText("Enter old password");
        newPasswordField.setPromptText("Enter new password");

        addGridRow(grid, 0, new Label("Name:"), nameField);
        addGridRow(grid, 1, new Label("Email:"), emailField);
        addGridRow(grid, 2, new Label("Old Password:"), oldPasswordField);
        addGridRow(grid, 3, new Label("New Password:"), newPasswordField);
        grid.add(successLabel, 0, 4, 2, 1);
        grid.add(errorLabel, 0, 5, 2, 1);
        grid.add(saveButton, 0, 6);

        root = new VBox(10, grid);
        root.setAlignment(Pos.CENTER);

        saveButton.setOnAction(e -> save());
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

    private Label createLabel(Color color) {
        Label label = new Label();
        label.setTextFill(color);
        return label;
    }

    private void save() {
        String name = nameField.getText();
        String email = emailField.getText();
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();

        Response<String> response = uc.changeProfile( name, email, oldPassword, newPassword);
        if (response.isSuccess()) {
            successLabel.setText(response.getMessage());
            errorLabel.setText("");
        } else {
            errorLabel.setText(response.getMessage());
            successLabel.setText("");
        }
    }
}
