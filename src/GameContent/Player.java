package GameContent;

import Main.GamePanel;
import Tiles.Animator;
import Tiles.Timer;

import javax.imageio.ImageIO;
import java.awt.*;

public class Player {
    public int health = 100;
    public int stamina = 100;
    private boolean useStamina = false;
    private Image[] runLeft = new Image[5];
    private Image[] runRight = new Image[5];
    private int height = (int)(GamePanel.HEIGHT * 0.3);
    private int width = (int)(GamePanel.WIDTH * 0.2);
    Timer timer = new Timer(80);
    private boolean puffing = false;
    private Animator animator;

    public Player(){
        try {
            for (int i = 0; i < 5; i++) {
                runRight[i] = ImageIO.read(getClass().getResourceAsStream("/Resources/ManAnimation/RunningRight/runningRight" + i + ".png")).getScaledInstance(width, height, Image.SCALE_SMOOTH);
                runLeft[i] = ImageIO.read(getClass().getResourceAsStream("/Resources/ManAnimation/RunningLeft/runningLeft" + i + ".png")).getScaledInstance(width, height, Image.SCALE_SMOOTH);
            }
            timer.start();
            animator = new Animator(runRight , 0, (int)(GamePanel.HEIGHT * 0.7));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void runRight(){
        animator = new Animator(runRight , (int) animator.getX(), (int)(GamePanel.HEIGHT * 0.7));
        animator.setVector(20, 0 );
        puffing = false;

    }

    public void runLeft(){
        animator = new Animator(runLeft , (int) animator.getX(), (int)(GamePanel.HEIGHT * 0.7));
        animator.setVector(-20, 0 );
        puffing = false;
    }

    public void puff(){
        animator.setVector(0, 0);
        puffing = true;
    }

    public void jump(){
        puffing = false;

    }

    public void attack(){
        puffing = false;
    }


    public void useStamina(boolean useStamina){
        this.useStamina = useStamina;
    }

    public void update() {
        if (useStamina) {
            if (stamina == 0) puff();
            if(timer.isDone()){
                if (puffing && stamina < 100){
                    stamina++;
                } else if  (!puffing && stamina > 0){
                    stamina--;
                }
                timer.start();
            }
        }
        animator.update();
    }

    public void render(Graphics2D g) {
        animator.render(g);

    }

}
