package GameContent;

import Main.GamePanel;
import Tiles.Animator;
import Tiles.Timer;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Player extends ObjectMap{
    public int health = 100;
    public int stamina = 100;
    private boolean useStamina = false;
    private BufferedImage[] runLeft = new BufferedImage[7];
    private BufferedImage[] runRight = new BufferedImage[7];
    private Timer timer = new Timer(80);
    private boolean puffing = false;
    private boolean dead;

    private Animator animator;
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numImages = {1, 6, 1, 1, 1};

    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int ATTACKING = 3;
    private static final int FALLING = 4;

    private boolean idle, walking, jumping, falling, attacking;


    public Player(){
        collisionHeight = 30;
        collisionWidth = 20;

        width = 300;
        height = 400;

        acceleration = 0.1;
        maxSpeed = 10;
        deceleration = 0.8;
        gravitation = 0;
        jumpHeight = 0;
        maxFallSpeed = 0;

        try {
            sprites = new ArrayList<BufferedImage[]>();
            for (int i = 0; i < 5; i++){
                BufferedImage[] bi = new BufferedImage[numImages[i]];
                for (int a = 0; a < numImages[i]; a++) {

                    bi[a] = ImageIO.read(getClass().getResourceAsStream("/Resources/ManAnimation/RunningRight/runningRight" + a + ".png"));
                    //runLeft[a] = ImageIO.read(getClass().getResourceAsStream("/Resources/ManAnimation/RunningLeft/runningLeft" + a + ".png"));
                }
                sprites.add(bi);
            }

            animator = new Animator();
            currentAction = IDLE;
            animator.setImages(sprites.get(IDLE));
            animator.setDelay(-1);

            //timer.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

   /* private void playSound(String soundString) {
        try {
            File sound = new File(SOUNDPATH + soundString);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void setAttacking(boolean b) {
        attacking = b;
    }


    public void useStamina(boolean useStamina){
        this.useStamina = useStamina;
    }

    public void getNextPosition() {

        if (left) {
            dx -= acceleration;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        }
        else if (right) {
            dx += acceleration;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        }
        else  {
            if (dx > 0) {
                dx -= deceleration;
                if(dx < 0) {
                    dx = 0;
                }
            }
            else if (dx < 0) {
                dx += deceleration;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }

        if(jumping && !falling) {
            dy = jumpHeight;
            falling = true;
        }

        if(falling) {
            dy += gravitation;
            if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }
        x += dx;
        y += dy;
    }

    public void update() {
        /*if (useStamina) {
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
        y += dy;*/




        getNextPosition();
        setPosition(x, y);

        if (left || right){
            if(currentAction!=WALKING){
                currentAction = WALKING;
                animator.setImages(sprites.get(WALKING));
                animator.setDelay(50);
            }
        }
        else if(jumping){
            if(currentAction!=JUMPING){
                currentAction = JUMPING;
                animator.setImages(sprites.get(JUMPING));
                animator.setDelay(-1);
            }
        }
        else if(attacking){
            if(currentAction!=ATTACKING){
                currentAction = ATTACKING;
                animator.setImages(sprites.get(ATTACKING));
                animator.setDelay(-1);
            }
        }
        else if(falling){
            if(currentAction!=FALLING){
                currentAction = FALLING;
                animator.setImages(sprites.get(FALLING));
                animator.setDelay(-1);
            }
        }
        else {
            if(currentAction!=IDLE){
                currentAction = IDLE;
                animator.setImages(sprites.get(IDLE));
                animator.setDelay(-1);
            }
        }

        animator.update();

        if(right) {
            facingRight = true;
        }
        if(left) {
            facingRight = false;
        }
    }

    public void render(Graphics2D g) {
        if(facingRight) {
            g.drawImage(animator.getImage(), (int)(x - width/2) + height,(int)(y - height/2), width, height, null);
        } else {
            g.drawImage(animator.getImage(), (int)(x - width/2) + height,(int)(y - height/2), -width, height, null);
        }
        //animator.render(g);

    }

}
