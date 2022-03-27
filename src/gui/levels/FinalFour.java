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
 * @author Shachar Moreshet, 2098129618.
 */
public class FinalFour implements LevelInformation {
    //Fields
    private Block background;

    /**
     * constructor.
     */
    public FinalFour() {
        this.background = new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(187, 151, 224));
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> list = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(130, 3);
        Velocity v2 = Velocity.fromAngleAndSpeed(180, 3);
        Velocity v3 = Velocity.fromAngleAndSpeed(230, 3);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return new String("Final Four");
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public java.util.List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        double width = 76;
        double height = 20;
        Color[] rowColors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW,
                Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                Block block = new Block(new Rectangle(new Point(705 - width * j, 200 + height * i), 76, 20));
                // color the block
                block.setColor(rowColors[i]);
                listOfBlocks.add(block);
            }
        }
        return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
