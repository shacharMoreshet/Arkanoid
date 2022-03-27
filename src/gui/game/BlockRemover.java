package gui.game;

import gui.collision.HitListener;
import gui.shapes.Ball;
import gui.shapes.Square.Block;

/**
 * @author Shachar Moreshet.
 * The class is in charge of removing blocks, and updating an availabe-blocks counter.
 */
public class BlockRemover implements HitListener {
    //Fields
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          is the game.
     * @param removedBlocks is the counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed.
     * @param beingHit is the block we hit in.
     * @param hitter .
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeCollidable(beingHit);
        this.game.removeSprite(beingHit);
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}
