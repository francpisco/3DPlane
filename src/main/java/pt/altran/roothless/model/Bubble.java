package pt.altran.roothless.model;


import pt.altran.roothless.service.Constants;

/**
 * Created by Altran on 23/05/2017.
 */
public class Bubble {

    private double xPosition;
    private double yPosition = Constants.CIRCLE_INITIAL_DIST;
    private double zPosition = Constants.CIRCLE_INITIAL_ALTITUDE;

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getzPosition() {
        return zPosition;
    }

    public void setzPosition(double zPosition) {
        this.zPosition = zPosition;
    }
}
