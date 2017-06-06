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
import pt.altran.roothless.service.UserService;

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

    private Navigation navigation;
    private UserService userService;

    public TextField userField;
    public TextField passwordField;
    public Button loginButton;

    public LoginController() {
    }

    public LoginController(Navigation navigation, UserService userService) {
        this.navigation = navigation;
        this.userService = userService;
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

        if (userService.register(user)) {
            message.setText("Registration sucessul");
            message.setVisible(true);
        }else {
            message.setText("USER ALREADY EXISTS");
            message.setVisible(true);
        }
    }
}
