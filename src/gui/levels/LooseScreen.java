package gui.levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animation.Animation;
import gui.game.Counter;

import java.awt.Color;

/**
 * @author Shachar Moreshet, 209129618.
 * This class is the animation of the screen when the user losses.
 */
public class LooseScreen implements Animation {
    //Fields score
    private Counter counterScore;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param k is the keyboard sensor.
     * @param counterScore count the score of the game.
     */
    public LooseScreen(KeyboardSensor k, Counter counterScore) {
        this.counterScore = counterScore;
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(206, 139, 83));
        d.drawText(250, d.getHeight() / 2 - 50, "Game Over!", 50);
        d.drawText(252, d.getHeight() / 2 - 48, "Game Over!", 50);
        d.drawText(254, d.getHeight() / 2 - 46, "Game Over!", 50);
        d.drawText(170, d.getHeight() / 2 + 50, " Your score is " + this.counterScore.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
