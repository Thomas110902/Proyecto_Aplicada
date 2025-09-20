package paneles;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private JPanel panelMenu;
    private String nombreUsuario;
    private JFrame ventanaPadre; // ventana a regresar

    // Botones que necesitamos exponer
    private JButton botonClima;
    private JButton botonReloj;
    private JButton botonOpciones;
    private JButton botonTemporizador;
    private JButton botonTiempo;
    private JButton botonConfiguracion;

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

        // Crear botones y guardarlos en atributos
        botonClima = crearBoton("Clima");
        botonReloj = crearBoton("Reloj");
        botonOpciones = crearBoton("Calendario");
        botonTemporizador = crearBoton("Temporizador");
        botonTiempo = crearBoton("Tiempo");
        botonConfiguracion = crearBoton("Configuración");

        panelMenu.add(botonClima);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(botonReloj);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(botonOpciones);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(botonTemporizador);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(botonTiempo);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(botonConfiguracion);
        panelMenu.add(Box.createVerticalGlue());

        // Botón regresar
        JButton regresarButton = crearBoton("Regresar");
        regresarButton.setBackground(Color.ORANGE);
        regresarButton.setForeground(Color.WHITE);
        regresarButton.addActionListener(e -> {
            JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(panelMenu);
            frameActual.dispose(); 
            if (ventanaPadre != null) {
                ventanaPadre.setVisible(true);
            }
        });
        panelMenu.add(regresarButton);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botón salir
        JButton salirButton = crearBoton("Salir");
        salirButton.setBackground(Color.RED);
        salirButton.setForeground(Color.WHITE);
        salirButton.addActionListener(e -> System.exit(0));
        panelMenu.add(salirButton);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setMaximumSize(new Dimension(125, 40));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(30, 30, 60));
        boton.setFocusPainted(false);
        return boton;
    }

    public JPanel getJPanel() {
        return panelMenu;
    }

    // Métodos públicos para acceder a los botones desde Dashboard
    public JButton getBotonClima() { return botonClima; }
    public JButton getBotonReloj() { return botonReloj; }
    public JButton getBotonOpciones() { return botonOpciones; }
    public JButton getBotonTemporizador() { return botonTemporizador; }
    public JButton getBotonTiempo() { return botonTiempo; }
    public JButton getBotonConfiguracion() { return botonConfiguracion; }
}
