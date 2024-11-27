package zonaCritica;

public class monitorRun {
	static Thread[] threads;

	public static void run() throws InterruptedException {
		System.out.println("Ejecutando con Monitor");

		threads = new Thread[main.hilos];
		for (int i = 0; i < main.hilos; i++) {
			threads[i] = new Thread(new MonitorHilo());
			threads[i].start();
		}
		for (Thread thread : threads)
			thread.join();

		System.out.println("Contador del Monitor: " + main.counter);
	}
}

class MonitorHilo implements Runnable {
	@Override
	public void run() {
		synchronized (main.monitor) {
			main.zonaCritica();
		}
	}
}
