package main.java.pt.altran.roothless.service;

import javafx.scene.shape.Ellipse;
import main.java.pt.altran.roothless.model.Circle;
import main.java.pt.altran.roothless.model.Plane;


/**
 * Created by Altran on 23/05/2017.
 */
public class UpdateShapes {


    public static void updateElipse(Ellipse targetElipse, Circle circle, Plane plane, double elipseCenter) {

        double distanceY = circle.getyPosition() - plane.getyPosition();
        double distanceX = circle.getxPosition() - plane.getxPosition();
        double distanceZ = circle.getzPosition() - plane.getzPosition();

        double distanceToCenter = Math.sqrt(distanceX*distanceX + distanceZ*distanceZ);
        System.out.println("Distance to center = " + distanceToCenter);

        resize(distanceY, distanceToCenter, targetElipse);
        recenter(distanceX, distanceZ, distanceY, targetElipse, elipseCenter);

    }

    private static void recenter(double distanceX, double distanceZ, double distanceY, Ellipse targetElipse, double elipseCenter) {
        targetElipse.setCenterY(elipseCenter - (distanceZ*10)/distanceY);
    }

    private static void resize(double distanceY, double distanceToCenter, Ellipse targetElipse) {
        double alphaAnlge = Math.atan((distanceToCenter < 30 ? 0 : distanceToCenter - 30)/distanceY);
        targetElipse.setRadiusX(1000.0/distanceY);
        targetElipse.setRadiusY((Math.cos(alphaAnlge)*1000.0)/distanceY);
    }
}
