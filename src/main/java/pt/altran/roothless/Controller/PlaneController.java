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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.service.UpdateShapes;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Altran on 23/05/2017.
 */
public class PlaneController {

    public ToggleButton parkingbrakes;
    public ToggleButton power;
    public ToggleButton engine2;
    public ToggleButton engine1;
    public ToggleButton engine3;
    public ToggleButton engine4;
    public ToggleButton fuelpump;
    public ToggleButton apu;
    public ToggleButton lights;
    public ToggleButton com;
    public Rectangle groundRectangle;

    private boolean parkingbrakesbol = false;
    private boolean powerbol = false;
    private boolean engine1bol = false;
    private boolean engine2bol = false;
    private boolean engine3bol = false;
    private boolean engine4bol = false;
    private boolean fuelpumpbol = false;
    private boolean apubol = false;
    private boolean lightsbol = false;
    private boolean combol = false;


    private Bubble bubble;
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
    private Boolean landingGearUp = true;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    private boolean isreadytofly;

    @FXML
    void initialize() {

        double elipseCenterZ = targetElipse.getCenterY();
        move(elipseCenterZ);

        turnslider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                turnValueLabel.setText(String.format("%.0f", newValue));
                plane.setxAcceleration((double) newValue/1000);

            }
        });

        thrustSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                thrustLabel.setText(String.format("%.0f", newValue));
                System.out.println("ready to fly value " + isreadytofly);
                isReadyToFly();
                if (isreadytofly) {
                    plane.setyAcceleration((double) newValue / 2);
                }
            }
        });

        flapsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                flapsLabel.setText(String.format("%.0f", newValue));
            }
        });

        landingGearButton.setStyle("-fx-background-color: red;");
        parkingbrakes.setStyle("-fx-background-color: red;");
        power.setStyle("-fx-background-color: red;");
        engine2.setStyle("-fx-background-color: red;");
        engine1.setStyle("-fx-background-color: red;");
        engine3.setStyle("-fx-background-color: red;");
        engine4.setStyle("-fx-background-color: red;");
        fuelpump.setStyle("-fx-background-color: red;");
        apu.setStyle("-fx-background-color: red;");
        lights.setStyle("-fx-background-color: red;");
        com.setStyle("-fx-background-color: red;");

    }

    public void move(double elipseCenterZ) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
//            System.out.println(bubble.getyPosition());
            UpdateShapes.updateElipse(targetElipse, bubble, plane, elipseCenterZ);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setBubble(Bubble bubble) {
        this.bubble = bubble;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void landingGear(ActionEvent actionEvent) {

        if (powerbol && apubol) {
            if (landingGearUp) {
                landingGearButton.setStyle("-fx-background-color: green;");
                landingGearUp = false;
            } else {
                landingGearButton.setStyle("-fx-background-color: red;");
                landingGearUp = true;
            }
        }

    }

    public void parkingbrakes(ActionEvent actionEvent) {

        if (powerbol && apubol && thrustLabel.getText().equals("0")) {
            if (!parkingbrakesbol) {
                parkingbrakes.setStyle("-fx-background-color: green;");
                parkingbrakesbol = true;

            } else {
                parkingbrakes.setStyle("-fx-background-color: red;");
                parkingbrakesbol = false;

            }
        }
    }

    public void power(ActionEvent actionEvent) {

        if (!powerbol) {
            power.setStyle("-fx-background-color: green;");
            powerbol = true;
        } else {
            power.setStyle("-fx-background-color: red;");
            powerbol = false;
            apu.setStyle("-fx-background-color: red;");
            apubol = false;
            fuelpump.setStyle("-fx-background-color: red;");
            fuelpumpbol = false;
            parkingbrakesbol=false;
            parkingbrakes.setStyle("-fx-background-color: red;");
            landingGearUp = false;
            landingGearButton.setStyle("-fx-background-color: red;");
            engine1bol = false;
            engine1.setStyle("-fx-background-color: red;");
            engine2.setStyle("-fx-background-color: red");
            engine2bol = false;
            engine3.setStyle("-fx-background-color: red");
            engine3bol = false;
            engine3.setStyle("-fx-background-color: red");
            engine4bol = false;
            engine4.setStyle("-fx-background-color: red");
            lightsbol = false;
            lights.setStyle("-fx-background-color: red;");
            combol = false;
            com.setStyle("-fx-background-color: red;");

        }
    }

    public void engine1(ActionEvent actionEvent) {


        if (powerbol && apubol && fuelpumpbol) {
            if (!engine1bol) {
                engine1.setStyle("-fx-background-color: green;");
                engine1bol = true;
            } else {
                engine1.setStyle("-fx-background-color: red;");
                engine1bol = false;
            }
        }
    }

    public void engine2(ActionEvent actionEvent) {

        if (powerbol && apubol && fuelpumpbol) {
            if (!engine2bol) {
                engine2.setStyle("-fx-background-color: green;");
                engine2bol = true;
            } else {
                engine2.setStyle("-fx-background-color: red;");
                engine2bol = false;
            }
        }
    }

    public void engine3(ActionEvent actionEvent) {

        if(powerbol && apubol && fuelpumpbol) {
            if (!engine3bol) {
                engine3.setStyle("-fx-background-color: green;");
                engine3bol = true;
            } else {
                engine3.setStyle("-fx-background-color: red;");
                engine3bol = false;
            }
        }
    }

    public void engine4(ActionEvent actionEvent) {

        if(powerbol && apubol && fuelpumpbol) {
            if (!engine4bol) {
                engine4.setStyle("-fx-background-color: green;");
                engine4bol = true;
            } else {
                engine4.setStyle("-fx-background-color: red;");
                engine4bol = false;
            }
        }
    }

    public void fuelpump(ActionEvent actionEvent) {

        if (powerbol && apubol) {
            if (!fuelpumpbol) {
                fuelpump.setStyle("-fx-background-color: green;");
                fuelpumpbol = true;
            } else {
                fuelpump.setStyle("-fx-background-color: red;");
                fuelpumpbol = false;
                engine1bol = false;
                engine1.setStyle("-fx-background-color: red;");
                engine2.setStyle("-fx-background-color: red");
                engine2bol = false;
                engine3.setStyle("-fx-background-color: red");
                engine3bol = false;
                engine3.setStyle("-fx-background-color: red");
                engine4bol = false;
                engine4.setStyle("-fx-background-color: red");
            }
        }
    }

    public void apu(ActionEvent actionEvent) {

        if (powerbol) {
            if (!apubol) {
                apu.setStyle("-fx-background-color: green;");
                apubol = true;
            } else {
                apu.setStyle("-fx-background-color: red;");
                apubol = false;
            }
        }
    }

    public void lights(ActionEvent actionEvent) {

        if (powerbol) {
            if (!lightsbol) {
                lights.setStyle("-fx-background-color: green;");
                lightsbol = true;
            } else {
                lights.setStyle("-fx-background-color: red;");
                lightsbol = false;
            }
        }
    }

    public void com(ActionEvent actionEvent) {

        if (powerbol && apubol) {
            if (!combol) {
                com.setStyle("-fx-background-color: green;");
                combol = true;
            } else {
                com.setStyle("-fx-background-color: red;");
                combol = false;
            }
        }
    }

    public void isReadyToFly() {

        if (parkingbrakesbol && powerbol && engine1bol && engine2bol && engine3bol && engine4bol && fuelpumpbol &&
                apubol && lightsbol && combol) {
            isreadytofly = true;
        } else {
            isreadytofly = true;
        }
    }
}
