package pt.altran.roothless.service;

import javafx.scene.shape.Rectangle;
import pt.altran.roothless.model.Plane;

/**
 * Created by Altran on 25/05/2017.
 */
public class UpdateWindowView {

    public static void updateView(Plane plane, Rectangle rectangle) {

        updatePitch(plane, rectangle);

        updateRoll(plane, rectangle);

    }

    private static void updateRoll(Plane plane, Rectangle rectangle) {
        rectangle.setRotate(plane.getRoll());
    }

    private static void updatePitch(Plane plane, Rectangle rectangle) {
        rectangle.setY((Math.tan(plane.getPitch()) * 214) + 71);
    }
}
