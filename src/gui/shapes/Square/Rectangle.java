package gui.shapes.Square;
import gui.shapes.Line;
import gui.shapes.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shachar Moreshet, 209129618.
 * the class creates a rectangle.
 * A rectengle has a upper point, width, heighet and array of it's sides lines.
 */
public class Rectangle {
    // Fields
    private Point upperLeft;
    private double width;
    private double height;
    // [0]-leftSide, [1]-rightSide, [2]-upperSide, [3]-bottomSide;
    private Line[] arrayLines;

    /**
     * constructor
     * Create a new rectangle with location, width/height and array of it's sides.
     *
     * @param upperLeft is the upper left point of the rectangle.
     * @param width     is the width of the rectangle.
     * @param height    is the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.arrayLines = getRecSides();
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line is the line we check for intersection with
     * @return list of the intersection point of the line with the rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // creating a list of intersection points of the line with the rectangle.
        List<Point> listOfPoint = new ArrayList<>();
        // running on the array of the sides lines of the rectangle
        for (Line l : this.arrayLines) {
            // saving the intersection point
            Point intersectionP = l.intersectionWith(line);
            // in case there is an intersecting point, enter it to the list.
            if (intersectionP != null) {
                listOfPoint.add(intersectionP);
            }
        }
        return listOfPoint;
    }

    /**
     * accessor.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * accessor.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * accessor.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * creating an array of the lines that represents the rectangle's sides.
     *
     * @return array of the rectangle's sides.
     */
    public Line[] getRecSides() {
        Line[] arrayOfLines = new Line[4]; // creating an array for the sides of the rectangle.
        // calculating the upper right point of the rectangle.
        Point upperRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        // calculating the bottom left point of the rectangle.
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        // calculating the bottom right point of the rectangle.
        Point bottomRight = new Point(bottomLeft.getX() + this.width, bottomLeft.getY());
        Line leftSide = new Line(upperLeft, bottomLeft);
        Line rightSide = new Line(upperRight, bottomRight);
        Line upperSide = new Line(upperLeft, upperRight);
        Line bottomSide = new Line(bottomLeft, bottomRight);
        // saving the new lines in the array.
        arrayOfLines[0] = leftSide;
        arrayOfLines[1] = rightSide;
        arrayOfLines[2] = upperSide;
        arrayOfLines[3] = bottomSide;
        return arrayOfLines;
    }
}
