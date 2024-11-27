package ai;

import javax.swing.*;
import java.util.Random;

public class ProgressThread extends Thread {
    private JProgressBar barra;
    private JLabel label;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    // Constantes
    private static final int CRASH_PROBABILITY = 10; // Probabilidad de crash en porcentaje
    private static final int CRASH_AMOUNT = 10; // Cantidad a bajar en caso de crash

    public ProgressThread(JProgressBar barra, JLabel label) {
        this.barra = barra;
        this.label = label;
    }

    @Override
    public void run() {
        while (running && barra.getValue() < 100) {
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
                // Incrementar el valor de la barra
                barra.setValue(barra.getValue() + 1);

                // Probabilidad de crash
                if (new Random().nextInt(100) < CRASH_PROBABILITY) {
                    int nuevoValor = Math.max(0, barra.getValue() - CRASH_AMOUNT);
                    barra.setValue(nuevoValor); // Reducir el valor en caso de crash
                }

                // Actualizar la etiqueta
                label.setText(label.getText().split(":")[0] + ": " + barra.getValue() + "%");

                // Espera aleatoria entre incrementos
                Thread.sleep(new Random().nextInt(200) + 50); // Entre 50 y 250 ms

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void pausar() {
        paused = true;
    }

    public void continuar() {
        paused = false;
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    public void detener() {
        running = false;
        continuar(); // Asegurar que se despierte si estaba pausado
    }
}
