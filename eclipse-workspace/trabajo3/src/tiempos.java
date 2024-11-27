import javax.swing.*;

public class tiempos implements Runnable {
    private JProgressBar barra1, barra2, barra3;
    private JLabel la1, la2, la3;
    Thread porcentaje;
    static Thread tiempoRe;
    private JLabel tiempo;
    String gana = "";
    JFrame ventana;
    int numero;

    private volatile boolean running = true; 
    private volatile static boolean paused = false; 
    private final static Object pauseLock = new Object(); 

    public tiempos(JProgressBar barra1, JLabel la1, JProgressBar barra2, JLabel la2,
                   JProgressBar barra3, JLabel la3, JLabel tiempo, JFrame ventana) {
        this.barra1 = barra1;
        this.la1 = la1;

        this.barra2 = barra2;
        this.la2 = la2;

        this.barra3 = barra3;
        this.la3 = la3;

        this.tiempo = tiempo;
        this.ventana = ventana;
    }

    @Override
    public void run() {
        tiempoRe = new Thread(new tiempoReloj(tiempo));
        tiempoRe.start();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int valor = 0, valor2 = 0, valor3 = 0;

        while (valor < 100 && valor2 < 100 && valor3 < 100) {
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
                numero = (int)(Math.random() * 11) - 3;

                if (valor + numero < 0)
                    valor = 0;
                else if (valor + numero > 100) {
                    valor = 100;
                    gana = "Jugador 1";
                } else
                    valor += numero;

                numero = (int)(Math.random() * 11) - 3;
                if (valor2 + numero < 0)
                    valor2 = 0;
                else if (valor2 + numero > 100) {
                    valor2 = 100;
                    gana = "Jugador 2";
                } else
                    valor2 += numero;

                numero = (int)(Math.random() * 11) - 3;
                if (valor3 + numero < 0)
                    valor3 = 0;
                else if (valor3 + numero > 100) {
                    valor3 = 100;
                    gana = "Jugador 3";
                } else
                    valor3 += numero;

                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; 
            }

            
            barra1.setValue(valor);
            barra2.setValue(valor2);
            barra3.setValue(valor3);

            porcentaje = new Thread(new porcentaje(la1, valor, la2, valor2, la3, valor3, gana, ventana));
            porcentaje.start();

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                 
            }
        }

        if (porcentaje != null) {
            porcentaje.interrupt(); 
        }
        if (tiempoRe != null) {
            tiempoRe.interrupt(); 
        }
    }

    public synchronized void pausar() {
        paused = true; 
        tiempoReloj.pause();
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
            tiempoReloj.resume();
        }
    }

    public void detener() {
        running = false; 
        resume();
    }

    public boolean estaPausado() {
        return paused; 
    }
}
