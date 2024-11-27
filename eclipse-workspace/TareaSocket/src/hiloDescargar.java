import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class hiloDescargar implements Runnable {
	private Socket s;
	private String nombreArchivo;

	public hiloDescargar(Socket s, String nombreArchivo) {
		this.s = s;
		this.nombreArchivo = nombreArchivo;
	}

	@Override
	public void run() {
		FileInputStream fis = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {

			dos = new DataOutputStream(s.getOutputStream());
			File f = new File(nombreArchivo);
			fis = new FileInputStream(f);
			
			System.out.println(f.getAbsolutePath());

			
			dos.writeUTF(f.getName());
			dos.flush();
			transferirArchivo.transfer(fis, s.getOutputStream());

			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
