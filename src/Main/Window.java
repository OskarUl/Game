package Main;

import javax.swing.*;

public class Window {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);

        window.pack();
        window.setVisible(true);
    }
}
