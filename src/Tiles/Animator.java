package Tiles;

import Main.GamePanel;

import java.awt.*;

public class Animator {
    private int imageState = 0;
    private Image[] images;
    private int length;
    private double x, y, dx, dy;
    private boolean rewind = false;
    private boolean run = false;

    public Animator(Image[] images, int x, int y) {
        this.x = x;
        this.y = y;
        this.images = images;
        length = images.length - 1;
    }

    public void update() {
        if (run) {
            if (!rewind) {
                imageState++;
                if (imageState > length) {
                    rewind = true;
                    imageState--;
                }
            } else {
                imageState--;
                if (imageState < 0) {
                    rewind = false;
                    imageState++;
                }
            }

            x += dx;
            y += dy;
        }

    }

    public void start() {
        run = true;
    }

    public void stop() {
        run = false;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void render(Graphics2D g) {
        g.drawImage(images[imageState], (int) x, (int) y, null);
        if (x < 0) {
            x = x + GamePanel.WIDTH;
            g.drawImage(images[imageState], (int) x, (int) y, null);
        }
        if (x > 0) {
            x = x - GamePanel.WIDTH;
            g.drawImage(images[imageState], (int) x, (int) y, null);
        }
    }
}
