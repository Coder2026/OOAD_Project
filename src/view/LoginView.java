package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends Application{

	@Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        Label label = new Label("Login View");
        Button button = new Button("Login");
        root.getChildren().addAll(label, button);

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

}
