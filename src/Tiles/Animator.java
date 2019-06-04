package Tiles;

import Main.GamePanel;

import java.awt.*;

public class Animator {
    int imageState = 0;
    Image[] images;
    int length;
    double x, y, dx, dy;
    boolean rewind = false;

    public Animator(Image[] images, int x, int y) {
        this.x = x;
        this.y = y;
        this.images = images;
        length = images.length - 1;
    }

    public void update() {
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

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getX(){
        return x;
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
