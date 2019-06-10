package GameContent;

import Main.GamePanel;
import Tiles.Animator;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;

public abstract class ObjectMap {
    protected double xmap, ymap;

    protected double x, y, dx, dy;

    protected int width, height;

    protected int collisionWidth, collisionHeight;

    protected double xDest, yDest, xTemp, yTemp;
    protected boolean topLeft, topRight, bottomRight, bottomLeft;

    protected Animator animator;
    protected int currentAction;
    protected boolean facingRight;

    protected boolean left, right, jumping, falling;

    //Physics
    protected double acceleration, maxSpeed, deceleration, gravitation, maxFallSpeed, jumpHeight;

    public boolean intersects(ObjectMap o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    public Rectangle getRectangle() {
        return new Rectangle((int)x - collisionWidth, (int) y - collisionHeight, collisionWidth, collisionHeight);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setLeft(Boolean b) {
        left = b;
    }

    public void setRight(Boolean b) {
        right = b;
    }

    public void setJumping(Boolean b) {
        jumping = b;
    }

    public void setFalling(Boolean b) {
        falling = b;
    }

    public boolean onScreen() {
        return (x - width >= 0 && x + width < GamePanel.WIDTH) && (y - height >= 0 && y + height <= GamePanel.HEIGHT);
    }

    public double getX() {
        return x;
    }
}
