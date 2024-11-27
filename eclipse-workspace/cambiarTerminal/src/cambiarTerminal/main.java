package cambiarTerminal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class main {
    static String rutaImagenes = "/home/alumno/Descargas/imagenes";
    static String rutaBanner = "/home/alumno/Descargas/banner";
    static List<Integer> numerosUsados;
    static int nuevoNumero = 0;
    static int numeroSeleccionado = 0;
    static Thread th;
    private volatile boolean ejecutando = false;

    public void ejecutar() {
        ejecutando = true;
        while (ejecutando) {
            th = new Thread(new secundario());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numerosUsados = obtenerNumerosUsados(rutaImagenes, ".jpg");

            if (numerosUsados.isEmpty()) {
                System.out.println("No hay archivos suficientes para renombrar.");
                continue;
            }

            nuevoNumero = generarNuevoNumero(numerosUsados);
            numeroSeleccionado = seleccionarNumeroAleatorio(numerosUsados);

            th.start();
            renombrarArchivo(rutaBanner, "1.txt", nuevoNumero + ".txt");
            renombrarArchivo(rutaBanner, numeroSeleccionado + ".txt", "1.txt");
        }
    }

    public void detener() {
        ejecutando = false;
    }

    static class secundario implements Runnable {
        @Override
        public void run() {
            renombrarArchivo(rutaImagenes, "1.jpg", nuevoNumero + ".jpg");
            renombrarArchivo(rutaImagenes, numeroSeleccionado + ".jpg", "1.jpg");
        }
    }

    private static List<Integer> obtenerNumerosUsados(String ruta, String extension) {
        File carpeta = new File(ruta);
        File[] archivos = carpeta.listFiles();
        List<Integer> numerosUsados = new ArrayList<>();
        
        if (archivos != null) {
            for (File archivo : archivos) {
                String nombre = archivo.getName();
                if (nombre.matches("\\d+" + extension)) {
                    int numero = Integer.parseInt(nombre.split("\\.")[0]);
                    numerosUsados.add(numero);
                }
            }
        }
        return numerosUsados;
    }

    private static int generarNuevoNumero(List<Integer> numerosUsados) {
        int nuevoNumero;
        do {
            nuevoNumero = (int)(Math.random() * 100) + 1;
        } while (numerosUsados.contains(nuevoNumero));
        return nuevoNumero;
    }

    private static int seleccionarNumeroAleatorio(List<Integer> numeros) {
        if (numeros.size() == 1 && numeros.get(0) == 1) {
            return 1; // Si solo hay un archivo y es el 1, devolvemos 1
        }
        int indice;
        int seleccionado;
        do {
            indice = (int)(Math.random() * numeros.size());
            seleccionado = numeros.get(indice);
        } while (seleccionado == 1);
        return seleccionado;
    }

    private static void renombrarArchivo(String ruta, String nombreViejo, String nombreNuevo) {
        File archivoViejo = new File(ruta, nombreViejo);
        File archivoNuevo = new File(ruta, nombreNuevo);
        
        if (archivoViejo.exists()) {
            if (archivoViejo.renameTo(archivoNuevo)) {
                System.out.println("Archivo renombrado exitosamente: " + nombreViejo + " -> " + nombreNuevo);
            } else {
                System.out.println("No se pudo renombrar el archivo: " + nombreViejo);
            }
        } else {
            System.out.println("El archivo no existe: " + nombreViejo);
        }
    }
}




/*
 package cambiarTerminal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class main {
	static String rutaImagenes = "/home/alumno/Descargas/imagenes";
	static String rutaBanner = "/home/alumno/Descargas/banner";
    static List<Integer> numerosUsados;
    static int nuevoNumero=0;
    static int numeroSeleccionado=0;
    static Thread th;
    public static void main(String[] args) {
        
        
        
        
        while (true) {
        	
        	
        	th = new Thread(new secundario());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numerosUsados = obtenerNumerosUsados(rutaImagenes, ".jpg");

            if (numerosUsados.isEmpty()) {
                System.out.println("No hay archivos suficientes para renombrar.");
                continue;
            }

            nuevoNumero = generarNuevoNumero(numerosUsados);
            numeroSeleccionado = seleccionarNumeroAleatorio(numerosUsados);

            // Renombrar archivos de banner y imagen
            th.start();
            renombrarArchivo(rutaBanner, "1.txt", nuevoNumero + ".txt");
            
            //Si quieres una cambia numeroSeleccionado
            renombrarArchivo(rutaBanner, numeroSeleccionado + ".txt", "1.txt");
            
            
            
        }
    }
    
    static class secundario implements Runnable {
    	@Override
    	public void run() {
    		renombrarArchivo(rutaImagenes, "1.jpg", nuevoNumero + ".jpg");
            renombrarArchivo(rutaImagenes, numeroSeleccionado + ".jpg", "1.jpg");
    		
    	}
    }
    
    
    private static List<Integer> obtenerNumerosUsados(String ruta, String extension) {
        File carpeta = new File(ruta);
        File[] archivos = carpeta.listFiles();
        List<Integer> numerosUsados = new ArrayList<>();
        
        if (archivos != null) {
            for (File archivo : archivos) {
                String nombre = archivo.getName();
                if (nombre.matches("\\d+" + extension)) {
                    int numero = Integer.parseInt(nombre.split("\\.")[0]);
                    numerosUsados.add(numero);
                }
            }
        }
        return numerosUsados;
    }

    private static int generarNuevoNumero(List<Integer> numerosUsados) {
        int nuevoNumero;
        do {
            nuevoNumero = (int)(Math.random() * 100) + 1;
        } while (numerosUsados.contains(nuevoNumero));
        return nuevoNumero;
    }

    private static int seleccionarNumeroAleatorio(List<Integer> numeros) {
        if (numeros.size() == 1 && numeros.get(0) == 1) {
            return 1; // Si solo hay un archivo y es el 1, devolvemos 1
        }
        int indice;
        int seleccionado;
        do {
            indice = (int)(Math.random() * numeros.size());
            seleccionado = numeros.get(indice);
        } while (seleccionado == 1);
        return seleccionado;
    }

    private static void renombrarArchivo(String ruta, String nombreViejo, String nombreNuevo) {
        File archivoViejo = new File(ruta, nombreViejo);
        File archivoNuevo = new File(ruta, nombreNuevo);
        
        if (archivoViejo.exists()) {
            if (archivoViejo.renameTo(archivoNuevo)) {
                System.out.println("Archivo renombrado exitosamente: " + nombreViejo + " -> " + nombreNuevo);
            } else {
                System.out.println("No se pudo renombrar el archivo: " + nombreViejo);
            }
        } else {
            System.out.println("El archivo no existe: " + nombreViejo);
        }
    }
}
 */
