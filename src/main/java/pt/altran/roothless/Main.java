package pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pt.altran.roothless.Controller.PlaneController;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.service.Loop;


public class Main extends Application {


    //TODO : change scren to green when pass a ellipse
    //TODO : change sizes

    @Override
    public void start(Stage primaryStage) throws Exception{

        Bubble bubble = new Bubble();
        Plane plane = new Plane();

        Loop loop = new Loop(plane, bubble);
        Thread planeLoop = new Thread(loop);
        planeLoop.start();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/first.fxml"));

        Parent root = fxmlLoader.load();

        PlaneController planeController = fxmlLoader.getController();
        planeController.setBubble(bubble);
        planeController.setPlane(plane);

        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        primaryStage.setTitle("Flight of the Conchords");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
