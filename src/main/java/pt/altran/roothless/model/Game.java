package pt.altran.roothless.model;

import java.util.Date;

/**
 * Created by Altran on 30/05/2017.
 */
public class Game {

    private Date date;
    private double highScore;

    public Game(Date date, double highScore) {
        this.date = date;
        this.highScore = highScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getHighScore() {
        return highScore;
    }

    public void setHighScore(double highScore) {
        this.highScore = highScore;
    }
}
