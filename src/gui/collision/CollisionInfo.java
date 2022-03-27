package gui.collision;

import gui.shapes.Point;

/**
 * @author Shachar Moreshet, 209129618.
 */
public class CollisionInfo {
    //Fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     * @param collisionPoint is the pont of the collision.
     * @param collisionObject is the object we collide into.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     * @return the collisionPoint.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * creating collision point.
     * @param p is the collision point.
     */
    public void setCollisionPoint(Point p) {
        this.collisionPoint = p;
    }

    /**
     * creating collision object.
     * @param object is the object we collide into.
     */
    public void setCollisionObject(Collidable object) {
        this.collisionObject = object;
    }

    /**
     *  accessor.
     * @return colloidal object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
