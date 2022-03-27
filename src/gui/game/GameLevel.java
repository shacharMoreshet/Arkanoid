package gui.game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gui.animation.Animation;
import gui.animation.AnimationRunner;
import gui.animation.CountdownAnimation;
import gui.animation.PauseScreen;
import gui.collision.Collidable;
import gui.shapes.Ball;
import gui.shapes.Point;
import gui.shapes.Square.Block;
import gui.shapes.Square.Paddle;
import gui.shapes.Square.Rectangle;
import gui.sprites.ScoreIndicator;
import gui.sprites.Sprite;
import gui.sprites.SpriteCollection;
import gui.sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shachar Moreshet, 209129618.
 * Game class holds the sprites and the collidables,
 * and incharge of the animation.
 */
public class GameLevel implements Animation {
    // Fields.
    private SpriteCollection sprites = new SpriteCollection(new ArrayList<>());
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;
    private Paddle paddle;

    /**
     * Constructor.
     * @param level is the level of the game.
     * @param keyboard is the keyboard of the game.
     * @param counterScore counts the scores of the current level.
     * @param animationRunner runs the animation.
     * @param gui is the gui of the game.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard,
                     Counter counterScore, AnimationRunner animationRunner, GUI gui) {
        this.gui = gui;
        this.counterBlocks = new Counter(level.numberOfBlocksToRemove());
        this.counterBalls = new Counter(level.numberOfBalls());
        //this.counterBalls = new Counter(0);
        this.counterScore = counterScore;
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.level = level;
    }

    /**
     * getter.
     * @return number of blocks remained in the game.
     */
    public Counter getCounterBlocks() {
        return this.counterBlocks;
    }

    /**
     * getter.
     * @return number of balls remained in the game.
     */
    public Counter getCounterBalls() {
        return this.counterBalls;
    }

    /**
     * @param c is the Colloidal object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param s is the Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle),
     * and add them to the game.
     */
    public void initialize() {
        createBackground();
        createFrame();
        createPaddle();
        createBalls();
        createBlockDeath();
        createBlocks();
        createScoreIndicator();
    }

    /**
     * creates the Background.
     */
    public void createBackground() {
        addSprite(this.level.getBackground());
    }

    /**
     * create the score indicator of the current level.
     */
    public void createScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(Color.BLACK, this.counterScore);
        scoreIndicator.addToGame(this);
    }

    /**
     * creates the blocks of the current level.
     */
    public void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.counterBlocks);
        ScoreTrackingListener score = new ScoreTrackingListener(this.counterScore);
        List<Block> listOfBlocks = this.level.blocks();
        for (Block listOfBlock : listOfBlocks) {
            listOfBlock.addToGame(this);
            listOfBlock.addHitListener(blockRemover);
            listOfBlock.addHitListener(score);
        }
    }

    /**
     * create the frame of the level.
     */
    public void createFrame() {
        Block upperBlock = new Block(new Rectangle(new Point(0, 25), 800, 20));
        Block leftBlock = new Block(new Rectangle(new Point(0, 25), 20, 580));
        Block rightBlock = new Block(new Rectangle(new Point(780, 25), 20, 580));
        upperBlock.setColor(Color.PINK);  // coloring the block of the frame.
        leftBlock.setColor(Color.PINK);
        rightBlock.setColor(Color.PINK);
        upperBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
    }

    /**
     * creates the death block.
     */
    public void createBlockDeath() {
        BallRemover ballRemover = new BallRemover(this, this.counterBalls);
        Block bottomBlock = new Block(new Rectangle(new Point(0, 620), 800, 20));
        bottomBlock.addToGame(this);
        bottomBlock.addHitListener(ballRemover);

    }

    /**
     * set the paddle.
     * @param paddle1 is the paddle of the current level.
     */
    public void setPaddle(Paddle paddle1) {
        this.paddle = paddle1;
    }

    /**
     * create the paddle of the game.
     */
    public void createPaddle() {
        biuoop.KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        // creating new paddle.
        Paddle currPaddle = new Paddle(keyboardSensor, new Rectangle(new Point(305, 560),
                this.level.paddleWidth(), 20), Color.WHITE);
        currPaddle.setVelocity(this.level.paddleSpeed());
        currPaddle.addToGame(this);
        setPaddle(currPaddle);
    }

    /**
     * creates the balls.
     */
    public void createBalls() {
        List<Velocity> listOfVelocities = this.level.initialBallVelocities();
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(375, 560), 4, Color.BLUE);
            ball.setGameEnvironment(environment);
            ball.setVelocity(listOfVelocities.get(i));
            ball.addToGame(this);
            ball.setPaddle(paddle);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * remove Collidable.
     *
     * @param c is the collidable we remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getColloidalList().remove(c);
    }

    /**
     * @param s is the sprite we delete.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.counterBlocks.getValue() <= 0) {
            this.counterScore.increase(100);
            this.running = false;
        }
        if (this.counterBalls.getValue() <= 0) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        d.drawText(570, 20, "Level Name: " + level.levelName(), 15);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen(this.keyboard)));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
