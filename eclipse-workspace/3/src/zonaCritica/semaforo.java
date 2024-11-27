package zonaCritica;

import java.util.concurrent.Semaphore;

public class semaforo {
    public static void run() throws InterruptedException {
        System.out.println("Ejecutando con Semáforo");
        int n1 = Math.max(1, main.hilos / 10);
        main.semaphore = new Semaphore(n1);
        Thread[] threads = new Thread[main.hilos];
        for (int i = 0; i < main.hilos; i++) {
            threads[i] = new Thread(new SemaforoHilo());
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Contador del Semáforo: " + main.counter);
    }
}

class SemaforoHilo implements Runnable {
    @Override
    public void run() {
        try {
            if (main.semaphore.tryAcquire()) { // Si esta en verde o no
            	main.zonaCritica();
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	main.semaphore.release();
        }
    }
}
