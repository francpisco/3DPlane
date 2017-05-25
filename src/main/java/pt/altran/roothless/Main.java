package pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pt.altran.roothless.Controller.PlaneController;
import pt.altran.roothless.model.Circle;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.service.Loop;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Circle circle = new Circle();
        Plane plane = new Plane();

        Loop loop = new Loop(plane, circle);
        Thread planeLoop = new Thread(loop);
        planeLoop.start();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/first2.fxml"));

        Parent root = fxmlLoader.load();

        PlaneController planeController = fxmlLoader.getController();
        planeController.setCircle(circle);
        planeController.setPlane(plane);

        primaryStage.setTitle("Flight of the Conchords");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
