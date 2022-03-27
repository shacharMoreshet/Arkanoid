package gui.shapes;

import biuoop.DrawSurface;
import gui.collision.CollisionInfo;
import gui.game.FrameSize;
import gui.game.GameLevel;
import gui.game.GameEnvironment;
import gui.shapes.Square.Paddle;
import gui.sprites.Sprite;
import gui.sprites.Velocity;

import java.awt.Color;

/**
 * @author Shachar Moreshet 209129618.
 * creating a Ball. Balls have size (radius), color, and location (a center point).
 */
public class Ball implements Sprite {
    //Fields
    private Point center;
    private final int r;
    private final Color color;
    private Velocity velocity;
    private FrameSize frameSize;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;


    /**
     * constructor.
     *
     * @param center is the center point of the ball.
     * @param r      is the radius of the ball.
     * @param color  is the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frameSize = new FrameSize(new Point(0, 0), 200, 200);

    }

    /**
     * constructor.
     *
     * @param x     is the x coordinate of the center point.
     * @param y     is the y coordinate of the center point.
     * @param r     is the radius of the ball.
     * @param color is the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frameSize = new FrameSize(new Point(0, 0), 200, 200);
    }

    /**
     * constructor.
     *
     * @param center          is the center of the ball.
     * @param r               is the radius of the ball.
     * @param color           is the color of the ball.
     * @param gameEnvironment is a collection of objects a Ball can collide with.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frameSize = new FrameSize(new Point(0, 0), 200, 200);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor.
     *
     * @param x               is the x coordinate of the center point.
     * @param y               is the y coordinate of the center point.
     * @param r               is the radius of the ball.
     * @param color           is the color of the ball.
     * @param gameEnvironment is a collection of objects a Ball can collide with.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frameSize = new FrameSize(new Point(0, 0), 200, 200);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * accessor.
     *
     * @return the x coordinate of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor.
     *
     * @return the y coordinate of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessor.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface is the surface the ball is drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r);
    }

    /**
     * determine the velocity of the ball.
     *
     * @param v is the ball's velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * determine the velocity of the ball.
     *
     * @param dx is the velocity of the ball in axis x.
     * @param dy is the velocity of the ball in axis y.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * accessor.
     *
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * determine the frame size.
     *
     * @param x is the x coordinate of the starting point of the frame.
     * @param y is the y coordinate of the starting point of the frame.
     * @param w is the width of the frame.
     * @param h is the height of the frame.
     */
    public void setFrameSize(int x, int y, int w, int h) {
        this.frameSize = new FrameSize(new Point(x, y), w, h);
    }

    /**
     * moving the ball one step.
     */
    public void moveOneStep() {
        if (isBallInPaddle(this.paddle)) {
            // saving the y coordinate of the upper left point minus the radius.
            double y = this.paddle.getCollisionRectangle().getUpperLeft().getY() - (2 * this.r);
            this.center = new Point(this.center.getX(), y);
            this.velocity = new Velocity(this.velocity.getDx(), -Math.abs(this.velocity.getDy()));
            return;
        }
        double centerX1 = this.center.getX();
        double centerY1 = this.center.getY();
        // the end point of the trajectory is the velocity+the center.
        Point endP = new Point(this.velocity.getDx() + centerX1, this.velocity.getDy() + centerY1);
        Line trajectory = new Line(this.center, endP);
        // saving the information of the closet collision
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        // in case the ball doesn't collide.
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            double xCollision = info.collisionPoint().getX();
            double yCollision = info.collisionPoint().getY();
            Point newPoint = new Point(0, 0);
            // saving the sides of the rectangle in an array.
            Line[] arrayRecSides = info.collisionObject().getCollisionRectangle().getRecSides();
            // in case the collision was in the upper\bottom side of the rectangle
            if (arrayRecSides[2].isPointInLine(info.collisionPoint())
                    || arrayRecSides[3].isPointInLine(info.collisionPoint())) {
                if (this.velocity.getDy() < 0) {
                    // the center of the ball will be almost the collision point.
                    newPoint = new Point(xCollision, yCollision + 1);
                } else {
                    newPoint = new Point(xCollision, yCollision - 1);
                }
            }
            // in case the collision was in the left\right side of the rectangle
            if (arrayRecSides[0].isPointInLine(info.collisionPoint())
                    || arrayRecSides[1].isPointInLine(info.collisionPoint())) {
                if (this.velocity.getDx() > 0) {
                    newPoint = new Point(xCollision - 1, yCollision);
                } else {
                    newPoint = new Point(xCollision + 1, yCollision);
                }
            }
            // saving the new center of the ball.
            this.center = newPoint;
            // saving the velocity after the hit.
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        }
    }

    /**
     * creating Game Environment.
     *
     * @param gameEnvironment1 is a collection of objects a Ball can collide with.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment1) {
        this.gameEnvironment = gameEnvironment1;
    }

    /**
     * accessor.
     *
     * @return gameEnvironment
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * The method moves the ball.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * adding the ball to the game.
     *
     * @param game is the game we add the ball into.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * checking if the ball is on the paddle.
     *
     * @param p is the paddle.
     * @return true if the ball is inside the paddle, else return false.
     */
    public boolean isBallInPaddle(Paddle p) {
        Line[] paddleSides = p.getCollisionRectangle().getRecSides();
        double centerX = this.center.getX();
        double centerY = this.center.getY();
        // in case the ball entered the paddle
        return (centerX >= paddleSides[0].start().getX()) && (centerX <= paddleSides[1].start().getX())
                && centerY >= paddleSides[2].start().getY() && centerY <= paddleSides[3].start().getY();

    }

    /**
     * setting paddle to the ball.
     *
     * @param paddle1 is the paddle of the ball.
     */
    public void setPaddle(Paddle paddle1) {
        this.paddle = paddle1;
    }


}