import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		DataOutputStream dos = null;
		DataInputStream dis = null;
		Socket s = null;
		Scanner lectura = new Scanner(System.in);

		System.out.print("Pon lo que quiere hacer (up, down, delete): ");
		String orden = lectura.next();

		System.out.print("Pon el nombre del archivo: ");
		String nombre = lectura.next();

		try {
			s = new Socket("127.0.0.1", 2311);
			dos = new DataOutputStream(s.getOutputStream());

			nombre = "/home/alumno/Imágenes/hola.gif";
			//nombre = "hola.gif";

			dos.writeUTF(orden);
			dos.flush();
			dos.writeUTF(nombre);
			dos.flush();

			dis = new DataInputStream(s.getInputStream());
			String mesajeServer = dis.readUTF();

			if (mesajeServer.equals("up")) {

				File f = new File(nombre);
				fis = new FileInputStream(f);
				
				System.out.println(f.getAbsolutePath());

				
				dos.writeUTF(f.getName());
				dos.flush();
				transferirArchivo.transfer(fis, s.getOutputStream());

			} else if (mesajeServer.equals("down")) {

				dis = new DataInputStream(s.getInputStream());

				
				String nombreArchivo = dis.readUTF();
				System.out.println(nombreArchivo);
				File f = new File("cliente/" + nombreArchivo);

				if (f.exists()) {
					f.delete();
				}

				fos = new FileOutputStream(f);
				transferirArchivo.transfer(s.getInputStream(), fos);

			}

			System.out.println(mesajeServer);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
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
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
