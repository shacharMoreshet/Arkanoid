package gui.collision;
import gui.shapes.Ball;
import gui.shapes.Square.Block;

/**
 * @author Shachar Moreshet, 209129618.
 * Objects that want to be notified of hit events, should implement the interface HitListener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit is the object we hit into.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
