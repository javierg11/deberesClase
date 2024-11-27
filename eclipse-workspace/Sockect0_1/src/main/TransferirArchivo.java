package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TransferirArchivo {

	public static void main(String[] args) {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		String fileRoute = (args.length>0) ? args[0] : "/home/alumno/Imágenes/8065b70059fa60a0ddc2a7850126b72c_w200.gif";
		File file = new File(fileRoute);
		if (file.exists()) {
			
			try {
				fis = new FileInputStream(file);
				File fileOutput = new File ("ArchivoRecibido.gif");
				System.out.println("Archivo guardado en: "+fileOutput.getAbsolutePath());
				if (fileOutput.exists())
					fileOutput.delete();
				fos = new FileOutputStream(fileOutput);
				Socket s = new Socket("127.0.0.1",3010);
				
				
				
				
				long tamaArchivo=file.length();
				Transferir.transfer(tamaArchivo, fis, s.getOutputStream());
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (fis !=null) {
						try {
							fis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
				if (fos !=null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			}
		}		
		else
			System.err.println("no esxiste: "+fileRoute);
	}

}
