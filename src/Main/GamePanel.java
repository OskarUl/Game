package Main;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int WIDTH;
    public static int HEIGHT;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    GamePanel(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        WIDTH = ((int) tk.getScreenSize().getWidth());
        HEIGHT = ((int) tk.getScreenSize().getHeight());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        while(running){
            start = System.nanoTime();

            update();
            render();
            showOnScreen();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            if (wait < 0) wait = 0;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gsm.update();
    }

    private void render() {
        gsm.render(g);
    }

    private void showOnScreen() {
        Graphics gS = getGraphics();
        gS.drawImage(image, 0, 0, null);
        gS.dispose();
    }

    private void init() {
        image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        running = true;

        g = (Graphics2D) image.getGraphics();

        gsm = new GameStateManager();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }
}
