package gui.levels;

import gui.game.LevelInformation;
import gui.shapes.Point;
import gui.shapes.Square.Block;
import gui.shapes.Square.Rectangle;
import gui.sprites.Sprite;
import gui.sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class DirectHit implements LevelInformation {
    //Fields
    private Block background;

    /**
     * constructor.
     */
    public DirectHit() {
        this.background = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK);
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(180, 3);
        list.add(v);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return new String("Direct Hit");
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        Block b = new Block(new Rectangle(new Point(340, 300), 75, 75), Color.RED);
        listOfBlocks.add(b);
        return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
