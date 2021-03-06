package GameState;

import GameContent.Player;
import Main.GamePanel;
import Tiles.Animator;
import Tiles.Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MenuState extends GameState{

    private int currentChoice = 0;
    private String[] options = {"Start", "Controls", "Quit"};
    private Font titleFont;
    private Font font;
    private Background bg2;
    private Background bg;
    private BufferedImage[] images = new BufferedImage[5];


    MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try{
            for (int i= 0; i < 5; i++) {
                images[i] = ImageIO.read(getClass().getResourceAsStream("/Resources/ManAnimation/RunningLeft/runningLeft" + i + ".png"));
            }
            //animator = new Animator(images , 0, 800);
            //animator.setVector(20, 0 );
            //player.runRight();
            bg = new Background("/Resources/clouds.png", 0);
            bg.setVector(-1, 0);
            bg2 = new Background("/Resources/MenuBg.png", 1);
            bg2.setVector(-0.5,0);
            InputStream is = getClass().getResourceAsStream("/Resources/lunchds.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);

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
        bg.update();
        bg2.update();
    }

    @Override
    public void render(Graphics2D g) {
        bg2.render(g);
        bg.render(g);


        g.setColor(Color.darkGray);
        g.setFont(titleFont);
        g.drawString("GAME", GamePanel.WIDTH/2-g.getFontMetrics().stringWidth("GAME")/2, (int)(GamePanel.HEIGHT * 0.2));

        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if (i == currentChoice) {
                g.setColor(Color.DARK_GRAY);
            }
            else {
                g.setColor(Color.GRAY);
            }
            g.drawString(options[i], GamePanel.WIDTH/2 - g.getFontMetrics().stringWidth(options[i])/2, (int) (GamePanel.HEIGHT * 0.4) + i * 70);
        }
    }

    private void select() {
        switch (currentChoice){
            case 0:
                gsm.setState(GameStateManager.FIRSSTSTATE);
                break;
            case 1:
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            if (currentChoice == 0){
                currentChoice = options.length - 1;
            }
            else {
                currentChoice--;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            if (currentChoice == options.length-1){
                currentChoice = 0;
            }
            else {
                currentChoice++;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
