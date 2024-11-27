package zonaCritica;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchCod {
	static int latchCount = Math.max(1, main.hilos / 10);
	static Thread[] threads;

	public static void run() throws InterruptedException {
		System.out.println("Ejecutando con CountDownLatch");

		main.latch = new CountDownLatch(latchCount);
		threads = new Thread[main.hilos];

		for (int i = 0; i < main.hilos; i++) {
			threads[i] = new Thread(new CountDownLatchHilo());
			threads[i].start();
		}

		for (Thread thread : threads)
			thread.join();

		System.out.println("Contador del CountDownLatch: " + main.counter);
	}
}

class CountDownLatchHilo implements Runnable {
	@Override
	public void run() {
		try {
			if (main.latch.getCount() > 0) {
				main.zonaCritica();
				main.latch.countDown();
			} else {
				System.out.println("Hilo detenido: el CountDownLatch es cero");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
