package paneles;

import javax.swing.*;
import java.awt.*;

public class Centralpanel extends JPanel {
    private CardLayout cardLayout;

    public Centralpanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setBackground(Color.DARK_GRAY); // fondo por defecto
    }

    // IMPORTANTE: siempre llamar a super.paintComponent
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // opcional: pintar un fondo personalizado
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    // Método público para mostrar un panel por nombre
    public void showPanel(String nombre) {
        cardLayout.show(this, nombre);
    }

    // Método auxiliar para agregar paneles al CardLayout
    public void addPanel(JPanel panel, String nombre) {
        this.add(panel, nombre);
    }

    // Método auxiliar para crear labels rápidamente
    public JLabel crearLabel(String texto, int size, boolean bold) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}
