package views;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import paneles.Menu;

public class Dashboard {

    public Dashboard(String nombreUsuario, JFrame ventanaPadre) {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ------------------- Menu lateral -------------------
        Menu menu = new Menu(nombreUsuario, ventanaPadre);
        frame.add(menu.getJPanel(), BorderLayout.EAST);

        // ------------------- Panel superior (header) -------------------
        Color colorMenu = new Color(30, 30, 60); // mismo color que el menú lateral
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelSuperior.setOpaque(true);
        panelSuperior.setBackground(colorMenu);

        // Borde inferior de 2px, gris oscuro
        panelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(50, 50, 50)));

        JLabel fechaLabel = new JLabel("", SwingConstants.LEFT);
        fechaLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        fechaLabel.setForeground(Color.WHITE);
        panelSuperior.add(fechaLabel);

        JLabel horaLabel = new JLabel("", SwingConstants.LEFT);
        horaLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        horaLabel.setForeground(Color.WHITE);
        panelSuperior.add(horaLabel);

        frame.add(panelSuperior, BorderLayout.NORTH); // siempre visible

        // ------------------- Timer para actualizar fecha y hora -------------------
        Timer timer = new Timer(1000, e -> {
            String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String fechaActual = new SimpleDateFormat("dd 'de' MMM yyyy").format(new Date());

            horaLabel.setText(horaActual);
            fechaLabel.setText(fechaActual);
        });
        timer.start();

        // ------------------- Panel central con CardLayout y fondo degradado -------------------
        JPanel panelCentral = new JPanel(new CardLayout()) {
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

        // ------------------- Paneles para CardLayout -------------------
        JPanel panelClima = crearPanel("Clima", new String[]{"22°C / 71.6°F", "Soleado"});
        JPanel panelReloj = crearPanel("Reloj y Temporizadores", new String[]{"Cronómetro y alarma aquí"});
        JPanel panelOpciones = crearPanel("Opciones", null);
        JPanel panelTemporizador = crearPanel("Temporizador", null);
        JPanel panelTiempo = crearPanel("Tiempo", null);
        JPanel panelConfiguracion = crearPanel("Configuración", null);

        // ------------------- Agregar paneles al CardLayout -------------------
        panelCentral.add(panelClima, "Clima");
        panelCentral.add(panelReloj, "Reloj");
        panelCentral.add(panelOpciones, "Opciones");
        panelCentral.add(panelTemporizador, "Temporizador");
        panelCentral.add(panelTiempo, "Tiempo");
        panelCentral.add(panelConfiguracion, "Configuracion");

        // ------------------- Acciones de los botones -------------------
        menu.getBotonClima().addActionListener(e -> showPanel(panelCentral, "Clima"));
        menu.getBotonReloj().addActionListener(e -> showPanel(panelCentral, "Reloj"));
        menu.getBotonOpciones().addActionListener(e -> showPanel(panelCentral, "Opciones"));
        menu.getBotonTemporizador().addActionListener(e -> showPanel(panelCentral, "Temporizador"));
        menu.getBotonTiempo().addActionListener(e -> showPanel(panelCentral, "Tiempo"));
        menu.getBotonConfiguracion().addActionListener(e -> showPanel(panelCentral, "Configuracion"));

        // ------------------- Agregar panel central al frame -------------------
        frame.add(panelCentral, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // ------------------- Métodos auxiliares -------------------
    private void showPanel(JPanel panelCentral, String nombre) {
        CardLayout cl = (CardLayout) panelCentral.getLayout();
        cl.show(panelCentral, nombre);
    }

    private JPanel crearPanel(String titulo, String[] detalles) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(crearLabel(titulo, 32, true));
        if (detalles != null) {
            for (String detalle : detalles) {
                panel.add(crearLabel(detalle, 24, false));
            }
        }

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JLabel crearLabel(String texto, int size, boolean bold) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}
