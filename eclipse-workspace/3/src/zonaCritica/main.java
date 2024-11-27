package zonaCritica;

import java.util.concurrent.*;
import java.util.Scanner;


public class main {
    public static final int hilos = 100;
    public static final int tiempo = 100;
    public static int counter = 0;
    public static final Object monitor = new Object();
    public static Semaphore semaphore;
    public static CountDownLatch latch;
    public static CyclicBarrier barrier;
    public static Exchanger<String> exchanger = new Exchanger<>();
    public static Thread exchangeTh;

    public static void main(String[] args) throws InterruptedException {
    	Scanner scanner = new Scanner(System.in);
    	
    	int numero;
        boolean opcionValida = false;

        do {
            System.out.print("¿Qué quieres hacer?\n1) Monitor\n2) Semáforo\n3) CountDownLatch\n4) CyclicBarrier\nIngrese una opción: ");
            
            // Verificar si el input es un número
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                
                switch(numero) {
                    case 1:
                        counter = 0;
                        monitorRun.run();
                        opcionValida = true;
                        break;
                    case 2:
                        counter = 0;
                        semaforo.run();
                        opcionValida = true;
                        break;
                    case 3:
                        counter = 0;
                        CountDownLatchCod.run();
                        opcionValida = true;
                        break;
                    case 4:
                        counter = 0;
                        CyclicBarrierCod.run();
                        opcionValida = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 4.");
                        break;
                }
            } else {
                System.out.println("Por favor, ingrese un número válido. Del 1 al 4");
                scanner.next(); // Limpiar el buffer del scanner
            }
        } while (!opcionValida);

        scanner.close();
    	
        

        exchangeTh = new Thread(new Exchager(exchanger, counter));
        exchangeTh.start();

        try {
            String men = exchanger.exchange("Mensaje del hilo principal");
            System.out.println(men);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("\nIntercambio en el hilo principal interrumpido: " + e.getMessage());
        }

        
}

    public static void zonaCritica() {
        try {
            Thread.sleep(tiempo);
            counter++;
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
