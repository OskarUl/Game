package GameState;

import GameContent.Player;
import Main.GamePanel;
import Tiles.Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirstState extends GameState{
    Image image;
    Player player;
    boolean bPressed = false;
    Background bgClouds;
    private HashMap activeKeys = new HashMap<Integer, Boolean>();

    public FirstState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Resources/GameState1BG.png")).getScaledInstance(GamePanel.WIDTH, GamePanel. HEIGHT, Image.SCALE_SMOOTH);
            bgClouds = new Background("/Resources/clouds.png", 0);
            bgClouds.setVector(-1,0);
            player = new Player();
            player.useStamina(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        //if (bPressed)
        player.update();
        bgClouds.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, 0, 0, null);
        bgClouds.render(g);
        player.render(g);
        g.drawString("Stamina: " + Integer.toString(player.stamina), 20,60);
        g.drawString("Health: " + Integer.toString(player.health), 20,120);
    }

    @Override
    public void keyPressed(int k) {
        activeKeys.put(k, true);

        if (!bPressed) {
            if (KeyEvent.VK_A == k) {
                player.runLeft();
                bPressed = true;
            }
            if (KeyEvent.VK_D == k) {
                player.runRight();
                bPressed = true;
            }
            if (KeyEvent.VK_SPACE == k) {
                player.jump();
                bPressed = true;
            }
            if (KeyEvent.VK_Q == k) {
                player.attack();
                bPressed = true;
            }
        }
    }

    @Override
    public void keyReleased(int k) {
        activeKeys.put(k, false);
        bPressed = false;
        player.puff();
    }
}
