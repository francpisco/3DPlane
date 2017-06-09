package pt.altran.roothless;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Altran on 09/06/2017.
 */
public class NavigationSuper {

    private LinkedList<Scene> scenes = new LinkedList<Scene>();
    private Map<String, Initializable> controllers = new HashMap<>();
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        scenes.pop();

        setScene(scenes.peek());
    }

    public void loadScreen(String view) {
        try {

            FXMLLoader fxmlLoader;

            fxmlLoader = new FXMLLoader(getClass().getResource(  view + ".fxml"));

            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {

                    return controllers.get(param.getSimpleName());
                }
            });

            root = fxmlLoader.load();

            scene = new Scene(root);

            //((ControllerScene) fxmlLoader.getController()).setScene(scene);

            System.out.println("Scene in Navigation" + scene);

            scenes.push(scene);

            setScene(scene);

        } catch (IOException e) {
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }
    }

    private void setScene(Scene scene) {

        stage.setScene(scene);

        stage.show();

    }

    public void close() {

        stage.close();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setControllers(Map<String, Initializable> controllers) {
        this.controllers = controllers;
    }

    public Scene getScene() {
        return scene;
    }
}
