package gui.game;

import gui.collision.HitListener;
import gui.shapes.Ball;
import gui.shapes.Square.Block;

/**
 * @author Shacar Moreshet, 209129618.
 * The class is in charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    //Fields
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game          is the game.
     * @param removedBalls is the counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }
}
