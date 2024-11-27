import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class transferirArchivo {

	public static void transfer(long tamaArchivo, InputStream fis, OutputStream os)
			throws FileNotFoundException, IOException {

		byte[] array = new byte[2048];
		int cantBytes = 0;

		// int total=0;

		// System.out.println(tamaArchivo);

		cantBytes = fis.read(array, 0, array.length);
		while (cantBytes != -1) {

			os.write(array, 0, cantBytes);
			if (cantBytes <= tamaArchivo) {
				cantBytes = fis.read(array, 0, array.length);

			}

			// total+=cantBytes;
			// System.out.println("% de transferencia: "+(total*100/tamaArchivo)+"%");
			// System.out.println("Lo que lleva del archivo es: "+total);
		}

		fis.close();
		os.flush();
		os.close();
		// System.out.println(total);

	}

}
