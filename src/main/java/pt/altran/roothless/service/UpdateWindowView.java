package pt.altran.roothless.service;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;

/**
 * Created by Altran on 25/05/2017.
 */
public class UpdateWindowView {

    public static void updateView(Plane plane, Rectangle rectangle, Circle circle, Bubble bubble) {

        double deltaX = plane.getxPosition() - bubble.getxPosition();
        double deltaY = plane.getyPosition() - bubble.getyPosition();
        double deltaZ = plane.getzPosition() - bubble.getzPosition();

        double angleH = Math.atan(deltaX/deltaY) * 180 / Math.PI;
        double angleV = Math.atan(deltaZ/deltaY);

        double distanceToCenter = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ);
        double distanceToPlane = Math.sqrt(deltaY*deltaY + distanceToCenter*distanceToCenter);

        resize(distanceToPlane, distanceToCenter, circle);
        recenter(deltaX, deltaZ, distanceToPlane, circle);

        //circle.setCenterX((Math.tan(angleH) * 0.45) * 600);

        updatePitch(plane, rectangle);

        updateRoll(plane, rectangle);

        double yawInDeg = plane.getYaw() * 180 / Math.PI;
        System.out.println("angle H = " + angleH);
        System.out.println("yaw = " + yawInDeg);
    }

    private static void recenter(double distanceX, double distanceZ, double distanceToPlane, Circle circle) {
        System.out.println("circle X = " + circle.getCenterX());
        circle.setCenterY(0.0 - (distanceZ*1000)/distanceToPlane);
        circle.setCenterX(distanceX*1000/distanceToPlane);
    }

    private static void resize(double distanceToPlane, double distanceToCenter, Circle circle) {
        circle.setRadius(2000.0/distanceToPlane);
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
