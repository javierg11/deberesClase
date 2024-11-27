package ai;

import javax.swing.JOptionPane;

public class MonitorThread extends Thread {
    private final ProgressThread[] threads;

    public MonitorThread(ProgressThread... threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        while (true) {
            boolean allFinished = true;

            for (ProgressThread thread : threads) {
                if (thread.isAlive()) { 
                    allFinished = false; 
                    break; 
                }
            }

            if (allFinished) {
                JOptionPane.showMessageDialog(null, "¡Una carrera ha terminado!");
                break; // Terminar el hilo si todos han terminado
            }
            
            try {
                Thread.sleep(100); // Comprobar cada 100 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void pausar() {
       for (ProgressThread thread : threads) { 
           thread.pausar(); 
       } 
   }
}
