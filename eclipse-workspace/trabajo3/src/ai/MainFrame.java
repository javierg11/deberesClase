package ai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JProgressBar barra1, barra2, barra3;
    private JLabel label1, label2, label3, tiempoLabel;
    private JButton startButton, pauseButton;
    private ProgressThread progressThread1, progressThread2, progressThread3;
    private MonitorThread monitorThread;
    private TimerThread timerThread;

    public MainFrame() {
        setTitle("La Carrera de los Hilos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        barra1 = new JProgressBar(0, 100);
        barra2 = new JProgressBar(0, 100);
        barra3 = new JProgressBar(0, 100);

        label1 = new JLabel("Jugador 1: 0%");
        label2 = new JLabel("Jugador 2: 0%");
        label3 = new JLabel("Jugador 3: 0%");
        tiempoLabel = new JLabel("Tiempo transcurrido: 0s");

        startButton = new JButton("Iniciar");
        pauseButton = new JButton("Pausar");

        add(barra1);
        add(label1);
        add(barra2);
        add(label2);
        add(barra3);
        add(label3);
        add(tiempoLabel);
        add(startButton);
        add(pauseButton);

        startButton.addActionListener(new StartAction());
        pauseButton.addActionListener(new PauseAction());

        setVisible(true);
    }

    private class StartAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetProgress();
            progressThread1 = new ProgressThread(barra1, label1);
            progressThread2 = new ProgressThread(barra2, label2);
            progressThread3 = new ProgressThread(barra3, label3);

            monitorThread = new MonitorThread(progressThread1, progressThread2, progressThread3);
            timerThread = new TimerThread(tiempoLabel);

            progressThread1.start();
            progressThread2.start();
            progressThread3.start();
            monitorThread.start();
            timerThread.start();
        }
    }

    private class PauseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (progressThread1 != null) progressThread1.pausar();
            if (progressThread2 != null) progressThread2.pausar();
            if (progressThread3 != null) progressThread3.pausar();
            monitorThread.pausar();
            timerThread.pausar();
        }
    }

    private void resetProgress() {
        barra1.setValue(0);
        barra2.setValue(0);
        barra3.setValue(0);
        label1.setText("Jugador 1: 0%");
        label2.setText("Jugador 2: 0%");
        label3.setText("Jugador 3: 0%");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
