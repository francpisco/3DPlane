package pt.altran.roothless.Controller;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;
import pt.altran.roothless.Navigation;
import pt.altran.roothless.NavigationSuper;
import pt.altran.roothless.model.Database;
import pt.altran.roothless.model.User;
import pt.altran.roothless.service.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Altran on 30/05/2017.
 */

@Controller
public class LoginController implements Initializable {

    Database database = new Database();

    public Button registerButton;
    public TextField emailField;
    public TextField nickField1;
    public Label message;

    public TableView<User> highscoreTable;
    public TableColumn<User, String> nick;
    public TableColumn<User, String> highscore;
    private ObservableList<User> data;

    private UserService userService;
    private NavigationSuper navigationSuper;

    public TextField userField;
    public TextField passwordField;
    public Button loginButton;

    private Scene scene;

    public LoginController() {
    }

    public LoginController(UserService userService, NavigationSuper navigationSuper) {
        this.userService = userService;
        this.navigationSuper = navigationSuper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                scene = navigationSuper.getScene();
                System.out.println("sceene in login " + scene);
            }
        };

        timer.schedule(task, 5000);

        nick.setCellValueFactory(new PropertyValueFactory("nick"));
        highscore.setCellValueFactory(new PropertyValueFactory("highscore"));
        buildData();
        highscoreTable.setItems(data);


    }

    public void login(ActionEvent actionEvent) {

        navigationSuper.loadScreen("/views/game1");
    }

    public void register(ActionEvent actionEvent) {

        User user = new User();
        user.setName(userField.getText());
        user.setEmail(emailField.getText());
        user.setPass(passwordField.getText());
        user.setnick(nickField1.getText());

        if (userService.register(user)) {
            message.setText("Registration sucessul");
            message.setVisible(true);
        } else {
            message.setText("USER ALREADY EXISTS");
            message.setVisible(true);
        }
    }

    public void buildData(){

        data = FXCollections.observableArrayList();
        ResultSet resultSet = database.getAll();

        for (Row row: resultSet) {
            User user = new User();
            user.setnick(row.getString("nick"));
            user.setHighscore(row.getInt("highscore"));
            data.add(user);

        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }
}
