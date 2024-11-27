import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class HiloBorrar implements Runnable {

	private String archivo;

	public HiloBorrar(String archivo) {
		this.archivo = archivo;
	}

	@Override
	public void run() {
		try {

//			ois = new ObjectInputStream(s.getInputStream());
//
//			Mensaje resp = (Mensaje) ois.readObject();
//			String nombreArchivo = resp.getArchivo();
//			System.out.println(nombreArchivo);

			File f = new File("servidor/" + archivo);

			if (f.exists()) {
				f.delete();
				System.out.println(f.getAbsolutePath());
			} else {
				System.out.println("no existe");
				System.out.println(f.getAbsolutePath());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
