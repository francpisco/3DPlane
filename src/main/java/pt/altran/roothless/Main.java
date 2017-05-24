package main.java.pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.pt.altran.roothless.Controller.FlightControl;
import main.java.pt.altran.roothless.model.Circle;
import main.java.pt.altran.roothless.model.Plane;
import main.java.pt.altran.roothless.service.Loop;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


        Circle circle = new Circle();
        Plane plane = new Plane();

        Loop loop = new Loop(plane, circle);
        Thread planeLoop = new Thread(loop);
        planeLoop.start();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FlightView.fxml"));

        Parent root = fxmlLoader.load();

        FlightControl flightControl = (FlightControl) fxmlLoader.getController();
        flightControl.setCircle(circle);
        flightControl.setPlane(plane);

        primaryStage.setTitle("Flight of the Conchords");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();

    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//
//        PlaneController planeController = new PlaneController();
//        Parent root;
//        Scene scene;
//        Stage stage = primaryStage;
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/first.fxml"));
//        //fxmlLoader.setController(planeController);
//
//        root = fxmlLoader.load();
//
//        scene = new Scene(root, 900,680);
//
//        stage.setTitle("3DPlane");
//        stage.setScene(scene);
//        stage.show();
//
//
//
//
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
