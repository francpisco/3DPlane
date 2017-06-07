package pt.altran.roothless.service;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.model.RelativePosition;

/**
 * Created by Altran on 25/05/2017.
 */
public class UpdateWindowView {

    private RelativePosition relativePosition;

    public UpdateWindowView(RelativePosition relativePosition) {
        this.relativePosition = relativePosition;
    }

    public void updateView(Plane plane, Rectangle rectangle, Circle circle, Bubble bubble) {

        resize(relativePosition.getDistanceToPlane(), circle);
        recenter(relativePosition.getDistanceH(), relativePosition.getDeltaHAngle(), relativePosition.getDistanceV()
                , relativePosition.getDeltaVAngle(), circle);

        updatePitch(plane, rectangle);

        updateRoll(plane, rectangle);

        double yawInDeg = plane.getYaw() * 180 / Math.PI;
    }

    private static void recenter(double distanceH, double deltaHAngle, double distanceV, double deltaVAngle, Circle circle) {
        circle.setCenterX(Math.sin(deltaHAngle) * 1280);
        circle.setCenterY(Math.sin(deltaVAngle) * 800);
    }


    private static void resize(double distanceToPlane, Circle circle) {
        circle.setRadius(2560.0/distanceToPlane);
    }

    private static void updateRoll(Plane plane, Rectangle rectangle) {
        System.out.println("Rectangle X = " + rectangle.getX());
        System.out.println("Plane roll = " + plane.getRoll());
        System.out.println("Plane yaw = " + plane.getYaw());
        System.out.println("Plane yaw vel = " + plane.getYawVelocity());
        System.out.println("Plane yaw accel = " + plane.getYawAcceleration());
        rectangle.setRotate(plane.getRoll());

    }

    private static void updatePitch(Plane plane, Rectangle rectangle) {
        System.out.println("Rectangle Y = " + rectangle.getY());
        System.out.println("Plane pitch = " + plane.getPitch());
        rectangle.setY((Math.tan(plane.getPitch()) * 214) + 71);
    }
}
