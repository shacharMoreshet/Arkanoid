package gui.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animation.Animation;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class KeyPressStoppableAnimation implements Animation {
    //fields
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor is the Keyboard sensor.
     * @param key is the string that stops the game.
     * @param animation .
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (isAlreadyPressed) {
                return;
            }
            this.stop = true;
        }
        isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
