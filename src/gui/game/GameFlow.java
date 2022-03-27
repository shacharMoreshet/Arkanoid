package gui.game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import gui.animation.AnimationRunner;
import gui.levels.LooseScreen;
import gui.levels.WinScreen;

import java.util.List;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class GameFlow {
    //Fields
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter counterScore;
    private GUI gui;

    /**
     * constructor.
     * @param ar is the animation runner.
     * @param ks is the keyboard sensor.
     * @param gui is thr gui of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.counterScore = new Counter(0);
        this.gui = gui;
    }

    /**
     * tihs method runs the levels of the game.
     * @param levels is the levels in the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        int count = 0;
        // running on the lists of levels
        for (LevelInformation levelInfo : levels) {
            // creating new gameLevel
            GameLevel gameLevel = new GameLevel(levelInfo, this.keyboardSensor,
                    this.counterScore, this.animationRunner, this.gui);
            gameLevel.initialize();
            //level has more blocks and balls
            while (gameLevel.getCounterBlocks().getValue() != 0 && gameLevel.getCounterBalls().getValue() != 0) {
                gameLevel.run();
                System.out.println(gameLevel.getCounterBalls().getValue());
            }
            System.out.println(gameLevel.getCounterBalls().getValue());
            if (gameLevel.getCounterBalls().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new LooseScreen(this.keyboardSensor, this.counterScore)));
                count++;
                break;
            }
        }
        if (count == 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                    new WinScreen(this.keyboardSensor, this.counterScore)));
        }
    }
}
