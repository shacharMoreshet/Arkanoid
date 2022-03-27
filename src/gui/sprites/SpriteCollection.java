package gui.sprites;

import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * @author Shchar Moreshet, 209129618.
 * The SpriteCollection holds a collection of sprites.
 */
public class SpriteCollection {
    // Fields
    private ArrayList<Sprite> arrayList;

    /**
     * Constructor.
     *
     * @param arrayList is the array of the Sprites objects.
     */
    public SpriteCollection(ArrayList<Sprite> arrayList) {
        this.arrayList = arrayList;
    }

    /**
     * add Sprite object.
     *
     * @param s is a sprite object.
     */
    public void addSprite(Sprite s) {
        this.arrayList.add(s);
    }

    /**
     * @param s is the sprite we remove.
     */
    public void removeSprite(Sprite s) {
        this.arrayList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d is the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.arrayList) {
            sprite.drawOn(d);
        }
    }
}
