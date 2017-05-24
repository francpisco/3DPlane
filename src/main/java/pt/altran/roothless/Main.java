package pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.altran.roothless.Controller.PlaneController;
import pt.altran.roothless.model.Circle;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.service.Loop;


public class Main extends Application {


    //TODO : ELLIPSE VAZIA; COPCKPIT POR CIMA DA ELLIPSE

    @Override
    public void start(Stage primaryStage) throws Exception{

        Circle circle = new Circle();
        Plane plane = new Plane();

        Loop loop = new Loop(plane, circle);
        Thread planeLoop = new Thread(loop);
        planeLoop.start();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/first.fxml"));

        Parent root = fxmlLoader.load();

        PlaneController planeController = fxmlLoader.getController();
        planeController.setCircle(circle);
        planeController.setPlane(plane);

        primaryStage.setTitle("Flight of the Conchords");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
