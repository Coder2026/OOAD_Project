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

public class EOHomeView {



    public void show(Stage primaryStage, String id) {

        VBox root = createLayout(primaryStage, id);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createLayout(Stage primaryStage, String id) {
	    
        GridPane grid = createGridPane();
        
        Button createEventBtn = new Button("Create Event");
        createEventBtn.setOnAction(event->{
        	new CreateEventView().show(primaryStage, id);
        });
        Button viewEventBtn = new Button("View All Events");
        viewEventBtn.setOnAction(event ->{
        	new OrganizedEventView().show(primaryStage, id);
        });
        grid.add(viewEventBtn, 1, 0);
        grid.add(createEventBtn, 0, 0);
     
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
