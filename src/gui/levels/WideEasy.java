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
public class WideEasy implements LevelInformation {
    //Fields
    private Block background;

    /**
     * constructor.
     */
    public WideEasy() {
        this.background = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.WHITE);
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(180 - (i * 20), 5);
            list.add(v);
        }
        for (int i = 0; i < 5; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(180 + (i * 20), 5);
            list.add(v);
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return new String("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        Color[] rowColors = new Color[]{Color.RED, Color.RED, Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN, Color.GREEN,
                Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN
        };
        for (int i = 0; i < 15; i++) {
            Block block = new Block(new Rectangle(new Point(20 + (51 * i), 200), 51, 20));
            block.setColor(rowColors[i]);
            listOfBlocks.add(block);
        }
        return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
