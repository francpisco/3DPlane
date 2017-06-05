package pt.altran.roothless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.altran.roothless.Controller.PlaneController;
import pt.altran.roothless.model.*;
import pt.altran.roothless.service.Loop;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Database database = new Database();
        database.createDatabase("plane");
        database.creatUserType();

        User renato = new User("renato", "renato343@gmail.com","1234",new Game(""))

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/springconfig.xml");

        Navigation navigation = applicationContext.getBean(Navigation.class);

        navigation.setStage(primaryStage);
        navigation.loadScreen("/first");

    }

    public static void main(String[] args) {
       launch(args);
    }
}
