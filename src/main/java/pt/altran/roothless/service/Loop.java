package pt.altran.roothless.service;


import pt.altran.roothless.model.Bubble;
import pt.altran.roothless.model.Plane;
import pt.altran.roothless.model.RelativePosition;

/**
 * Created by Altran on 23/05/2017.
 */
public class Loop implements Runnable {

    private Plane plane;
    private Bubble bubble;
    private RelativePosition relativePosition;

    private int score;



    public Loop(Plane plane, Bubble bubble, RelativePosition relativePosition) {
        this.plane = plane;
        this.bubble = bubble;
        this.relativePosition = relativePosition;
    }

    @Override
    public void run() {

        plane.setFuel(Constants.INIT_FUEL);

        while (plane.getFuel() > 0) {

            plane.update(0.020);
            relativePosition.updateValues();
            plane.setFuel(plane.getFuel() - 1);

            if (bubble.getyPosition() - plane.getyPosition() < 0) {
                if (relativePosition.getDistanceToCenter() < Constants.MAX_DIST_TO_WIN) {
                    score++;
                    plane.setFuel(Constants.INIT_FUEL);
                }
                bubble.setyPosition(plane.getyPosition() + Constants.BUBBLE_INITIAL_DIST);
                bubble.setxPosition(bubble.getxPosition() + (Math.random() - 0.5) * Constants.X_BUBBLE_RANDOM_FACTOR);
                bubble.setzPosition(bubble.getzPosition() + (Math.random() - 0.5) * Constants.Z_BUBBLE_RANDOM_FACTOR);
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

    public int getScore() {
        return score;
    }

}
