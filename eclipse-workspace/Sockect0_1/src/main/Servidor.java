package main;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		
		try {

			ss = new ServerSocket(3010);
			while (true) {
				s = ss.accept();
				ObjectInputStream dis = new ObjectInputStream(s.getInputStream());
				Mensaje res = (Mensaje) dis.readObject();
				
				//String respuesta = dis.readUTF();
				System.out.println(res);
				
				dis.close();
				s.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (s!=null) {
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
