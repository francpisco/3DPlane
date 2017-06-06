package pt.altran.roothless.model;

/**
 * Created by Altran on 06/06/2017.
 */
public class RelativePosition {

    private Plane plane;
    private Bubble bubble;

    private double deltaX;
    private double deltaY;
    private double deltaZ;

    private double angleH;
    private double deltaHAngle;
    private double distanceH;

    private double angleV;
    private double deltaVAngle;
    private double distanceV;

    private double distanceToCenter;
    private double distanceToPlane;

    public RelativePosition(Plane plane, Bubble bubble) {
        this.plane = plane;
        this.bubble = bubble;
    }

    public void updateValues() {
        deltaX = plane.getxPosition() - bubble.getxPosition();
        deltaY = plane.getyPosition() - bubble.getyPosition();
        deltaZ = plane.getzPosition() - bubble.getzPosition();

        angleH = Math.atan(deltaX/deltaY);
        deltaHAngle = plane.getYaw() - angleH;
        distanceH = Math.sqrt(deltaX*deltaX + deltaY*deltaY);

        angleV = Math.atan(deltaZ/deltaY);
        deltaVAngle = plane.getPitch() - angleV;
        distanceV = Math.sqrt(deltaZ*deltaZ + deltaY*deltaY);

        distanceToCenter = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ);
        distanceToPlane = Math.sqrt(deltaY*deltaY + distanceToCenter*distanceToCenter);
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public double getDeltaZ() {
        return deltaZ;
    }

    public double getAngleH() {
        return angleH;
    }

    public double getDeltaHAngle() {
        return deltaHAngle;
    }

    public double getDistanceH() {
        return distanceH;
    }

    public double getAngleV() {
        return angleV;
    }

    public double getDeltaVAngle() {
        return deltaVAngle;
    }

    public double getDistanceV() {
        return distanceV;
    }

    public double getDistanceToCenter() {
        return distanceToCenter;
    }

    public double getDistanceToPlane() {
        return distanceToPlane;
    }
}
