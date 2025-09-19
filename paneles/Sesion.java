package paneles;

import javax.swing.*;
import control.LoginSesion;
import views.Dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sesion {
    private JPanel panelLogin;
    private JTextField campoNombre;
    private JButton botonIniciar;

    public Sesion() {
        // Panel con fondo degradado azul
        panelLogin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(135, 206, 250),   // azul cielo arriba
                        0, getHeight(), new Color(25, 25, 112) // azul noche abajo
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelLogin.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));

        // Título principal
        JLabel titulo = new JLabel("Bienvenido a Reloj Digital");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 44));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(titulo);
        panelLogin.add(Box.createRigidArea(new Dimension(0, 30)));

        // Subtítulo
        JLabel titulo2 = new JLabel("Ingresa tu nombre");
        titulo2.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        titulo2.setForeground(new Color(200, 200, 200));
        titulo2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(titulo2);
        panelLogin.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo de texto para nombre
        campoNombre = new JTextField(20);
        campoNombre.setMaximumSize(new Dimension(300, 40));
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 18));
        campoNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLogin.add(campoNombre);
        panelLogin.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón iniciar
        botonIniciar = new JButton("Iniciar");
        botonIniciar.setFont(new Font("Arial", Font.BOLD, 20));
        botonIniciar.setBackground(new Color(70, 130, 180));
        botonIniciar.setForeground(Color.WHITE);
        botonIniciar.setFocusPainted(false);

        // Panel para alinear el botón a la derecha
        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonPanel.setOpaque(false);
        botonPanel.setMaximumSize(new Dimension(800, 60));
        botonPanel.add(botonIniciar);
        panelLogin.add(botonPanel);

        // ActionListener del botón
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entradaUsuario = campoNombre.getText();

                // Validación del usuario
                if (new LoginSesion().validacionUsuario(entradaUsuario)) {
                    // Cierra la ventana de login
                    JFrame framePadre = (JFrame) SwingUtilities.getWindowAncestor(panelLogin);
                    framePadre.dispose();

                    // Abre el Dashboard
                    new Dashboard(entradaUsuario, framePadre); 
                } else {
                    JOptionPane.showMessageDialog(panelLogin, "Usuario no válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getJPanel() {
        return panelLogin;
    }

    public String getNombreUsuario() {
        return campoNombre.getText();
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }
}
