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

public class FirstState extends GameState {
    Image image;
    Player player;
    boolean bPressed = false;
    Background bgClouds;
    Background bgSea;
    Background bgPlatform;
    private ArrayList activeKeys = new ArrayList<Integer>();

    public FirstState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bgSea = new Background("/Resources/GameState1BG.png", 0);
            bgClouds = new Background("/Resources/clouds.png", 5);
            bgClouds.setVector(-2, 0);
            bgPlatform = new Background("/Resources/stoneflor.png", 5);
            player = new Player();
            player.useStamina(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        player.update();
        bgSea.update();
        bgClouds.update();
        bgPlatform.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, 0, 0, null);
        bgSea.render(g);
        bgClouds.render(g);
        bgPlatform.render(g);
        player.render(g);
        g.drawString("Stamina: " + Integer.toString(player.stamina), 20, 60);
        g.drawString("Health: " + Integer.toString(player.health), 20, 120);
    }

    @Override
    public void keyPressed(int k) {
        activeKeys.add(k);
        activeKeys.forEach(key -> {
            switch ((int) key) {
                case KeyEvent.VK_D:
                    player.runRight();
                    bgSea.setVector(-1, 0);
                    bgClouds.setVector(-1.5, 0);
                    bgPlatform.setVector(-20, 0);
                    bPressed = true;
            }
        });

        if (KeyEvent.VK_A == k) {
            player.runLeft();
            bgSea.setVector(1, 0);
            bgClouds.setVector(0.5, 0);
            bgPlatform.setVector(20, 0);
            bPressed = true;
        }
        if (KeyEvent.VK_D == k) {

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



    @Override
    public void keyReleased(int k) {
        activeKeys.remove((Object) k);
        bPressed = false;
        player.puff();
    }
}
