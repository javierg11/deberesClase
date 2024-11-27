package main;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TranferirServidorRed {

	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;

		try {
			ss = new ServerSocket(3010);
			while (true) {
				s = ss.accept();
				Thread th = new Thread(new HiloTransferencia(s));
				th.start();

			}
		} catch (IOException e) {
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
