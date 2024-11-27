package main;

import java.util.concurrent.Exchanger;

public class ExchangerThread implements Runnable{

	private Exchanger<String> ExchangeFinalMessage;
	private int finalCount;
	
	public ExchangerThread(int finalCount, Exchanger<String> ExchangeFinalMessage) {
		this.ExchangeFinalMessage = ExchangeFinalMessage;
		this.finalCount = finalCount;
	}
	
	@Override
	public void run() {
		try {
			String unusedResponse = ExchangeFinalMessage.exchange(
					"La cantidad de veces que se ha incrementado el contador es: " + finalCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
