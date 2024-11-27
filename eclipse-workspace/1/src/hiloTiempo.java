public class hiloTiempo implements Runnable {
    private long startTime;
    private long startTime2;
    private long elapsedTime;
    private long currentTime;
    private volatile boolean running = true; 
    private volatile static boolean paused = false; 
    private final static Object pauseLock = new Object(); 

    public hiloTiempo(long startTime) {
        this.startTime = startTime;
        this.elapsedTime = 0;
    }
    
    @Override
    public void run() {
    	startTime2 = System.currentTimeMillis();
        System.out.println("Tiempo inicial: " + startTime);
        currentTime = System.currentTimeMillis();
//        while(currentTime - startTime < 1000* 10) {
//        	
//        	synchronized (pauseLock) {
//                if (paused) {
//                    try {
//                        pauseLock.wait();
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                        return;
//                    }
//                    // Ajustar el tiempo de inicio para compensar la pausa
//                    long pauseDuration = System.currentTimeMillis() - startTime;
//                }
//            }
//        	
//        	if ((currentTime - startTime) % 1000 == 0) {
//                System.out.println("Quedan " + (10000 - (currentTime - startTime)) + " para acabar");
////                i++;
//            } 
//        	
//        	currentTime = System.currentTimeMillis();
//        }
        for (int i = 0; i < 10; ) {
            synchronized (pauseLock) {
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    // Ajustar el tiempo de inicio para compensar la pausa
                    long pauseDuration = System.currentTimeMillis() - startTime;
                    startTime += pauseDuration;
                }
            }
            
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;
            
            if (elapsedTime >= 1000 * i) {
                System.out.println("Quedan " + (10 - i) + " para acabar");
                i++;
            }        
        }
//        
//        elapsedTime = currentTime - startTime2;
        System.out.println("Ha tardado: " + ((currentTime - startTime2) / 1000)+" segundos"); //No se yo
        System.out.println("Tiempo final:   " + currentTime);
        System.out.println("Tiempo inicial: " + startTime2);
    }
    
    public void pausar() {
        paused = true; 
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
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
