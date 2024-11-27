import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloTransferencia implements Runnable {

	private Socket s;

	public HiloTransferencia(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		FileOutputStream fos = null;
		DataInputStream dis = null;

		try {

			dis = new DataInputStream(s.getInputStream());

			
			String nombreArchivo = dis.readUTF();

			File f = new File("servidor/" + nombreArchivo);
			if (f.exists()) {
				f.delete();
			}
			fos = new FileOutputStream(f);
			transferirArchivo.transfer(s.getInputStream(), fos);

			fos.close();
			fos = null;
			dis.close();
			dis = null;
			s.close();
			s = null;

		}

		catch (IOException e) {

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
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
