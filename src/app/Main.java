package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.CreateEventView;
import view.EOHomeView;
import view.EventDetailsView;
import view.LoginView;
import view.ManageVendorView;
import view.OrganizedEventView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       
        LoginView loginView = new LoginView();
        ManageVendorView view = new ManageVendorView();
        try {
//			loginView.start(primaryStage);
			view.start(primaryStage);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
