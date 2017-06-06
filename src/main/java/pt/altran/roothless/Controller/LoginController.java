package pt.altran.roothless.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;
import pt.altran.roothless.Navigation;
import pt.altran.roothless.model.Database;
import pt.altran.roothless.model.User;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Altran on 30/05/2017.
 */

@Controller
public class LoginController implements Initializable {

    public Button registerButton;
    public TextField emailField;
    public TextField nickField1;
    public Label message;
    private String name = "plane";
    private String pass = "pass";

    private Navigation navigation;
    private Database database;

    public TextField userField;
    public TextField passwordField;
    public Button loginButton;

    public LoginController() {
    }

    public LoginController(Navigation navigation, Database database) {
        this.navigation = navigation;
        this.database = database;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {
        navigation.loadScreen("/first2");
    }

    public void register(ActionEvent actionEvent) {

        User user = new User();
        user.setName(userField.getText());
        user.setEmail(emailField.getText());
        user.setPass(passwordField.getText());
        user.setNick(nickField1.getText());
        if (!database.exists(user)) {
            database.register(user);
        }else {
            message.setText("USER ALREADY EXISTS");
            message.setVisible(true);
        }
    }
}
