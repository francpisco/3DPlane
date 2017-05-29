package pt.altran.roothless.service;

/**
 * Created by Altran on 22/05/2017.
 */
public class Physics {

    public static double zVelocityCalc(double lift, double currentZVel, double timeInterval) {
        return (lift - Constants.GRAVITY) * timeInterval + currentZVel;
    }

    public static double zPositionCalc(double zInitPosition, double zInitVelocity, double lift, double timeInterval) {
        return zInitPosition + zInitVelocity * timeInterval + (1/2) * (lift - Constants.GRAVITY) * timeInterval * timeInterval;
    }

    public static double VelocityCalc(double acceleration, double currentVel, double timeInterval) {
        return (acceleration - currentVel * Constants.DRAG_COEFICIENT) * timeInterval + currentVel;
    }

    public static double distanceCalc(double initVelocity, double acceleration, double timeInterval) {
        return initVelocity * timeInterval + (1/2) * (acceleration) * timeInterval * timeInterval;
    }

    public static double angleCalc(double initAngle, double initAngularVel, double angularAccel, double timeInterval) {
        return initAngle + initAngularVel * timeInterval + (1/2) * angularAccel * timeInterval * timeInterval;
    }

    public static double angularVelCalc(double initAngularVel, double angularAccel, double timeInterval) {
        return (angularAccel - initAngularVel * Constants.ROT_INERCIA) * timeInterval + initAngularVel;
    }

    public static double yawCalc(double initAngle, double initAngularVel, double angularAccel, double timeInterval) {
        return initAngle + initAngularVel * timeInterval + (1/2) * angularAccel * timeInterval * timeInterval;
    }

    public static double yawVelCalc(double initAngularVel, double angularAccel, double timeInterval) {
        return (angularAccel - initAngularVel * Constants.ROT_INERCIA) * timeInterval + initAngularVel;
    }
}
