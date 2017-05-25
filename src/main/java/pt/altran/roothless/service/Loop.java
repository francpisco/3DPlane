package pt.altran.roothless.service;


import pt.altran.roothless.model.Circle;
import pt.altran.roothless.model.Plane;

/**
 * Created by Altran on 23/05/2017.
 */
public class Loop implements Runnable {

    Plane plane;
    Circle circle;

    public Loop(Plane plane, Circle circle) {
        this.plane = plane;
        this.circle = circle;
    }

    @Override
    public void run() {

        while (true) {

            plane.update(0.020);

            if (circle.getyPosition() - plane.getyPosition() < 0) {
                circle.setyPosition(plane.getyPosition() + Constants.CIRCLE_INITIAL_DIST);
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
