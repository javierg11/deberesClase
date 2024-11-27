package main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TransferirClienteRed {
	public static void main(String[] args) {
		Socket s = null;
		FileInputStream fis = null;
		
		DataOutputStream dos = null;
		try {
			for(int i =0;i<10;i++) {
				s = new Socket("127.0.0.1",3010);
				File f = new File("/home/alumno/Imágenes/hola.gif");
				fis = new FileInputStream(f);
				dos = new DataOutputStream(s.getOutputStream());
				dos.writeLong(f.length());
				dos.flush();
				
				dos.writeUTF(i+f.getName());
				dos.flush();
				
			
				Transferir.transfer(f.length(), fis, s.getOutputStream());
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (s!=null && s.isClosed()) {
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
