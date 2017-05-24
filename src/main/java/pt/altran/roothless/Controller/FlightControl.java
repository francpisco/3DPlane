package main.java.pt.altran.roothless.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import main.java.pt.altran.roothless.model.Circle;
import main.java.pt.altran.roothless.model.Plane;
import main.java.pt.altran.roothless.service.UpdateShapes;

import java.net.URL;
import java.util.ResourceBundle;

public class FlightControl implements Initializable{

    private double elipseYPos;
    private double elipseRot;
    private double elipseScale = 1.0;
    private Circle circle;
    private Plane plane;

    @FXML
    private Button leftButton;

    @FXML
    private Button upButton;

    @FXML
    private Button downButton;

    @FXML
    private Button rightButton;

    @FXML
    private Ellipse targetElipse;

    @FXML
    void up(ActionEvent event) {
        plane.setLift(20.0);
    }

    @FXML
    void down(ActionEvent event) {
        elipseRot += 10.0;
        targetElipse.setRotate(elipseRot);

    }

    @FXML
    void right(ActionEvent event) {
        elipseScale *= 1.05;
        targetElipse.setScaleX(elipseScale);
        targetElipse.setScaleY(elipseScale);

    }

    @FXML
    void left(ActionEvent event) {

    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double elipseCenterZ = targetElipse.getCenterY();
        move(elipseCenterZ);
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
}
