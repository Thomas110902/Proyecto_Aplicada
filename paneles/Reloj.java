package paneles;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reloj extends JPanel {
    public Reloj() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 60));

        JLabel horaLabel = new JLabel("", SwingConstants.CENTER);
        horaLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        horaLabel.setForeground(Color.WHITE);

        JLabel timerLabel = new JLabel("00:30", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 100));
        timerLabel.setForeground(new Color(0, 255, 255));

        add(horaLabel, BorderLayout.NORTH);
        add(timerLabel, BorderLayout.CENTER);

        final int[] tiempoRestante = {30};
        Timer timer = new Timer(1000, e -> {
            tiempoRestante[0]--;
            int minutos = tiempoRestante[0] / 60;
            int segundos = tiempoRestante[0] % 60;
            timerLabel.setText(String.format("%02d:%02d", minutos, segundos));

            if (tiempoRestante[0] <= 0) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(this, "¡Tiempo terminado!");
            }

            String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());
            horaLabel.setText("Hora actual: " + horaActual);
        });
        timer.start();
    }
}
