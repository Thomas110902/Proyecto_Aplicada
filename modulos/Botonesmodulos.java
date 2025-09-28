package modulos;

import javax.swing.*;
import java.awt.*;

public class Botonesmodulos {

    public static JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setMaximumSize(new Dimension(125, 40));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(30, 30, 60));
        boton.setFocusPainted(false);

        // Listener genérico (opcional)
        boton.addActionListener(e -> System.out.println("Botón presionado: " + texto));

        return boton;
    }
}