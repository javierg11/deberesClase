public class main {
    static Thread tiempo;
    private static hiloTiempo miTiempo;

    public static void main(String[] args) {
        long tiempoInicio = System.currentTimeMillis();
        long b;
        miTiempo = new hiloTiempo(tiempoInicio);
        tiempo = new Thread(miTiempo);
        tiempo.start();
        for (int i=0;i<3;) {
			b = System.currentTimeMillis();
			if ((b-tiempoInicio)/1000>=3) {
				i++;
			}	
		}
        System.out.print("Empieza pausa\n");
		miTiempo.pausar();
//        b = System.currentTimeMillis();
//        while(b-tiempoInicio<1000*3) {
//        	b = System.currentTimeMillis();
//        }
       
//        boolean algo = false;
//        while (!algo) {
//			b = System.currentTimeMillis();
//			
//			if ((b-tiempoInicio)/1000==3) {
//				System.out.print("Empieza pausa\n");
//				miTiempo.pausar();
//				algo = true;
//			}	
//		}
        
        
        
        
        tiempoInicio= System.currentTimeMillis();
        for (int i=0;i<=4;) {
			b = System.currentTimeMillis();	
			if (b-tiempoInicio>=1000*i) {
				System.out.print("Desde principal 2: Quedan " + (5 - i) + " para acabar" + "\n");
				i++;
			}	
		}
        System.out.print("Termina pausa\n");
        miTiempo.resume();
        
    }
}