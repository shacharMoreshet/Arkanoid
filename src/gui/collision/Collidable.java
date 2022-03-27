package gui.collision;
import biuoop.DrawSurface;
import gui.shapes.Ball;
import gui.shapes.Point;
import gui.shapes.Square.Rectangle;
import gui.sprites.Velocity;

/**
 * @author Shachar Moreshet, 2098129618.
 */
public interface Collidable {
    /**
     * // Return the "collision shape" of the object.
     * @return the rectangle we collide into.
     */
    Rectangle getCollisionRectangle();

    /**
     *  Calculating the new velocity after a collision.
     * @param collisionPoint is the point of the collision.
     * @param currentVelocity is the current velocity.
     * @param hitter is the ball that hits the colloidal.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * draw on a given surface.
     * @param d is the surface.
     */
    void drawOn(DrawSurface d);
}
