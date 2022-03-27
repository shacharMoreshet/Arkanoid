package gui.sprites;
import biuoop.DrawSurface;

/**
 * @author Shachar Moreshet, 209129618.
 * Sprites can be drawn on the screen, and can be notified that time has passed
 */
public interface Sprite {

    /**
     * draw on the surface.
     * @param d is the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}