package gui.shapes;
/**
 * @author Shachar Moreshet 209129618
 * The class creates dimensional point
 */
public class Point {
    // Fields
    private final double x;
    private final double y;

    private static double epsilon = Math.pow(10, -12);

    /**
     * constructor.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other is another point
     * @return the distance between the point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other is another point
     * @return true or false
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return ((Math.abs(this.x - other.x) < epsilon) && (Math.abs(this.y - other.y) < epsilon));
        //return this.x == other.x && this.y == other.y;
    }

    /**
     * getX get the value of x.
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY get the value of y.
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * printing a point.
     * @return a string.
     */
    public String toString() {
        return "Point{" + "x=" + this.getX() + ", y=" + this.getY() + '}';
    }
}