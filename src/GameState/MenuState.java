package GameState;

import Main.GamePanel;
import Tiles.Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;

public class MenuState extends GameState{

    private int currentChoice = 0;
    private String[] options = {"Start", "Quit"};
    private Font titleFont;
    private Font font = new Font(null, Font.PLAIN, 40);
    private Background bg;
    private Image image;

    MenuState(GameStateManager gsm) {
        this.gsm = gsm;


        try{
            bg = new Background("clouds.png", 0);
            bg.setVector(-1, 0);
            image = ImageIO.read(getClass().getResourceAsStream("bgMenu.png")).getScaledInstance(GamePanel.WIDTH, GamePanel. HEIGHT, Image.SCALE_SMOOTH);
            InputStream is = getClass().getResourceAsStream("lunchds.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(125f);
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
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image,0,0,null);
        bg.render(g);

        g.setColor(Color.darkGray);
        g.setFont(titleFont);
        g.drawString("GAME", GamePanel.WIDTH/2-145, 300);

        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if (i == currentChoice) {
                g.setColor(Color.CYAN);
            }
            else {
                g.setColor(Color.white);
            }
            g.drawString(options[i], GamePanel.WIDTH/2 - 50, 500 + i *70);
        }
    }

    private void select() {
        switch (currentChoice){
            case 0:
                System.out.println("Start");
                break;
            case 1:
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
