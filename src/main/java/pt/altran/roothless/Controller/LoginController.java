package pt.altran.roothless.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.altran.roothless.Navigation;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Altran on 30/05/2017.
 */

@Controller
public class LoginController implements Initializable {

    private String name = "plane";
    private String pass = "pass";
    private Navigation navigation;

    public TextField userField;
    public TextField passwordField;
    public Button loginButton;

    public LoginController() {
    }

    public LoginController(Navigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {
        navigation.loadScreen("/first2");
    }
}
