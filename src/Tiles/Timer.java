package Tiles;

public class Timer {
    private long targetTime;
    private boolean done;

    public Timer(int time){
        targetTime = time;
    }

    public void start() {
        new Thread(() -> {
            try {
                Thread.sleep(targetTime);
                done = true;
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        }).start();
    }

    public boolean isDone() {
        if (done) {
            done = false;
            return true;
        }
        return false;
    }
}
