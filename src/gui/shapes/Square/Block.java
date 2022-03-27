package gui.shapes.Square;

import biuoop.DrawSurface;
import gui.collision.Collidable;
import gui.collision.HitListener;
import gui.collision.HitNotifier;
import gui.game.GameLevel;
import gui.shapes.Ball;
import gui.shapes.Line;
import gui.shapes.Point;
import gui.sprites.Sprite;
import gui.sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shachar Moreshet 209129618.
 * The class creates blocks that objects collide into.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // Fields
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param rectangle is the shape of the block.
     */
    public Block(Rectangle rectangle) {
        this(rectangle, Color.GRAY);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param rectangle is the shape of the block.
     * @param color     is the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * accessor.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * setting color to the block.
     *
     * @param c is the new color of the block.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * @param collisionPoint  is the point of the collision
     * @param currentVelocity is the velocity of the object before the collision
     * @param hitter is the ball that hits the collide.
     * @return the new velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        Line leftSide = rectangle.getRecSides()[0];
        Line rightSide = rectangle.getRecSides()[1];
        Line upperSide = rectangle.getRecSides()[2];
        Line bottomSide = rectangle.getRecSides()[3];
        // in case it hits a corner of the rectangle
        if (collisionPoint.equals(rectangle.getUpperLeft())
                || collisionPoint.equals(leftSide.intersectionWith(bottomSide))
                || collisionPoint.equals(bottomSide.intersectionWith(rightSide))
                || collisionPoint.equals(rightSide.intersectionWith(upperSide))) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            // in case it hits the left or the right side of the rectangle
        } else if (leftSide.isPointInLine(collisionPoint)
                || rightSide.isPointInLine(collisionPoint)) {
            // we change the horizontal direction.
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            // in case it hits the bottom or upper side of the rectangle
        } else if (upperSide.isPointInLine(collisionPoint)
                || bottomSide.isPointInLine(collisionPoint)) {
            // we change the vertical direction.
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // in case there is no collision
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * adding the block to the game.
     *
     * @param game is the game we add the ball into.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * remove from game.
     * @param game is the game.
     */
    public void removeFromGame(GameLevel game) {
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event.
     *
     * @param hitter is the ball that hits the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
