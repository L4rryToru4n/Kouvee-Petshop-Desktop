package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        } catch(IOException e)
        {
            throw new RuntimeException();
        }

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kouvee Pet Shop");
        primaryStage.show();

        showMainPanelView();
    }

    public void showMainPanelView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("EmployeeView.fxml"));
    }
}
