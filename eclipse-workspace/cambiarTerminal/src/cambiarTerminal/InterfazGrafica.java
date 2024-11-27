package cambiarTerminal;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;

public class InterfazGrafica extends JFrame {
    private JButton btnCerrar, btnEjecutar;
    private JLabel imagenLabel;
    private Timer timer;
    private main mainApp;

    public InterfazGrafica() {
        setTitle("Visor de Imágenes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para la imagen
        imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imagenLabel, BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        btnCerrar = new JButton("Cerrar");
        btnEjecutar = new JButton("Ejecutar/Detener");
        panelBotones.add(btnCerrar);
        panelBotones.add(btnEjecutar);
        add(panelBotones, BorderLayout.SOUTH);

        // Configurar acciones de los botones
        btnCerrar.addActionListener(e -> System.exit(0));
        btnEjecutar.addActionListener(e -> toggleEjecucion());

        // Timer para actualizar la imagen
        timer = new Timer(3000, e -> actualizarImagen());
        
        mainApp = new main();
    }

    private void toggleEjecucion() {
        if (timer.isRunning()) {
            timer.stop();
            btnEjecutar.setText("Ejecutar");
        } else {
            timer.start();
            btnEjecutar.setText("Detener");
            new Thread(() -> mainApp.ejecutar()).start();
        }
    }

    private void actualizarImagen() {
        SwingUtilities.invokeLater(() -> {
            try {
                File file = new File(main.rutaImagenes, "1.jpg");
                if (file.exists()) {
                    Image img = ImageIO.read(file);
                    Image scaledImg = img.getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_SMOOTH);
                    imagenLabel.setIcon(new ImageIcon(scaledImg));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica gui = new InterfazGrafica();
            gui.setVisible(true);
        });
    }
}
