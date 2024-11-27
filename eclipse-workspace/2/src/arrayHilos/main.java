package arrayHilos;

public class main {
	static Thread cuenta;
	private static IncrementadorHilo miTiempo;
    private static int contadorGlobal = 0; // Contador compartido
    static class IncrementadorHilo implements Runnable {
        @Override
        public void run() {
                contadorGlobal+=10;
                
        }
    }

    public static void main(String[] args) {
        int numeroDeHilos = 50; // Valor por defecto

        // Comprobar si se ha proporcionado un argumento para el número de hilos
        if (args.length > 0) {
            try {
                numeroDeHilos = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("El valor será: " + numeroDeHilos);
            }
        }
                
        
        // Crear y lanzar los hilos
        for (int i = 0; i < numeroDeHilos; i++) {
        	Thread cuenta = new Thread(new IncrementadorHilo());
        	cuenta.start();
        }

 

        // Mostrar el resultado
        System.out.println("El resultado es: " + contadorGlobal);
    }
    
}
