package pt.altran.roothless.service;

/**
 * Created by Altran on 22/05/2017.
 */
public class Physics {

    public static double zVelocityCalc(double lift, double currentZVel, double timeInterval) {
        return (lift - Constants.GRAVITY) * timeInterval + currentZVel;
    }

    public static double yVelocityCalc(double yAcceleration, double currentYVel, double timeInterval) {
        return (yAcceleration - currentYVel * Constants.DRAG_COEFICIENT) * timeInterval + currentYVel;
    }

    public static double xVelocityCalc(double xAcceleration, double currentXVel, double timeInterval) {
        return xAcceleration * timeInterval + currentXVel;
    }

    public static double zPositionCalc(double zInitPosition, double zInitVelocity, double lift, double timeInterval) {
        return zInitPosition + zInitVelocity * timeInterval + (1/2) * (lift - Constants.GRAVITY) * timeInterval * timeInterval;
    }

    public static double yPositionCalc(double yInitPosition, double yInitVelocity, double yAcceleration, double timeInterval) {
        return yInitPosition + yInitVelocity * timeInterval + (1/2) * yAcceleration * timeInterval * timeInterval;
    }

    public static double xPositionCalc(double xInitPosition, double xInitVelocity, double xAcceleration, double timeInterval) {
        return xInitPosition + xInitVelocity * timeInterval + (1/2) * xAcceleration * timeInterval * timeInterval;
    }
}
