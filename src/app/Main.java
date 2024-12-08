package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminHomeView;
import view.CreateEventView;
import view.EOHomeView;
import view.EventDetailsView;
import view.LoginView;
import view.ManageVendorView;
import view.OrganizedEventView;
import view.VendorHomeView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       

        LoginView loginView = new LoginView();
        //ManageVendorView view = new ManageVendorView();

        //LoginView loginView = new LoginView();
        //EventDetailsView view = new EventDetailsView();
    	//AdminHomeView view = new AdminHomeView();
//        VendorHomeView view = new VendorHomeView();

        try {
			loginView.start(primaryStage);
			//view.start(primaryStage);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch(args); 
    }
}