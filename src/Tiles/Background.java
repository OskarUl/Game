package Tiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private BufferedImage image;

    private double x, y, dx, dy;

    private double moveScale;

    public Background (String s , double ms) {

        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPosition (double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }

    public void setVector (double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void render(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, null);
        if (x < 0) {
            x = x + GamePanel.WIDTH;
            g.drawImage(image, (int)x, (int)y, null);
        }
        if (x > 0) {
            x = x - GamePanel.WIDTH;
            g.drawImage(image, (int)x, (int)y, null);
        }
    }
}
