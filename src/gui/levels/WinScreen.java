package gui.levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animation.Animation;
import gui.game.Counter;

import java.awt.Color;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class WinScreen implements Animation {
    //Fields score
    private Counter counterScore;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * @param k KeyboardSensor.
     * @param counterScore Counter.
     */
    public WinScreen(KeyboardSensor k, Counter counterScore) {
        this.counterScore = counterScore;
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(165, 98, 219));
        d.drawText(250, d.getHeight() / 2 - 50, "You Win! ", 50);
        d.drawText(252, d.getHeight() / 2 - 48, "You Win! ", 50);
        d.drawText(254, d.getHeight() / 2 - 46, "You Win! ", 50);
        d.drawText(170, d.getHeight() / 2 + 50, " Your score is " + this.counterScore.getValue(), 50);
        //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
