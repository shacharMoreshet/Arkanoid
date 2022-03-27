package gui.game;
import gui.collision.Collidable;
import gui.collision.CollisionInfo;
import gui.shapes.Line;
import gui.shapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shachar Moreshet, 209129618.
 * The class is a collection of objects a Ball can collide with.
 */
public class GameEnvironment {
    //Fields
    private List<Collidable> colloidalList;

    /**
     * constructor.
     *
     * @param colloidalList is a list of object the ball can collide into.
     */
    public GameEnvironment(List<Collidable> colloidalList) {
        this.colloidalList = colloidalList;
    }

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.colloidalList = new ArrayList<>();
    }

    /**
     * add the given colloidal to the environment.
     *
     * @param c is an object a ball can collide with.
     */
    public void addCollidable(Collidable c) {
        this.colloidalList.add(c);
    }

    /**
     * accessor.
     *
     * @return colloidalList.
     */
    public List<Collidable> getColloidalList() {
        return this.colloidalList;
    }

    /**
     * finds the closest collision of an object.
     *
     * @param trajectory is the line the object moves on from trajectory.start() to trajectory.end().
     * @return return the information about the closest collision that is going to occur if exists,
     * otherwise null.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        // in case the list is empty
        if (colloidalList.isEmpty()) {
            return null;
        }
        Double minDistance = null;
        CollisionInfo minCollisionInfo = new CollisionInfo(null, null);
        // running on the list of the colloidal objects.
        for (Collidable object : this.colloidalList) {
            // saving the point of the collision
            Point collision = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (collision != null) {
                double distance = trajectory.start().distance(collision);
                // if the distance of the new collision point is smaller we save it as the new closest collision.
                if (minDistance == null || distance < minDistance) {
                    minDistance = distance;
                    minCollisionInfo.setCollisionPoint(collision);
                    minCollisionInfo.setCollisionObject(object);
                }
            }
        } // if the collision point exists we return the closest collision.
        return minDistance == null ? null : minCollisionInfo;
    }
}
