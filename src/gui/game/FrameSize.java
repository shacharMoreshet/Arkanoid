package gui.game;

import gui.shapes.Point;

/**
 * @author Shachar Moreshet 209129618.
 * determine the frame size.
 */
public class FrameSize {
    //Fields
    private final Point startingP;
    private final int width;
    private final int height;

    /**
     * Constructor.
     * @param startingP is the starting point of the frame (bottom left corner).
     * @param width is the width of the frame.
     * @param height is the height of the frame.
     */
    public FrameSize(Point startingP, int width, int height) {
        this.startingP = startingP;
        this.width = width;
        this.height = height;
    }

    /**
     * accessor.
     * @return the starting point of the frame.
     */
    public Point getStartingP() {
        return this.startingP;
    }

    /**
     * accessor.
     * @return the frame's width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * accessor.
     * @return the frame's height.
     */
    public int getHeight() {
        return this.height;
    }
}
