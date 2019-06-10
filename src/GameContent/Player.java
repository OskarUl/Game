package GameContent;

import Main.GamePanel;
import Tiles.Animator;
import Tiles.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class Player {
    public int health = 100;
    public int stamina = 100;
    private boolean useStamina = false;
    private Image[] runLeft = new Image[5];
    private Image[] runRight = new Image[5];
    private int x, y, dx, dy;
    private int height = (int)(GamePanel.HEIGHT * 0.3);
    private int width = (int)(GamePanel.WIDTH * 0.2);
    Timer timer = new Timer(80);
    private boolean puffing = false;
    private Animator animator;

    private final String SOUNDPATH = "C:\\Users\\Hule-Elev\\IdeaProjects\\Game1\\src\\Resources\\Sounds\\";

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
        animator = new Animator(runRight , (int) animator.getX(), (int)animator.getY());
        animator.setVector(20, 0 );
        animator.start();
        puffing = false;

    }

    public void runLeft(){
        animator = new Animator(runLeft , (int) animator.getX(), (int) animator.getY());
        animator.setVector(-20, 0 );
        animator.start();
        puffing = false;
    }

    public void puff(){
        if (stamina < 10) {
            playSound("impact.wav");
        }
        if (animator.getY() < 537) {
            animator.setVector(0,  5);
        } else {
            animator.setVector(0,0);
        }
        animator.start();
        puffing = true;
    }

    public void jump(){
        animator.setVector(0, -5);
        animator.start();
        puffing = false;
    }

    public void attack(){
        puffing = false;
    }

    private void playSound(String soundString) {
        try {
            File sound = new File(SOUNDPATH + soundString);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        x += dx;
        y += dy;
    }

    public void render(Graphics2D g) {
        animator.render(g);

    }

}
