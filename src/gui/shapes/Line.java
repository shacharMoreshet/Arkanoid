package gui.shapes;
import gui.shapes.Square.Rectangle;

import java.util.List;

/**
 * @author Shachar Moreshet 209129618
 * A line (actually a line-segment) connects two points -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    // Fields
    private final Point start;
    private final Point end;
    private final double slope;
    private final double yIntersection;

    private static double epsilon = Math.pow(10, -12);

    /**
     * constructor.
     *
     * @param start is the start point of the line
     * @param end   is the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = getSlope();
        this.yIntersection = getYIntersection();
    }

    /**
     * constructor.
     *
     * @param x1 is the x coordinate of the start point
     * @param y1 is the y coordinate of the start point
     * @param x2 is the x coordinate of the end point
     * @param y2 is the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = getSlope();
        this.yIntersection = getYIntersection();
    }

    /**
     * calculate the length of the line,
     * according to the distance between the start point and the end point.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * // calculate the slope of the line, we only get here in case non of the lines is vertical to axis y.
     *
     * @return the slope of the line
     */
    private double getSlope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Calculate the line intersection with the y-axis according to the straight equation.
     *
     * @return the line intersection with axis y.
     */
    private double getYIntersection() {
        return this.start.getY() - this.slope * (this.start.getX());
    }

    /**
     * checks if the line is vertical.
     *
     * @return true if the lines are vertical and false if not.
     */
    private boolean isVertical() {
        return this.start.getX() == this.end.getX();
    }

    /**
     * @param other is another line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // in case the lines have the same slopes and one or more intersection point (coincident lines).
        if (sameSlopesIntersections(other)) {
            return true;
        }
        return this.intersectionWith(other) != null;
    }

    /**
     * checks if there is more than one intersection point.
     *
     * @param other is another line
     * @return true if there is only one intersection point, otherwise false
     */
    private boolean sameSlopesIntersections(Line other) {
        int count = 0;
        if (this.slope == other.slope) {
            // checks if the end point of the other line is in the line range
            if (isInRange(this.start, other.end, this.end)) {
                count++;
            }
            // checks if the start point of the other line is in the line range
            if (isInRange(this.start, other.start, this.end)) {
                count++;
            }
            // checks if the start point in the other line range
            if (isInRange(other.start, this.start, other.end)) {
                count++;
            }
            // checks if the end point in the other line range
            if (isInRange(other.start, this.end, other.end)) {
                count++;
            }
        }
        return (count != 0);
    }

    /**
     * checks the lines intersection for line that is vertical.
     *
     * @param other is another line.
     * @return the point intersection.
     */
    private Point verticalIntersection(Line other) {
        if ((this.start.equals(other.start)) || (this.end.equals(other.start))) {
            return other.start;
        }
        if ((this.start.equals(other.end)) || (this.end.equals(other.end))) {
            return other.end;
        }
        if (this.start.equals(this.end)) {
            return this.start;
        }
        if (other.start.equals(other.end)) {
            return (other.start);
        }
        return null;
    }

    /**
     * The method is used in case that both of the lines are vertical.
     *
     * @param other is another line
     * @return the intersection point of two vertical line if exists.
     * otherwise, return null.
     */
    public Point intersectionBothVertical(Line other) {
        // in that case there is no intersection
        if (this.start.getX() != other.end.getX()) {
            return null;
        }
        double sum = (this.start.distance(this.end) / 2) + (other.start.distance(other.end) / 2);
        if ((Math.abs(this.middle().distance(other.middle()) - sum) < epsilon) || (other.end.equals(other.start))
                || (this.end.equals(this.start))) {
            return this.verticalIntersection(other);
        }
        return null;
    }

    /**
     * The method is used in case only one line is vertical.
     *
     * @param other is another line.
     * @return the intersection point of the line if exists,
     * otherwise return null.
     */
    private Point intersectionOneIsVert(Line other) {
        // saving the vertical line
        Line vertical = this.isVertical() ? this : other;
        // saving the non vertical line
        Line notVertical = this.isVertical() ? other : this;
        double vertX = vertical.start.getX();
        double minNotVertX = Math.min(notVertical.start.getX(), notVertical.end.getX());
        double maxNotVertX = Math.max(notVertical.start.getX(), notVertical.end.getX());
        // in case the x coordinate of the vertical line is in the range of the other line
        if (minNotVertX <= vertX && vertX <= maxNotVertX) {
            double crossY = notVertical.slope * vertX + notVertical.yIntersection;
            double minY = Math.min(vertical.start.getY(), vertical.end.getY());
            double maxY = Math.max(vertical.start.getY(), vertical.end.getY());
            if (minY <= crossY && crossY <= maxY) {
                // return the point intersection
                return new Point(vertX, crossY);
            }
            return null;
        }
        return null;
    }

    /**
     * The method is used for lines with different slopes, it calculate their intersection.
     *
     * @param other is another line.
     * @return the intersection point if exists, otherwise null.
     */
    private Point intersectionDifferentSlopes(Line other) {
        double xIntersection = (other.getYIntersection() - this.yIntersection) / (this.slope - other.slope);
        double yIntersectionWith = this.slope * xIntersection + this.yIntersection;
        Point newP = new Point(xIntersection, yIntersectionWith);
        // in case the intersection point is in the range of both of the lines.
        if (isInRange(this.start, newP, this.end) && isInRange(other.start, newP, other.end)) {
            return newP;
        }
        return null;
    }

    /**
     * returns the intersection of the lines.
     *
     * @param other is another line.
     * @return Returns the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // in case both of them are vertical
        if (this.isVertical() && other.isVertical()) {
            return intersectionBothVertical(other);
        }
        // in case only one of the lines is vertical
        if (this.isVertical() || other.isVertical()) {
            return intersectionOneIsVert(other);
        } else if (this.slope != other.slope) { // in case the slopes are not the same
            return intersectionDifferentSlopes(other);
        }
        // in case the line intersection is the start point of the line
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
            // in case the line intersection is the end point of the line
        } else if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        // in case there is no intersection.
        return null;
    }


    /**
     * The method is checking if the Point check is in the line range.
     *
     * @param start1 is the start point of the line.
     * @param check  is the point we are checking if it is in the range
     * @param end1   is the end point of the line
     * @return true if the point is in the range, otherwise false
     */
    public boolean isInRange(Point start1, Point check, Point end1) {
        /* checking if the distance between the start and end point of the line,
           is the same as - the distance between the check point and the starting point
           the distance between the check point and the ending point. */
        return (Math.abs((check.distance(start1) + check.distance(end1)) - start1.distance(end1)) < epsilon);
    }

    /**
     * @param other is another line.
     * @return true is the lines are equal, false otherwise
     */

    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect is the rectangle.
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listOfPoint = rect.intersectionPoints(this);
        // in case the list is empty
        if (listOfPoint.isEmpty()) {
            return null;
        }
        // in case there is only one point of intersection
        if (listOfPoint.size() == 1) {
            return listOfPoint.get(0);
        } // in case the second point is the closest.
        if (this.start.distance(listOfPoint.get(1)) < this.start.distance(listOfPoint.get(0))) {
            return listOfPoint.get(1);
        }
        return listOfPoint.get(0);
    }

    /**
     * checking if the point is in the line.
     * @param other is a point
     * @return true if the point is on the lines, otherwise null.
     */
    public boolean isPointInLine(Point other) {
        return (Math.abs((other.distance(this.start) + other.distance(end)) - start.distance(end)) < epsilon);
    }
}


