package pt.altran.roothless.service;


import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;

/**
 * Created by Altran on 23/05/2017.
 */
public class Loop implements Runnable {

    private Plane plane;
    private Bubble bubble;

    private int score;

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

    public Loop(Plane plane, Bubble bubble) {
        this.plane = plane;
        this.bubble = bubble;
    }

    @Override
    public void run() {

        while (true) {

            plane.update(0.020);
            updateValues();

            if (bubble.getyPosition() - plane.getyPosition() < 0) {
                if (distanceToCenter < Constants.MAX_DIST_TO_WIN) {
                    score++;
                }
                bubble.setyPosition(plane.getyPosition() + Constants.CIRCLE_INITIAL_DIST);
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public int getScore() {
        return score;
    }
}
