package gui.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *@author Shachar Moreshet, 209129618.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param k is the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
@Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
@Override
    public boolean shouldStop() {
        return this.stop;
    }
}
