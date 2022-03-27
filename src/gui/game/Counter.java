package gui.game;

/**
 * @author Shachr Moreshet, 209129618.
 * The class is responsible for counting (hitting\scores).
 */
public class Counter {
    //Fields
    private int counter;

    /**
     * constructor.
     *
     * @param c is the counter
     */
    public Counter(int c) {
        this.counter = c;
    }

    /**
     * add number to current count.
     *
     * @param number is the number of blocks we added.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number is the number of blocks we removed.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * get current count.
     * @return the value of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}
