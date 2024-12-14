package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EOHomeView {

    private VBox root;
    private GridPane grid;
    private Button createEventBtn;
    private Button viewEventBtn;
    private Stage primaryStage;
    private String id;

    public void show(Stage primaryStage, String id) {
        this.primaryStage = primaryStage;
        this.id = id;
        init();

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void init() {
        grid = createGridPane();
        createEventBtn = createCreateEventButton();
        viewEventBtn = createViewEventButton();

        grid.add(viewEventBtn, 1, 0);
        grid.add(createEventBtn, 0, 0);

        root = new VBox(grid);
        root.setAlignment(Pos.CENTER);
    }

    private Button createCreateEventButton() {
        Button createEventBtn = new Button("Create Event");
        createEventBtn.setOnAction(event -> {
            new CreateEventView().show(primaryStage, id);
        });
        return createEventBtn;
    }

    private Button createViewEventButton() {
        Button viewEventBtn = new Button("View All Events");
        viewEventBtn.setOnAction(event -> {
            new OrganizedEventView().show(primaryStage, id);
        });
        return viewEventBtn;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
}
