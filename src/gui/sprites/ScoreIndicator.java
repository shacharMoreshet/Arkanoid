package gui.sprites;

import biuoop.DrawSurface;
import gui.game.Counter;
import gui.game.GameLevel;

import java.awt.Color;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class ScoreIndicator implements Sprite {
    //Fields
    private Color color;
    private Counter currentScore;

    /**
     * constructor.
     * @param color is the color.
     * @param currentScore is the current score in the game.
     */
    public ScoreIndicator(Color color, Counter currentScore) {
        this.color = color;
        this.currentScore = currentScore;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        String score = Integer.toString(this.currentScore.getValue());
        d.drawText(300, 20, "Score:" + score, 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the score indicator to the game.
     * @param game is the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
