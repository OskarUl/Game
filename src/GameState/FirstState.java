package GameState;

import GameContent.Player;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;

public class FirstState extends GameState{
    Image image;
    Player player;
    public FirstState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Resources/bgMenu.png")).getScaledInstance(GamePanel.WIDTH, GamePanel. HEIGHT, Image.SCALE_SMOOTH);
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
        player.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, 0, 0, null);
        player.render(g);
    }

    @Override
    public void keyPressed(int k) {
        if (KeyEvent.VK_LEFT == k) {
            player.runLeft();
        }
        if (KeyEvent.VK_RIGHT == k) {
            player.runRight();
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
