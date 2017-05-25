package pt.altran.roothless.model;


import pt.altran.roothless.service.Physics;

/**
 * Created by Altran on 22/05/2017.
 */
public class Plane {

    private double xPosition;
    private double yPosition;
    private double zPosition = 10000;

    private double xVelocity;
    private double yVelocity = 0;
    private double zVelocity;

    private double xAcceleration = 0.0001;
    private double yAcceleration;

    private double lift = 9.8;


    public void update(double time) {

        xPosition = Physics.xPositionCalc(xPosition, xVelocity, xAcceleration, time);
        yPosition = Physics.yPositionCalc(yPosition, yVelocity, yAcceleration, time);
        zPosition = Physics.zPositionCalc(zPosition, zVelocity, lift, time);

        xVelocity = Physics.xVelocityCalc(xAcceleration, xVelocity, time);
        yVelocity = Physics.yVelocityCalc(yAcceleration, yVelocity, time);
        zVelocity = Physics.zVelocityCalc(lift, zVelocity, time);
    }

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

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getzVelocity() {
        return zVelocity;
    }

    public void setzVelocity(double zVelocity) {
        this.zVelocity = zVelocity;
    }

    public double getxAcceleration() {
        return xAcceleration;
    }

    public void setxAcceleration(double xAcceleration) {

        System.out.println("turning value is = " + xAcceleration);
        this.xAcceleration = xAcceleration;
    }

    public double getyAcceleration() {
        return yAcceleration;
    }

    public void setyAcceleration(double yAcceleration) {

       // System.out.println("accelaration value is " + yAcceleration);
        this.yAcceleration = yAcceleration;
    }

    public double getLift() {
        return lift;
    }

    public void setLift(double lift) {
        this.lift = lift;
    }
}
