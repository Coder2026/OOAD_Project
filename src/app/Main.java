package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       
        LoginView loginView = new LoginView();
        try {
			loginView.start(primaryStage);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
