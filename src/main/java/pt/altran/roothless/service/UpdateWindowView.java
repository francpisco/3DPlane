package pt.altran.roothless.service;

import javafx.scene.shape.Circle;
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

        double angleH = Math.atan(deltaX/deltaY);
        double deltaHAngle = plane.getYaw() - angleH;
        double distanceH = Math.sqrt(deltaX*deltaX + deltaY*deltaY);

        double angleV = Math.atan(deltaZ/deltaY);
        double deltaVAngle = plane.getPitch() - angleV;
        double distanceV = Math.sqrt(deltaZ*deltaZ + deltaY*deltaY);


        double distanceToCenter = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ);
        double distanceToPlane = Math.sqrt(deltaY*deltaY + distanceToCenter*distanceToCenter);

        resize(distanceToPlane, distanceToCenter, circle);
        recenter2(distanceH, deltaHAngle, distanceV, deltaVAngle, circle);

        updatePitch(plane, rectangle);

        updateRoll(plane, rectangle);

        double yawInDeg = plane.getYaw() * 180 / Math.PI;
    }

    private static void recenter2(double distanceH, double deltaHAngle, double distanceV, double deltaVAngle, Circle circle) {
        circle.setCenterX(Math.sin(deltaHAngle) * 1280);
        circle.setCenterY(Math.sin(deltaVAngle) * 800);
    }

    private static void recenter(double distanceX, double distanceZ, double distanceToPlane, Circle circle) {
        circle.setCenterY(0.0 - (distanceZ*1000)/distanceToPlane);
        circle.setCenterX(distanceX*1000/distanceToPlane);
    }

    private static void resize(double distanceToPlane, double distanceToCenter, Circle circle) {
        circle.setRadius(2560.0/distanceToPlane);
    }

    private static void updateRoll(Plane plane, Rectangle rectangle) {
        rectangle.setRotate(plane.getRoll());

    }

    private static void updatePitch(Plane plane, Rectangle rectangle) {
        System.out.println("Rectangle Y = " + rectangle.getY());
        System.out.println("Plane pitch = " + plane.getPitch());
        rectangle.setY((Math.tan(plane.getPitch()) * 214) + 71);
    }
}
