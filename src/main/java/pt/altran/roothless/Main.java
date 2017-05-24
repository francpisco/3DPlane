package pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.altran.roothless.Controller.PlaneController;
import pt.altran.roothless.service.PlaneCommands;
import pt.altran.roothless.view.WindowView;

import java.awt.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        PlaneController planeController = new PlaneController();
        Parent root;
        Scene scene;
        Stage stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/first.fxml"));
        //fxmlLoader.setController(planeController);

        root = fxmlLoader.load();

        scene = new Scene(root, 900,680);

        stage.setTitle("3DPlane");
        stage.setScene(scene);
        stage.show();




    }

    public static void main(String[] args) {
        launch(args);
    }
}
