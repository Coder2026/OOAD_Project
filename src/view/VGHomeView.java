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
import model.User;
import util.SessionManager;

public class VGHomeView {

	private VBox root;
    private GridPane grid;
    private Button createEventBtn;
    private Button viewEventBtn;
    private Stage primaryStage;
    private String id;
    private String role = SessionManager.getInstance().getCurrentUser().getUser_role();
    
    public void show(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    
        
        VBox root = createLayout(primaryStage);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Home" + role);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createLayout(Stage primaryStage) {
	    
        GridPane grid = createGridPane();
        
        Button viewEventBtn = new Button("View Invitation");
        viewEventBtn.setOnAction(event ->{
        	new VendorViewInvitation().show(primaryStage, id);
        });
        Button view2EventBtn = new Button("View Accepted Event");
        view2EventBtn.setOnAction(event->{
        	new VendorViewAcceptedInvitation().show(primaryStage, id);
        });
        if (role.equalsIgnoreCase("Vendor")) {
            Button manageVendorBtn = new Button("Manage Vendor");
            manageVendorBtn.setOnAction(event -> {
                new ManageVendorView().show(primaryStage);
            });
            grid.add(manageVendorBtn, 2, 0);
        }
        grid.add(viewEventBtn, 0, 0);
        grid.add(view2EventBtn, 1, 0);
        
     
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
