package algo;

import java.io.File;

public class main {

	public static String[] fiche(File f) {
		String[] fs = f.list();
		return fs;
	}

	public static File[] ficheX(File f) {
		File[] fl = f.listFiles();
		return fl;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ruta = "/home";
		File f = new File(ruta);
		System.out.println("Nombre: " + f.getName());
		System.out.println("Directorio: " + f.getParent());
		System.out.println("Ruta relativa: " + f.getPath());
		System.out.println("Ruta Absoluta: " + f.getAbsolutePath());

		for (int i = 0; i < ficheX(f).length; i++) {
			System.out.println(ficheX(f)[i]);
			System.out.println(ficheX(f)[i].isFile());
		}
	}
}
