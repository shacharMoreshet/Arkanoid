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
 * THis class represent level 3.
 */
public class Green3 implements LevelInformation {
    //Fields
    private Block background;

    /**
     * constructor.
     */
    public Green3() {
        this.background = new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(137, 210, 104));
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(100, 6);
        Velocity v2 = Velocity.fromAngleAndSpeed(200, 5);
        list.add(v1);
        list.add(v2);
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
        return new String("Green3");
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        double width = 50;
        double height = 20;
        Color[] rowColors = new Color[]{
                new Color(1f, 0f, 0.5f), new Color(1f, 0.4f, 0.4f),
                new Color(1f, 0.6f, 0.2f), new Color(1f, 1f, 0f),
                new Color(0f, 1f, 0f), new Color(0.2f, 0.2f, 1f)
        };
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Block block = new Block(new Rectangle(new Point(730 - width * j, 200 + height * i), 50, 20));
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
