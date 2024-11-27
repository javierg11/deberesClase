package examenNestor1;

import java.util.ArrayList;
import java.util.Vector;


public class main {
	static ArrayList<Integer> n1 = new ArrayList<Integer>();
	static Thread cuenta1;


	static int numero;
	private static int contadorGlobal = 0; //Contador
	private static int numeroGet = 0; //Para sumar <198
	private static int contSecundario = 0; //Para sumar 198 y 199
	
	 
	public static void main(String[] args) {
		for (int i =0;i<200;i++) {
			 numero = (int)(Math.random() * 10)+1;
			 n1.add(numero);
		}
		int comprobacion = 0;
		for (int i = 0; i<n1.size(); i++) {
			comprobacion += n1.get(i);
		}
		System.out.println("Comprobación: " + comprobacion);
		
		while(numeroGet<=191) {
			for (int i = 0; i <= 8; i++) {
				cuenta1 = new Thread(new IncrementadorHilo());
	        	cuenta1.start();
	        	
	        	
	        }

			
		}
		
		System.out.println("El resultado es : "+contadorGlobal);
	}
	
	static class IncrementadorHilo implements Runnable {
        @Override
        public void run() {
        	if (numeroGet<198) {
                sumarValor();
                sumarValor();
                sumarValor();
        	}
        	else if (contSecundario>197 && contSecundario<200) {
        		sumarValor();
                sumarValor();
        	}
        	contSecundario+=3;
        }

		private void sumarValor() {
			contadorGlobal+=n1.get(numeroGet);
			numeroGet++;
		}
    }
	

}
