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

public class VendorHomeView extends Application {

	@Override
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
        
        Button viewEventBtn = new Button("View Invitation");
        viewEventBtn.setOnAction(event ->{
        	new VendorViewInvitation().show(primaryStage);
        });
        Button view2EventBtn = new Button("View Accepted Event");
        view2EventBtn.setOnAction(event->{
        	new VendorViewAcceptedInvitation().show(primaryStage);
        });
        Button view3EventBtn = new Button("Manage Vendor");
        view3EventBtn.setOnAction(event ->{
        	new ManageVendorView().show(primaryStage);
        });
        grid.add(viewEventBtn, 0, 0);
        grid.add(view2EventBtn, 1, 0);
        grid.add(view3EventBtn, 2, 0);
     
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
