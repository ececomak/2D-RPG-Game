package entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected int renderWidth, renderHeight;

    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitbox(Graphics g) {
        // for debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void initHitbox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

//    protected void updateHitbox() {
//        hitbox.x = (int) x;
//        hitbox.y = (int) y;
//        hitbox.width = width;
//        hitbox.height = height;
//    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    // for collision box
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
