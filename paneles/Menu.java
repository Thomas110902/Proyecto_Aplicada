package paneles;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private JPanel panelMenu;
    private String nombreUsuario;
    private JFrame ventanaPadre; // ventana a regresar

    public Menu(String nombreUsuario, JFrame ventanaPadre) {
        this.nombreUsuario = nombreUsuario;
        this.ventanaPadre = ventanaPadre;

        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(30, 30, 60));
        panelMenu.setPreferredSize(new Dimension(150, 0));
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        JLabel usuarioLabel = new JLabel("Usuario: " + nombreUsuario);
        usuarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        usuarioLabel.setForeground(Color.WHITE);
        usuarioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMenu.add(usuarioLabel);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] opciones = {"Clima", "Reloj", "Opciones", "Temporizador", "Tiempo", "Configuración"};
        for (String texto : opciones) {
            JButton boton = new JButton(texto);
            boton.setMaximumSize(new Dimension(125, 40));
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setBackground(Color.WHITE);
            boton.setForeground(new Color(30, 30, 60));
            boton.setFocusPainted(false);
            panelMenu.add(boton);
            panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        panelMenu.add(Box.createVerticalGlue());

        JButton regresarButton = new JButton("Regresar");
        regresarButton.setMaximumSize(new Dimension(100, 40));
        regresarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        regresarButton.setBackground(Color.ORANGE);
        regresarButton.setForeground(Color.WHITE);
        regresarButton.setFocusPainted(false);
        regresarButton.addActionListener(e -> {
            JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(panelMenu);
            frameActual.dispose(); // cierra dashboard
            if (ventanaPadre != null) {
                ventanaPadre.setVisible(true); // vuelve a mostrar ventana anterior
            }
        });
        panelMenu.add(regresarButton);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton salirButton = new JButton("Salir");
        salirButton.setMaximumSize(new Dimension(100, 40));
        salirButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        salirButton.setBackground(Color.RED);
        salirButton.setForeground(Color.WHITE);
        salirButton.setFocusPainted(false);
        salirButton.addActionListener(e -> System.exit(0));
        panelMenu.add(salirButton);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    public JPanel getJPanel() {
        return panelMenu;
    }
}
