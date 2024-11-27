package main;
import java.io.Serializable;

public class Mensaje implements Serializable{
	private static final long serialVersionUID = 1L;
	private int numero;
	private String mensaje;
	
	
	public int getNum() {
		return numero;
	}
	public void setNum(int num) {
		this.numero = num;
	}
	public String getHola() {
		return mensaje;
	}
	public void setHola(String hola) {
		this.mensaje = hola;
	}
	
	@Override
	public String toString() {
		return numero+" "+mensaje;
	}
	
}
