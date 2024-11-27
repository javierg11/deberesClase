package main;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) {
		Socket s = null;
		ObjectOutputStream dos = null;
		try {
			s = new Socket("172.23.107.103", 3010);
			dos = new ObjectOutputStream(s.getOutputStream());
			Mensaje m = new Mensaje();
			m.setNum(11);
			m.setHola("Hello");
			
			dos.writeObject(m);
			dos.flush();
			//byte[] bytes = new byte[];
			//for(int i=0;i<70;i++)
			//dos.writeUTF("Javier: hello");
			//dos.flush();
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (s!=null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dos!=null) {
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
