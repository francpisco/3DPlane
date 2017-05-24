package pt.altran.roothless.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import pt.altran.roothless.model.Circle;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.service.UpdateShapes;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Altran on 23/05/2017.
 */
public class PlaneController {

    private Circle circle;
    private Plane plane;

    @FXML
    private Ellipse targetElipse;

    @FXML
    private Slider flapsSlider;

    @FXML
    private Label flapsLabel;

    @FXML
    private ToggleButton landingGearButton;

    @FXML
    private Slider turnslider;
    @FXML
    private Label turnValueLabel;

    @FXML
    private Slider thrustSlider;
    @FXML
    public Label thrustLabel;

    @FXML
    private Boolean LandingGearUp = true;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void initialize() {

        double elipseCenterZ = targetElipse.getCenterY();
        move(elipseCenterZ);

        turnslider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                turnValueLabel.setText(String.format("%.0f" , newValue));
                plane.setyAcceleration(1);
            }
        });

        thrustSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                thrustLabel.setText(String.format("%.0f",newValue));
            }
        });

        flapsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                flapsLabel.setText(String.format("%.0f",newValue));
            }
        });


    }

    public void move(double elipseCenterZ) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            System.out.println(circle.getyPosition());
            UpdateShapes.updateElipse(targetElipse, circle, plane, elipseCenterZ);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void landingGear(ActionEvent actionEvent) {

        System.out.println("enter landing");

        if(LandingGearUp) {
            System.out.println("setting to green");
            landingGearButton.setStyle("-fx-background-color: green;");
            LandingGearUp = false;
        }else {
            System.out.println("setting to red");
            landingGearButton.setStyle("-fx-background-color: red;");
            LandingGearUp = true;
        }

    }


}
