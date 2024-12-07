package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminHomeView {

	public void start(Stage primaryStage) {
		   
        show(primaryStage);
    }

    public void show(Stage primaryStage) {

        VBox root = createLayout(primaryStage);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createLayout(Stage primaryStage) {
	    
        GridPane grid = createGridPane();
        
        Button viewEventBtn = new Button("View All Events");
        viewEventBtn.setOnAction(event ->{
        	new AdminEventView().show(primaryStage);
        });
        Button createEventBtn = new Button("View All Users");
        createEventBtn.setOnAction(event->{
        	new AdminUserView().show(primaryStage);
        });
        grid.add(viewEventBtn, 0, 0);
        grid.add(createEventBtn, 1, 0);
     
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
}
