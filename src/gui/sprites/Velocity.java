package gui.sprites;

import gui.shapes.Point;

/**
 * @author Shachar Moreshet 209129618.
 * The class creates velocity to the ball.
 */
public class Velocity {
    // Fields
    private final double dx;
    private final double dy;

    /**
     * constructor.
     *
     * @param dx is the ball's velocity in axis x.
     * @param dy is the ball's velocity in axis y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p is the origin point
     * @return a new point with the velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Take angle and speed and return the velocity in axis x, the velocity in axis y.
     *
     * @param angle is the angle that represents the change in the position of the ball.
     * @param speed is the speed of the ball.
     * @return the velocity according to axis y, axis x.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, -dy);
    }

    /**
     * accessor.
     *
     * @return the velocity in axis x.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * accessor.
     *
     * @return the velocity in axis y.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * convert speed from velocity.
     * @return speed
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}
