package gui.animation;

import biuoop.DrawSurface;

/**
 * @author Shachar Moreshet, 209129618.
 */
public interface Animation {
    /**
     * This method runs one frame in the game.
     * @param d is the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * return if it should stop.
     * @return true to stop the game, otherwise false.
     */
    boolean shouldStop();
}
