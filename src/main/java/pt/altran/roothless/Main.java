package pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.altran.roothless.Controller.PlaneController;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.service.Loop;


public class Main extends Application {

    Navigation navigation;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Bubble bubble = new Bubble();
        Plane plane = new Plane();

        Loop loop = new Loop(plane, bubble);
        Thread planeLoop = new Thread(loop);
        planeLoop.start();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/springconfig.xml");

        Navigation navigation = applicationContext.getBean(Navigation.class);

        navigation.setStage(primaryStage);
        navigation.loadScreen("/first");





//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/first.fxml"));
//
//        Parent root = fxmlLoader.load();
//
//        PlaneController planeController = fxmlLoader.getController();
//        planeController.setBubble(bubble);
//        planeController.setPlane(plane);
//
//        primaryStage.setTitle("Flight of the Conchords");
//        primaryStage.setScene(new Scene(root, 1280, 800));
//        primaryStage.show();

    }

    public static void main(String[] args) {
       launch(args);
    }
}
