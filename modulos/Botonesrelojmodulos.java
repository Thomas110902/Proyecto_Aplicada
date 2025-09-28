package modulos;

import javax.swing.*;
import java.awt.*;

public class Botonesrelojmodulos {

    public interface Acciones {
        void agregar();
        void eliminar();
        void activarDesactivar();
        void verHistorial();
    }

    public static JPanel crearPanel(Acciones acciones) {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton agregarBtn = new JButton("➕ Agregar Alarma");
        JButton eliminarBtn = new JButton("❌ Eliminar Alarma");
        JButton activarBtn = new JButton("🔘 Activar/Desactivar");
        JButton historialBtn = new JButton("📜 Ver Historial");

        agregarBtn.addActionListener(e -> acciones.agregar());
        eliminarBtn.addActionListener(e -> acciones.eliminar());
        activarBtn.addActionListener(e -> acciones.activarDesactivar());
        historialBtn.addActionListener(e -> acciones.verHistorial());

        panelBotones.add(agregarBtn);
        panelBotones.add(eliminarBtn);
        panelBotones.add(activarBtn);
        panelBotones.add(historialBtn);

        return panelBotones;
    }
}
