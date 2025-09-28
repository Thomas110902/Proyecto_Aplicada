package paneles;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Horapanel extends JPanel {

    private JLabel fechaLabel;
    private JLabel horaLabel;

    public Horapanel() {
        // Color de fondo (igual que el menú lateral)
        Color colorMenu = new Color(30, 30, 60);

        setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        setOpaque(true);
        setBackground(colorMenu);

        // Borde inferior
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(50, 50, 50)));

        // Etiqueta de fecha
        fechaLabel = new JLabel("", SwingConstants.LEFT);
        fechaLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        fechaLabel.setForeground(Color.WHITE);
        add(fechaLabel);

        // Etiqueta de hora
        horaLabel = new JLabel("", SwingConstants.LEFT);
        horaLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        horaLabel.setForeground(Color.WHITE);
        add(horaLabel);

        // Timer para actualizar cada segundo
        Timer timer = new Timer(1000, e -> {
            String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String fechaActual = new SimpleDateFormat("dd 'de' MMM yyyy").format(new Date());

            horaLabel.setText(horaActual);
            fechaLabel.setText(fechaActual);
        });
        timer.start();
    }
}
