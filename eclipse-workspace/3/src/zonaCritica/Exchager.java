package zonaCritica;

import java.util.concurrent.Exchanger;

public class Exchager implements Runnable {
	private String result = "";
	private String reci = "";
	private final Exchanger<String> exchanger;
	private final int counter;

	public Exchager(Exchanger<String> exchanger, int counter) {
		this.exchanger = exchanger;
		this.counter = counter;
	}

	@Override
	public void run() {
		try {
			result = "Valor final del contador: " + counter;
			reci = exchanger.exchange(result);
			System.out.println("Envio principal, recive exchager.java: " + reci);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Tarea del Exchanger interrumpida: " + e.getMessage());
		}
	}
}