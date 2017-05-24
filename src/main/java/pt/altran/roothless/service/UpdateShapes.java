package pt.altran.roothless.service;

import javafx.scene.shape.Ellipse;
import pt.altran.roothless.model.Circle;
import pt.altran.roothless.model.Plane;


/**
 * Created by Altran on 23/05/2017.
 */
public class UpdateShapes {


    public static void updateElipse(Ellipse targetElipse, Circle circle, Plane plane, double elipseCenter) {

        double distanceY = circle.getyPosition() - plane.getyPosition();
        double distanceX = circle.getxPosition() - plane.getxPosition();
        double distanceZ = circle.getzPosition() - plane.getzPosition();

        double distanceToCenter = Math.sqrt(distanceX*distanceX + distanceZ*distanceZ);
        double distanceToCCorrected = (distanceToCenter < 20 ? 0 : distanceToCenter);
//        System.out.println("Distance to center = " + distanceToCenter);

        double distanceToPlane = Math.sqrt(distanceY*distanceY + distanceToCenter*distanceToCenter);
        double distanceToPCorrected = Math.sqrt(distanceY*distanceY + distanceToCCorrected*distanceToCCorrected);
        //System.out.println("dixtanceToPlane = " + distanceToPlane + "; distanceY = " + distanceY);

        resize(distanceToPCorrected, distanceToCenter, targetElipse);
        recenter(distanceX, distanceZ, distanceToPlane, targetElipse, elipseCenter);
        rotate(distanceX, distanceZ, targetElipse);

    }

    private static void rotate(double distanceX, double distanceZ, Ellipse targetElipse) {
        System.out.println("" + Math.atan(distanceZ/distanceX));
        targetElipse.setRotate(Math.atan(distanceZ/distanceX));
    }

    private static void recenter(double distanceX, double distanceZ, double distanceToPlane, Ellipse targetElipse, double elipseCenter) {
        //System.out.println("elipse center = " + elipseCenter + "; distanceZ = " + distanceZ + "; distanceToPlane = " + distanceToPlane);
        targetElipse.setCenterY(elipseCenter - (distanceZ*1000)/distanceToPlane);
        targetElipse.setCenterX(distanceX*1000/distanceToPlane);
    }

    private static void resize(double distanceToPlane, double distanceToCenter, Ellipse targetElipse) {
        double alphaAnlge = Math.atan(distanceToCenter/distanceToPlane);
        //System.out.println("alphaAngle = " + alphaAnlge);
        //double alphaAnlge = Math.atan((distanceToCenter < 30 ? 0 : distanceToCenter - 30)/distanceToPlane);
        //System.out.println("" + 2000.0/distanceToPlane);
        targetElipse.setRadiusX(5000.0/distanceToPlane);
        //System.out.println("" + (Math.cos(alphaAnlge)*2000.0)/distanceToPlane);
        targetElipse.setRadiusY((Math.cos(alphaAnlge)*2000.0)/distanceToPlane);
    }
}
