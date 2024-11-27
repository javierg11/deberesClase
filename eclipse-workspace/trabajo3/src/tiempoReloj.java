import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class tiempoReloj implements Runnable {
    private JLabel tiempo;
    private volatile boolean running = true;
    private volatile static boolean paused = false;
    private final static Object pauseLock = new Object();
    private int currentTime = 0;

    public tiempoReloj(JLabel tiempo) {
        this.tiempo = tiempo;
    }

    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            synchronized (pauseLock) {
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            
            if (!running) break;

            try {
                Thread.sleep(1000);
                SwingUtilities.invokeLater(() -> tiempo.setText("Time: " + currentTime));
                currentTime++;
                if (currentTime >= 15) {
                    currentTime = 0;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void pause() {
        try {
			paused = true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }

    public static void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void stop() {
        running = false;
        resume(); 
    }

    public boolean isPaused() {
        return paused;
    }
}
