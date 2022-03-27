package gui.game;

import gui.collision.HitListener;
import gui.shapes.Ball;
import gui.shapes.Square.Block;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class ScoreTrackingListener implements HitListener {
    //Fields
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter is the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * we increase the scores by 5 points when the hitting happens.
     * @param beingHit is the object we hit into.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
