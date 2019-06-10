package GameContent;

public class Seagull extends ObjectMap {
    int health = 100;
    boolean confused;
    private int posX;
    private int posY;

    public Seagull(int x, int y) {
        posX = x;
        posY = y;
        // åkrunt random
        // attakera var 10:de sekund
    }
}
