package gui.game;

import gui.shapes.Square.Block;
import gui.sprites.Sprite;
import gui.sprites.Velocity;

import java.util.List;

/**
 * @author Shachar Moreshet, 209129618.
 */
public interface LevelInformation {
    /**
     * @return number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return Number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
