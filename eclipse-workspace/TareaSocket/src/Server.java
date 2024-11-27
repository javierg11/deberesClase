import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

public class Server {

	public static void main(String[] args) {
		Socket s = null;
		ServerSocket ss = null;
		String respuesta = "";
		DataOutputStream dos = null;
		DataInputStream dis = null;

		try {
			ss = new ServerSocket(2311);

			s = ss.accept();

			dis = new DataInputStream(s.getInputStream());

			String men = dis.readUTF();
			men = men.toLowerCase().trim();

			String nombreArchivo = dis.readUTF();

			File file = new File(nombreArchivo);
			String nombre = "servidor/" + file.getName();
			String cliente = "cliente/" + file.getName();
			File archivoServidor = new File(nombre);
			File archivoCliente = new File(cliente);
			System.out.println(archivoServidor.getAbsolutePath());

			switch (men) {
			case "up":
				if (!archivoServidor.exists()) {
					respuesta = "up";
					Thread thTransferir = new Thread(new HiloTransferencia(s));
					thTransferir.start();
				} else
					respuesta = "AlreadyExists";

				break;
			case "down":
				if (!archivoCliente.exists()) {
					respuesta = "down";
					Thread thDescargar = new Thread(new hiloDescargar(s, nombreArchivo));
					thDescargar.start();
				} else
					respuesta = "NotFound";
				break;
			case "delete":
				if (archivoServidor.exists()) {
					respuesta = "delete";
					Thread thBorrar = new Thread(new HiloBorrar(file.getName()));
					thBorrar.start();
				} else
					respuesta = "NotFound";
				break;
			default:
				respuesta = "Por favor pon una orden válida";
				break;
			}

			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(respuesta);
			dos.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			
		}

	}
}