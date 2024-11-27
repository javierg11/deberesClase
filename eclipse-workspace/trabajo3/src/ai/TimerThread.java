package ai;

import javax.swing.*;

public class TimerThread extends Thread {
    private JLabel tiempoLabel;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    public TimerThread(JLabel tiempoLabel) {
       this.tiempoLabel = tiempoLabel; 
   }

   @Override
   public void run() { 
       int secondsPassed = 0; 

       while (running) { 
           synchronized (pauseLock) { 
               while (paused) { 
                   try { 
                       pauseLock.wait(); 
                   } catch (InterruptedException e) { 
                       Thread.currentThread().interrupt(); 
                       return; 
                   } 
               } 
           } 

           try { 
               Thread.sleep(1000); // Espera un segundo 
               secondsPassed++; 
               tiempoLabel.setText("Tiempo transcurrido: " + secondsPassed + "s"); 

           } catch (InterruptedException e) { 
               Thread.currentThread().interrupt(); 
               return; 
           } 
       } 
   }

   public void pausar() { paused = true; }

   public void continuar() { paused = false; synchronized (pauseLock) { pauseLock.notifyAll(); } }

   public void detener() { running = false; continuar(); }
}
