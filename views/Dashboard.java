package views;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import paneles.Menu;

public class Dashboard {

    public Dashboard(String nombreUsuario, JFrame ventanaPadre) {
        JFrame frame = new JFrame("Reloj Digital");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ------------------- Menu lateral -------------------
        Menu menu = new Menu(nombreUsuario, ventanaPadre); // pasamos ventanaPadre
        frame.add(menu.getJPanel(), BorderLayout.EAST); // menú al lado derecho

        // ------------------- Panel central -------------------
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(10, 25, 77),
                        0, getHeight(), new Color(25, 50, 150)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelCentral.setLayout(new BorderLayout());

        // Hora actual arriba
        JLabel horaLabel = new JLabel("", SwingConstants.CENTER);
        horaLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        horaLabel.setForeground(Color.WHITE);
        panelCentral.add(horaLabel, BorderLayout.NORTH);

        // Temporizador en el centro
        JLabel timerLabel = new JLabel("00:30", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 100));
        timerLabel.setForeground(new Color(0, 255, 255));
        panelCentral.add(timerLabel, BorderLayout.CENTER);

        frame.add(panelCentral, BorderLayout.CENTER);
        frame.setVisible(true);

        // ------------------- Temporizador de 30 segundos -------------------
        final int[] tiempoRestante = {30};
        Timer timer = new Timer(1000, e -> {
            tiempoRestante[0]--;

            int horas = tiempoRestante[0] / 3600;
            int minutos = (tiempoRestante[0] % 3600) / 60;
            int segundos = tiempoRestante[0] % 60;
            String tiempoFormateado = String.format("%02d:%02d:%02d", horas, minutos, segundos);
            timerLabel.setText(tiempoFormateado);

            // Beep últimos 10 segundos
            if (tiempoRestante[0] <= 10 && tiempoRestante[0] > 0) {
                Toolkit.getDefaultToolkit().beep();
            }

            if (tiempoRestante[0] <= 0) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(panelCentral, "¡Tiempo terminado!");
            }

            // Actualizar hora actual
            String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());
            horaLabel.setText("Hora actual: " + horaActual);
        });
        timer.start();
    }
}
