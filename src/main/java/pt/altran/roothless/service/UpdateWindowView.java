package pt.altran.roothless.service;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;

/**
 * Created by Altran on 25/05/2017.
 */
public class UpdateWindowView {

    public static void updateView(Plane plane, Rectangle rectangle, Ellipse ellipse, Bubble bubble) {

        double deltaX = plane.getxPosition() - bubble.getxPosition();
        double deltaY = plane.getyPosition() - bubble.getyPosition();
        double deltaZ = plane.getzPosition() - bubble.getzPosition();

        double angleH = Math.atan(deltaX/deltaY);
        double angleV = Math.atan(deltaZ/deltaY);

        ellipse.setCenterX((Math.tan(angleH) * 0.45) * 600);

        updatePitch(plane, rectangle);

        updateRoll(plane, rectangle);

    }

    private static void updateRoll(Plane plane, Rectangle rectangle) {
        rectangle.setRotate(plane.getRoll());

    }

    private static void updatePitch(Plane plane, Rectangle rectangle) {
        //System.out.println("Before" + rectangle.getY());
        rectangle.setY((Math.tan(plane.getPitch()) * 214) + 71);
        //System.out.println("After" + rectangle.getY());
    }
}
