package paneles;

import javax.swing.*;
import java.awt.*;

public class Climapaneles extends JPanel {

    private JLabel detalle;   // Texto de temperatura y descripción
    private JLabel icono;     // Icono del clima

    public Climapaneles() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Clima");
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        detalle = new JLabel("Cargando...");
        detalle.setForeground(Color.BLACK);
        detalle.setFont(new Font("SansSerif", Font.PLAIN, 24));
        detalle.setAlignmentX(Component.CENTER_ALIGNMENT);

        icono = new JLabel();
        icono.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(titulo);
        add(Box.createVerticalStrut(20));
        add(icono);       // Icono arriba del texto
        add(Box.createVerticalStrut(10));
        add(detalle);
        add(Box.createVerticalGlue());
    }

    // Método para actualizar clima con ícono
    public void actualizarClima(String temperaturaC, String temperaturaF, String descripcion) {
        detalle.setText(temperaturaC + "°C / " + temperaturaF + "°F - " + descripcion);

        // Elegir ícono según descripción
        String rutaIcono = "";
        descripcion = descripcion.toLowerCase();
        if (descripcion.contains("soleado")) {
            rutaIcono = "/assets/sol.png";
        } else if (descripcion.contains("nublado")) {
            rutaIcono = "/assets/nubes.png";
        } else if (descripcion.contains("lluvia") || descripcion.contains("lluvioso")) {
            rutaIcono = "/assets/lluvia.png";
        } else {
            rutaIcono = "/assets/desconocido.png"; // ícono genérico
        }

        // Cargar y escalar icono
        ImageIcon imgIcono = new ImageIcon(getClass().getResource(rutaIcono));
        Image imgEscalada = imgIcono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        icono.setIcon(new ImageIcon(imgEscalada));

        repaint();
        revalidate();
    }

    // Método de prueba
    public void simularClima() {
        actualizarClima("28", "82.4", "Parcialmente Nublado");
    }
}
