package pt.altran.roothless.service;


import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;

/**
 * Created by Altran on 23/05/2017.
 */
public class Loop implements Runnable {

    Plane plane;
    Bubble bubble;

    public Loop(Plane plane, Bubble bubble) {
        this.plane = plane;
        this.bubble = bubble;
    }

    @Override
    public void run() {

        while (true) {

            plane.update(0.020);

            if (bubble.getyPosition() - plane.getyPosition() < 0) {
                bubble.setyPosition(plane.getyPosition() + Constants.CIRCLE_INITIAL_DIST);
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
