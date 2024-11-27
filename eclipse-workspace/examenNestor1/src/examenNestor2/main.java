package examenNestor2;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
public class main {
	final static int hilosTotal=70;
	static Vector<Thread> hilos;
	public static void main(String[] args) {
		hilos = generateSemaphoreThreads();
		ejecutarhreads(hilos);
	}
	
	private static Vector<Thread> generateSemaphoreThreads() {
		Semaphore semaphore = new Semaphore(3);
		return generateThreads(() -> semaphoreFunction(semaphore));
	}
	
	private static void semaphoreFunction(Semaphore semaphore) {
		try {
			semaphore.acquire();
			criticalZone();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	private static void ejecutarhreads(Vector<Thread> threads) {
		for (int i = 0; i<threads.size(); i++) {
			threads.get(i).start();
		}
	}

	private static Vector<Thread> generateThreads(Runnable task) {
		Vector<Thread> arrayThreads = new Vector<Thread>(hilosTotal);
		for(int i = 0; i<hilosTotal; i++) {
			arrayThreads.add(new Thread(task));
		}
		return arrayThreads;
	}
	private static void criticalZone() throws InterruptedException {
		System.out.println("El hilo ha entrado en la zona crítica");
		Thread.sleep(100);
		System.out.println("El hilo va a salir de la zona crítica");
	}
}
