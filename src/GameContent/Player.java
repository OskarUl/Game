package GameContent;

import Tiles.Animator;

import javax.imageio.ImageIO;
import java.awt.*;

public class Player {
    private int health = 100;
    private double stamina = 100;
    private boolean useStamina;
    private Image[] runLeft = new Image[5];
    private Image[] runRight = new Image[5];
    private Animator animator;

    public Player(){
        try {
            for (int i = 0; i < 5; i++) {
                runRight[i] = ImageIO.read(getClass().getResourceAsStream("/Resources/ManAnimation/RunningRight/runningRight" + i + ".png")).getScaledInstance(400, 600, Image.SCALE_SMOOTH);
                runLeft[i] = ImageIO.read(getClass().getResourceAsStream("/Resources/ManAnimation/RunningLeft/runningLeft" + i + ".png")).getScaledInstance(400, 600, Image.SCALE_SMOOTH);
            }
            animator = new Animator(runRight , 0, 800);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void runRight(){
        //animator = new Animator(runRight , 0, 800);
        animator.setVector(20, 0 );
    }

    public void runLeft(){
        //animator = new Animator(runLeft , 0, 800);
        animator.setVector(-20, 0 );
    }

    public void jump(){

    }

    public void attack(){

    }


    public void useStamina(boolean useStamina){
        this.useStamina = useStamina;
    }

    public void update(){
        animator.update();
    }

    public void render(Graphics2D g) {
        animator.render(g);
    }

}