import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class porcentaje implements Runnable{
	JLabel la1, la2, la3;
	private int valor, valor2, valor3;
	private String gana;
	JFrame frame;
	public porcentaje(JLabel la1, int valor, JLabel la2, int valor2, JLabel la3, int valor3, String gana, JFrame ventana) {
		this.la1=la1;
		this.valor=valor;

		this.la2=la2;
		this.valor2=valor2;
		
		this.la3=la3;
		this.valor3=valor3;
		this.gana=gana;
		this.frame=ventana;
	}
	@Override
	public void run() {
		frame=new JFrame ("The great thread race");
		if (!Thread.currentThread().isInterrupted()) {
			try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(frame, "Ha ganado el "+gana, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                System.out.print("Ha ganado el "+gana);
            }
			la1.setText(valor+"%");
			la2.setText(valor2+"%");
			la3.setText(valor3+"%");
		}
		
	}

}
