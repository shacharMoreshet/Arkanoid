import biuoop.GUI;
import biuoop.KeyboardSensor;
import gui.animation.AnimationRunner;
import gui.game.Counter;
import gui.game.GameFlow;
import gui.game.LevelInformation;
import gui.levels.DirectHit;
import gui.levels.FinalFour;
import gui.levels.Green3;
import gui.levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class Ass6Game {
    /**
     * main method, running the game.
     *
     * @param args is array of params from the command line.
     */
    public static void main(String[] args) {
        Counter counterScore = new Counter(0);
        List<LevelInformation> levelInfo = new ArrayList<>();
        DirectHit level1 = new DirectHit();
        WideEasy level2 = new WideEasy();
        Green3 level3 = new Green3();
        FinalFour level4 = new FinalFour();
        List<LevelInformation> existingLevels = new ArrayList<>();
        existingLevels.add(level1);
        existingLevels.add(level2);
        existingLevels.add(level3);
        existingLevels.add(level4);
        if (args.length == 0) { // no args
            levelInfo.add(level1);
            levelInfo.add(level2);
            levelInfo.add(level3);
            levelInfo.add(level4);
        } else {
            for (String arg : args) {
                try {
                    int currentLevel = parseInt(arg);
                    levelInfo.add(existingLevels.get(currentLevel - 1));
                } catch (Exception ignored) {
                }
            }
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), keyboardSensor, gui);
        gameFlow.runLevels(levelInfo);
        AnimationRunner ar = new AnimationRunner(gui);
        gui.close();
    }
}
