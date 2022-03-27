package gui.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gui.sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean stop;

    /**
     * constructor.
     * @param numOfSeconds number of seconds we wait.
     * @param countFrom .
     * @param gameScreen the screen of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(25, 105, 142));
        d.drawText(370, d.getHeight() / 2, "" + countFrom, 100);
        d.setColor(new Color(25, 112, 153));
        d.drawText(372, d.getHeight() / 2 + 1, "" + countFrom, 100);
        d.setColor(new Color(60, 173, 226));
        d.drawText(374, d.getHeight() / 2 + 2, "" + countFrom, 100);
        sleeper.sleepFor(500);
        countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
