package Tiles;

import Main.GamePanel;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Animator {
    private int imageState = 0;
    private BufferedImage[] images;
    private int length;
    private double x, y, dx, dy;
    private boolean rewind;
    private boolean run = false;
    private long startTime;
    private long delay;

    public Animator() {

    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
        imageState = 0;
        rewind = false;
        startTime = System.nanoTime();
        length = images.length - 1;
    }

    public void setDelay (long d) {
        delay = d;
    }

    public void update() {

        if(delay == -1) return;

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > delay) {
            if (!rewind) {
                imageState++;
                if (imageState == length) {
                    rewind = true;
                }
            } else {
                imageState--;
                if (imageState == 0) {
                    rewind = false;
                }
            }
            if(imageState < 0) imageState++;

                startTime = System.nanoTime();
        }
    }

    public BufferedImage getImage() {
        return images[imageState];
    }
}
