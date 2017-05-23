package pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.altran.roothless.view.WindowView;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane root = new Pane();
        //Parent root = FXMLLoader.load(getClass().getResource("view/cockpitView.fxml"));
        primaryStage.setTitle("3D Skies");
        Scene scene = new Scene(root, 700, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        WindowView windowView = new WindowView();
        windowView.start(root);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
