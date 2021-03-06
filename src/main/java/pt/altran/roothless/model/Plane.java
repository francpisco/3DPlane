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
    private double yVelocity;
    private double zVelocity;

    private double xAcceleration;
    private double yAcceleration;
    private double lift = 9.8;

    private double throtle;
    private double elevatorRight;
    private double elevatorLeft;
    private double flaps;
    private double rudder;

    private double roll;
    private double rollAux;
    private double pitch;
    private double pitchAux;
    private double yaw;

    private double rollVelocity;
    private double pitchVelocity;
    private double yawVelocity;

    private double rollAcceleration;
    private double pitchAcceleration;
    private double yawAcceleration;

    private double acceleration;
    private double speed;
    private double distance;
    private double horizontalDistance;

    private int fuel;



    public void update(double time) {

        rollAux = Physics.angleCalc(roll, rollVelocity, rollAcceleration, time);
        if (rollAux > -30.0 && rollAux < 30.0) {
            roll = rollAux;
            yawVelocity = Physics.yawVelCalc(yawVelocity, yawAcceleration, time);
        }
        yaw = Physics.yawCalc(yaw, yawVelocity, yawAcceleration, time);

        pitchVelocity = Physics.angularVelCalc(pitchVelocity, pitchAcceleration, time);

        pitchAux = Physics.angleCalc(pitch, pitchVelocity, pitchAcceleration, time);
        if (pitchAux > -1.35 && pitchAux < 1.35) {
            pitch = pitchAux;
        }

        distance = Physics.distanceCalc(speed, acceleration, time);
        speed = Physics.VelocityCalc(acceleration, speed, time);

        horizontalDistance = distance * Math.cos(pitch);
        yPosition = yPosition + horizontalDistance * Math.cos(yaw);
        xPosition = xPosition + horizontalDistance * Math.sin(yaw);
        zPosition = zPosition + distance * Math.sin(pitch);

    }

    public void moveRudder(double dValue) {
        rollVelocity = -dValue * 10;
        yawAcceleration = -dValue/10;
    }

    public void moveFlaps(double dValue) {
        pitchAcceleration = -dValue;
    }

    public void moveThrottle(double dValue) {
        acceleration = dValue;
    }

    public double getSpeed() {
        return speed;
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

       // System.out.println("turning value is = " + xAcceleration);
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

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getThrotle() {
        return throtle;
    }

    public void setThrotle(double throtle) {
        this.throtle = throtle;
    }

    public double getElevatorRight() {
        return elevatorRight;
    }

    public void setElevatorRight(double elevatorRight) {
        this.elevatorRight = elevatorRight;
    }

    public double getElevatorLeft() {
        return elevatorLeft;
    }

    public void setElevatorLeft(double elevatorLeft) {
        this.elevatorLeft = elevatorLeft;
    }

    public double getFlaps() {
        return flaps;
    }

    public void setFlaps(double flaps) {
        this.flaps = flaps;
    }

    public double getRudder() {
        return rudder;
    }

    public void setRudder(double rudder) {
        this.rudder = rudder;
    }

    public double getRollVelocity() {
        return rollVelocity;
    }

    public void setRollVelocity(double rollVelocity) {
        this.rollVelocity = rollVelocity;
    }

    public double getPitchVelocity() {
        return pitchVelocity;
    }

    public void setPitchVelocity(double pitchVelocity) {
        this.pitchVelocity = pitchVelocity;
    }

    public double getYawVelocity() {
        return yawVelocity;
    }

    public void setYawVelocity(double yawVelocity) {
        this.yawVelocity = yawVelocity;
    }

    public double getRollAcceleration() {
        return rollAcceleration;
    }

    public void setRollAcceleration(double rollAcceleration) {
        this.rollAcceleration = rollAcceleration;
    }

    public double getPitchAcceleration() {
        return pitchAcceleration;
    }

    public void setPitchAcceleration(double pitchAcceleration) {
        this.pitchAcceleration = pitchAcceleration;
    }

    public double getYawAcceleration() {
        return yawAcceleration;
    }

    public void setYawAcceleration(double yawAcceleration) {
        this.yawAcceleration = yawAcceleration;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
