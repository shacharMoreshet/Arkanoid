package gui.shapes.Square;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.collision.Collidable;
import gui.game.GameLevel;
import gui.shapes.Ball;
import gui.shapes.Line;
import gui.shapes.Point;
import gui.sprites.Sprite;
import gui.sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class Paddle implements Sprite, Collidable {
    // Fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private Velocity velocity;

    private static final int MOVE = 10;

    /**
     * Constructor.
     *
     * @param keyboard  is the key board sensor.
     * @param rectangle is the shape of the paddle.
     * @param color     is the color of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
        this.velocity = new Velocity(10, 0);
    }

    /**
     * move the paddle left.
     */
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - MOVE;
        double y = this.rectangle.getUpperLeft().getY();
        // check that the paddle does not get outside of the frame.
        if (this.rectangle.getUpperLeft().getX() < 29) {
            return;
        } // moving the paddle tp the left.
        rectangle = new Rectangle(new Point(newX, y), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * move the paddle right.
     */
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + MOVE;
        double y = this.rectangle.getUpperLeft().getY();
        // check that the paddle does not get outside of the frame.
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() > 770) {
            return;
        } // moving the paddle tp the right.
        rectangle = new Rectangle(new Point(newX, y), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * moving the paddle.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the paddle.
     *
     * @param d is the surface.
     */
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

    /**
     * getting the rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * calculating the velocity after the hit in the paddle.
     *
     * @param collisionPoint  is the point of the collision.
     * @param currentVelocity is the current velocity.
     * @param hitter          is the ball that hits the collide.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double size = this.rectangle.getWidth() / 5; // saving the size of every section.
        ArrayList<Line> arrayLines = new ArrayList<>(); // creating array list to save the lines.
        Point start = this.rectangle.getUpperLeft(); // start point of the paddle
        double endX = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth();
        Point end = new Point(endX, this.rectangle.getUpperLeft().getY()); // end point of the paddle
        // adding the sections of the paddle to array list.
        for (int i = 0; i < 5; i++) {
            double newStartX = start.getX() + (size * i);
            double newEndX = newStartX + size;
            Line l = new Line(new Point(newStartX, start.getY()), new Point(newEndX, end.getY()));
            arrayLines.add(l);
        }
        // in case the ball hits the first section of the paddle.
        if (arrayLines.get(0).isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            // in case the ball hits the second section of the paddle.
        } else if (arrayLines.get(1).isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            // in case the ball hits the third section of the paddle.
        } else if (arrayLines.get(2).isPointInLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            // in case the ball hits the forth section of the paddle.
        } else if (arrayLines.get(3).isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            // in case the ball hits the fifth section of the paddle.
        } else if ((arrayLines.get(4).isPointInLine(collisionPoint))) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            // in case it hits the left or right side of the paddle.
        } else {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * set the velocity of the paddle.
     * @param dx is the new location of the paddle in axis x.
     */
    public void setVelocity(int dx) {
        this.velocity = new Velocity(dx, this.velocity.getDy());
    }

}
