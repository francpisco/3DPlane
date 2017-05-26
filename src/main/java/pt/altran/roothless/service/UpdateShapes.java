package pt.altran.roothless.service;

import javafx.scene.shape.Ellipse;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;


/**
 * Created by Altran on 23/05/2017.
 */
public class UpdateShapes {


    public static void updateElipse(Ellipse targetElipse, Bubble bubble, Plane plane, double elipseCenter) {

        double distanceY = bubble.getyPosition() - plane.getyPosition();
        double distanceX = bubble.getxPosition() - plane.getxPosition();
        double distanceZ = bubble.getzPosition() - plane.getzPosition();

        double distanceToCenter = Math.sqrt(distanceX*distanceX + distanceZ*distanceZ);
        double distanceToCCorrected = (distanceToCenter < 20 ? 0 : distanceToCenter);

        double distanceToPlane = Math.sqrt(distanceY*distanceY + distanceToCenter*distanceToCenter);
        double distanceToPCorrected = Math.sqrt(distanceY*distanceY + distanceToCCorrected*distanceToCCorrected);

        resize(distanceToPCorrected, distanceToCenter, targetElipse);
        //recenter(distanceX, distanceZ, distanceToPlane, targetElipse, elipseCenter);
        rotate(distanceX, distanceZ, targetElipse);

    }

    private static void rotate(double distanceX, double distanceZ, Ellipse targetElipse) {
        //System.out.println("" + Math.atan(distanceZ/distanceX));
        targetElipse.setRotate((Math.atan(distanceZ/distanceX))*180/Math.PI);
    }

    private static void recenter(double distanceX, double distanceZ, double distanceToPlane, Ellipse targetElipse, double elipseCenter) {
        targetElipse.setCenterY(elipseCenter - (distanceZ*1000)/distanceToPlane);
        targetElipse.setCenterX(distanceX*1000/distanceToPlane);
    }

    private static void resize(double distanceToPlane, double distanceToCenter, Ellipse targetElipse) {
        double alphaAnlge = Math.atan(distanceToCenter/distanceToPlane);

        targetElipse.setRadiusX(2000.0/distanceToPlane);

        targetElipse.setRadiusY((Math.cos(alphaAnlge)*2000.0)/distanceToPlane);
    }
}
