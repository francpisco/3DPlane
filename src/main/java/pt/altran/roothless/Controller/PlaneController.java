package pt.altran.roothless.Controller;

import eu.hansolo.medusa.Gauge;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;
import pt.altran.roothless.Navigation;
import pt.altran.roothless.NavigationSuper;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.model.RelativePosition;
import pt.altran.roothless.service.Constants;
import pt.altran.roothless.service.Loop;
import pt.altran.roothless.service.UpdateWindowView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Altran on 23/05/2017.
 */

@Controller
public class PlaneController implements Initializable {


    public Label score;
    public Gauge fuelGauge;
    private Loop loop;

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
    public Rectangle skyrectangle;
    public Circle targetCircle;

    public Gauge altitudeLcd;
    public Gauge northLcd;
    public Gauge eastLcd;
    public Gauge compassLcd;
    public Gauge pitchLcd;
    public Gauge speedLc;
    public Gauge rollLcd;

    public ImageView leftArrow;
    public ImageView rightArrow;
    public ImageView upArrow;
    public ImageView downArrow;

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
    private Plane plane = new Plane();
    private RelativePosition relativePosition;
    private UpdateWindowView updateWindowView;

    private NavigationSuper navigationSuper;

    private Scene scene;

    @FXML
    private Ellipse targetElipse;

    @FXML
    private Slider flapsSlider;

    @FXML
    private Label flapsLabel;

    @FXML
    private ToggleButton landingGearButton;

    @FXML
    private Slider turnSlider;
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

    public PlaneController() {

    }

    public PlaneController(Bubble bubble, Plane plane, RelativePosition relativePosition
            , UpdateWindowView updateWindowView, Loop loop, NavigationSuper navigationSuper) {

        this.bubble = bubble;
        this.plane = plane;
        this.relativePosition = relativePosition;
        this.updateWindowView = updateWindowView;
        this.loop = loop;
        this.navigationSuper = navigationSuper;

        //initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                scene = navigationSuper.getScene();
                System.out.println("sceene in PlaneController " + scene);
            }
        };

        timer.schedule(task, 1000);

        Thread planeLoop = new Thread(loop);
        planeLoop.start();

        move();

        setCockpitButtons();

        moveRudderSlider();
        moveThrusthSlider();
        moveFlapsSlider();

        leftArrow.setVisible(false);
        rightArrow.setVisible(false);
        upArrow.setVisible(false);
        downArrow.setVisible(false);

    }

    public void move() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {

            updateWindowView.updateView(plane, groundRectangle, targetCircle, bubble);
            altitudeLcd.setValue(plane.getzPosition());
            northLcd.setValue(plane.getyPosition());
            eastLcd.setValue(plane.getxPosition());
            compassLcd.setValue(plane.getYaw() * 180 / Math.PI);
            pitchLcd.setValue(plane.getPitch() * 180 / Math.PI);
            speedLc.setValue(plane.getSpeed());
            rollLcd.setValue(plane.getRoll() * 180 / Math.PI);
            score.setText(Integer.toString(loop.getScore()));
            fuelGauge.setValue(plane.getFuel() * 100 / Constants.INIT_FUEL);

            if (relativePosition.getDeltaHAngle() < 0) {
                rightArrow.setVisible(false);
                leftArrow.setVisible(true);
            }
            if (relativePosition.getDeltaHAngle() > 0) {
                leftArrow.setVisible(false);
                rightArrow.setVisible(true);
            }
            if (relativePosition.getDeltaVAngle() > 0) {
                upArrow.setVisible(false);
                downArrow.setVisible(true);
            }
            if (relativePosition.getDeltaVAngle() < 0) {
                downArrow.setVisible(false);
                upArrow.setVisible(true);
            }

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
            parkingbrakesbol = false;
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

        if (powerbol && apubol && fuelpumpbol) {
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

        if (powerbol && apubol && fuelpumpbol) {
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

    public void moveThrusthSlider() {

        thrustSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                thrustLabel.setText(String.format("%.0f", newValue));
                isReadyToFly();
                if (isreadytofly) {
                    plane.moveThrottle((double) newValue / 10);
                }
            }
        });

        thrustSlider.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

            if (key.getCode() == KeyCode.A) {
                System.out.println("enter A");
                plane.moveThrottle(1);
            }
        });

        thrustSlider.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.Z) {
                System.out.println("enter Z");
                plane.moveThrottle(-1);
            }
        });

    }


    public void moveRudderSlider() {

        turnSlider.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.LEFT) {
                System.out.println("enter LEFT");
                plane.moveRudder(-1);
            }
        });

        turnSlider.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.RIGHT) {
                System.out.println("enter RIGHT");
                plane.moveRudder(1);
            }
        });

        turnSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                turnValueLabel.setText(String.format("%.0f", newValue));
                plane.moveRudder((double) newValue / 100);

            }
        });


    }

    public void moveFlapsSlider() {


        flapsSlider.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.UP) {
                System.out.println("enter UP");
                plane.moveFlaps(-1);
            }
        });

        flapsSlider.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.DOWN) {
                System.out.println("enter down");
                plane.moveFlaps(1);
            }
        });

        flapsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                plane.moveFlaps((double) newValue / 500);

            }
        });


    }

    public void setCockpitButtons() {

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

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }
}



