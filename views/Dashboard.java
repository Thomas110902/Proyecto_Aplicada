package views;

import javax.swing.*;
import java.awt.*;
import paneles.Menu;
import paneles.Centralpanel;
import paneles.Climapaneles;
import paneles.Horapanel;
import paneles.Relojpaneles; 

public class Dashboard {        

    public Dashboard(String nombreUsuario, JFrame ventanaPadre) {
        JFrame frame = new JFrame("Reloj Digital");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ------------------- Menu lateral -------------------
        Menu menu = new Menu(nombreUsuario, ventanaPadre);
        frame.add(menu.getJPanel(), BorderLayout.EAST);

        // ------------------- Panel superior (header con hora y fecha) -------------------
        Horapanel panelSuperior = new Horapanel();
        frame.add(panelSuperior, BorderLayout.NORTH);

        // ------------------- Panel central con CardLayout -------------------
        Centralpanel panelCentral = new Centralpanel();
        frame.add(panelCentral, BorderLayout.CENTER);

        // ------------------- Panel de Clima -------------------
        Climapaneles climaPanel = new Climapaneles();
        panelCentral.addPanel(climaPanel, "Clima");

        // ------------------- Panel de Reloj avanzado -------------------
        Relojpaneles relojPanel = new Relojpaneles();
        panelCentral.addPanel(relojPanel, "Reloj");

        // ------------------- Panel de Calendario -------------------
        JPanel calendarioPanel = new JPanel();
        panelCentral.addPanel(calendarioPanel, "Calendario");

        menu.getBotonReloj().addActionListener(e -> panelCentral.showPanel("Reloj"));
        menu.getBotonOpciones().addActionListener(e -> panelCentral.showPanel("Calendario"));
        menu.getBotonTemporizador().addActionListener(e -> panelCentral.showPanel("Temporizador"));
        menu.getBotonTiempo().addActionListener(e -> panelCentral.showPanel("Tiempo"));
        menu.getBotonConfiguracion().addActionListener(e -> panelCentral.showPanel("Configuracion"));

        // ------------------- Mostrar ventana -------------------
        frame.setVisible(true);

        // ------------------- Simulación de actualización automática del clima -------------------
        new javax.swing.Timer(10000, e -> climaPanel.actualizarClima("30", "86", "Soleado")).start();
    }

    // ------------------- Main para probar -------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard("Thomas", new JFrame()));
    }
}
