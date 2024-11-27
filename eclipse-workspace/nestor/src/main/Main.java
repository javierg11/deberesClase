package main;

import java.util.Vector;
import java.util.concurrent.*;

public class Main {

	private static int generalCounter = 0;
	private static Exchanger<String> ExchangeFinalMessage = new Exchanger<String>();
	
	public static void main(String[] args) {
		int threadAmount = getThreadAmount(args);
		executeRestrictions(threadAmount);
		executeMessageExchanger(generalCounter, ExchangeFinalMessage);
	}
	
	private static void executeRestrictions(int threadAmount) {
		Vector<Thread> threads;
		System.out.println("Comenzando la ejecución de los hilos de los monitores");
		threads = generateMonitorThreads(threadAmount);
		executeThreads(threads);
		System.out.println("Se ha terminado los hilos del semáforo los monitores");
		System.out.println("Comenzando la ejecución de los hilos del semáforo");
		threads = generateSemaphoreThreads(threadAmount);
		executeThreads(threads);
		System.out.println("Se ha terminado los hilos del semáforo");
		System.out.println("Comenzando la ejecución de los hilos de CountDownLatch");
		threads = generateCountDownLatchThreads(threadAmount);
		executeThreads(threads);
		System.out.println("Se ha terminado los hilos de CountDownLatch");
		System.out.println("Comenzando la ejecución de los hilos de CyclicBarrier");
		threads = generateCyclicBarrierThreads(threadAmount);
		executeThreads(threads);
		System.out.println("Se ha terminado los hilos de CyclicBarrier");
	}

	private static void executeThreads(Vector<Thread> threads) {
		for (int i = 0; i<threads.size(); i++) {
			threads.get(i).start();
		}
	}

	private static Vector<Thread> generateThreads(int threadAmount, Runnable task) {
		Vector<Thread> arrayThreads = new Vector<Thread>(threadAmount);
		for(int i = 0; i<threadAmount; i++) {
			arrayThreads.add(new Thread(task));
		}
		return arrayThreads;
	}

	private static Vector<Thread> generateMonitorThreads(int threadAmount) {
		return generateThreads(threadAmount, () -> monitorFunction());
	}
	
	private static synchronized void monitorFunction() {
		try {
			criticalZone();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static Vector<Thread> generateSemaphoreThreads(int threadAmount) {
		Semaphore semaphore = new Semaphore(4);
		return generateThreads(threadAmount, () -> semaphoreFunction(semaphore));
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

	private static Vector<Thread> generateCountDownLatchThreads(int threadAmount) {
		int countDown = 3;
		CountDownLatch latch = new CountDownLatch(countDown);
		Vector<Thread> threads = new Vector<Thread>();
		for (int i = 0; i< countDown; i++) {
			threads.add(new Thread(() -> countDownFunction(latch)));
		}
		threads.addAll(generateThreads(threadAmount - countDown, () -> countDownLatchFunction(latch)));
		return threads;
	}
	
	private static void countDownFunction(CountDownLatch latch) {
		try {
			criticalZone();
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void countDownLatchFunction(CountDownLatch latch) {
		try {
			latch.await();
			criticalZone();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Vector<Thread> generateCyclicBarrierThreads(int threadAmount) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(threadAmount);
		return generateThreads(threadAmount, () -> cyclicBarrierFunction(cyclicBarrier));
	}

	private static void cyclicBarrierFunction(CyclicBarrier cyclicBarrier) {
		try {
			cyclicBarrier.await();
			criticalZone();
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	private static void criticalZone() throws InterruptedException {
		System.out.println("El hilo: " + Thread.currentThread().threadId() + " ha entrado en la zona crítica");
		Thread.sleep(100);
		generalCounter++;
		Thread.sleep(100);
		System.out.println("El hilo: " + Thread.currentThread().threadId() + " va a salir de la zona crítica");
	}
	
	private static void executeMessageExchanger(
			int generalCounter, Exchanger<String> ExchangeFinalMessage) {
		Thread finalThread = new Thread(
				new ExchangerThread(generalCounter, ExchangeFinalMessage)
				);
		finalThread.start();
		
		try {
			String finalMessage = ExchangeFinalMessage.exchange("unused");
			System.out.println(finalMessage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static int getThreadAmount(String[] args) {
		int threadAmount = 50;
		if (args.length > 0) {
			try {
				threadAmount = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("El primer parámetro no es un número entero. Se utiliza este número para indicar la cantidad de hilos a ejecutar. Se utilizará el valor por defecto: " + threadAmount);
				e.printStackTrace();
			}
		}
		return threadAmount;
	}

}
