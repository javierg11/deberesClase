import javax.swing.*;

import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class main {
    JFrame ventana;
    JLabel l1, player1, player2, player3;
    static JLabel la1, la2, la3;
    static JLabel tiempo;
    JPanel panelbarras, p1, p2, p3, titulo, pstart, pstop, pboton;
    static JProgressBar barra1;
    static JProgressBar barra2;
    static JProgressBar barra3;
    JButton start, stop;
    private static tiempos miTiempo;
    boolean hola=true;

    static Thread barra;

    public main() {
        ventana = new JFrame("The great thread race");

        l1 = new JLabel("<HTML><FONT SIZE=5>The great thread race!</FONT></HTML>\"");
        player1 = new JLabel("Player 1");
        player2 = new JLabel("Player 2");
        player3 = new JLabel("Player 3");
        tiempo = new JLabel("TIME: ");
        la1 = new JLabel("0%");
        la2 = new JLabel("0%");
        la3 = new JLabel("0%");
        panelbarras = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        titulo = new JPanel();

        pstop = new JPanel();
        pstart = new JPanel();
        pboton = new JPanel();

        barra1 = new JProgressBar();
        barra2 = new JProgressBar();
        barra3 = new JProgressBar();
        start = new JButton("<HTML><FONT COLOR=green SIZE=5>START!</FONT></HTML>");
        stop = new JButton("<HTML><FONT COLOR=red SIZE=5>PAUSE</FONT></HTML>");

        start.addActionListener(new StartAction());
        stop.addActionListener(new StopAction()); 

        titulo.add(l1);

        p1.add(player1);
        p1.add(barra1);
        p1.add(la1);

        p2.add(player2);
        p2.add(barra2);
        p2.add(la2);

        p3.add(player3);
        p3.add(barra3);
        p3.add(la3);

        panelbarras.add(p1);
        panelbarras.add(p2);
        panelbarras.add(p3);

        pstart.add(start);
        pstop.add(stop);

        pboton.setLayout(new GridLayout(3, 1));
        pboton.add(pstart);
        pboton.add(pstop);
        pboton.add(tiempo);

        ventana.setLayout(new GridLayout(4, 0));
        ventana.add(titulo);
        ventana.add(panelbarras);
        ventana.add(pboton);

        ventana.setSize(300, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        miTiempo = new tiempos(barra1, la1, barra2, la2, barra3, la3, tiempo, ventana);
    }

    public static void main(String[] args) {
        main app = new main();
    }

    class StartAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            if (barra == null || !barra.isAlive()) {
                barra = new Thread(miTiempo);
                barra.start();
                start.setEnabled(false); 
            }
            la1.setText("");
            la2.setText("");
            la3.setText("");
        }
    }

    class StopAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            	if (!hola) {
                miTiempo.resume(); 
               
                hola=true;
                System.out.print("\nContinua\n");
            	}
            
            	else {
                miTiempo.pausar(); 
                hola=false;
                System.out.print("\nPausa\n");
            	}
        }
    }
}

