package zonaCritica;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierCod {
	static int barrierSize = Math.max(1, main.hilos / 10);
	static Thread[] threads;

	public static void run() throws InterruptedException {
		System.out.println("Ejecutando con CyclicBarrier");

		main.barrier = new CyclicBarrier(barrierSize);
		threads = new Thread[main.hilos];

		for (int i = 0; i < main.hilos; i++) {
			threads[i] = new Thread(new CyclicBarrierHilo());
			threads[i].start();
		}

		for (Thread thread : threads)
			thread.join();

		System.out.println("Contador del CyclicBarrier: " + main.counter);
	}

}

class CyclicBarrierHilo implements Runnable {
	@Override
	public void run() {
		try {
			main.barrier.await();
			main.zonaCritica();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
