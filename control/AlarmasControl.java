package control;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

public class AlarmasControl {

    private static final String RUTA_HISTORIAL = "database/historialAlarmas.txt";

    // Agregar alarma
    public void agregarAlarma(List<LocalTime> alarmas, List<String> descripciones,
                              List<Boolean> activadas, List<Boolean> encendidas,
                              LocalTime nuevaHora, String descripcion) {
        alarmas.add(nuevaHora);
        descripciones.add(descripcion);
        activadas.add(false);
        encendidas.add(false);
    }

    // Eliminar alarma
    public void eliminarAlarma(List<LocalTime> alarmas, List<String> descripciones,
                               List<Boolean> activadas, List<Boolean> encendidas,
                               int index) {
        alarmas.remove(index);
        descripciones.remove(index);
        activadas.remove(index);
        encendidas.remove(index);
    }

    // Alternar ON/OFF
    public void alternarEstadoAlarma(List<LocalTime> alarmas, List<String> descripciones,
                                     List<Boolean> activadas, List<Boolean> encendidas,
                                     int index) {
        if (index != -1) {
            boolean nuevoEstado = !encendidas.get(index);
            encendidas.set(index, nuevoEstado);

            String estadoStr = nuevoEstado ? "ON" : "OFF";
            JOptionPane.showMessageDialog(null, "La alarma ahora está: " + estadoStr);

            guardarHistorial(descripciones.get(index), "Estado cambiado a " + estadoStr);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una alarma para activar/desactivar");
        }
    }

    // Guardar historial
    public void guardarHistorial(String descripcion, String estado) {
        try {
            File carpeta = new File("database");
            if (!carpeta.exists()) carpeta.mkdir();

            try (FileWriter fw = new FileWriter(RUTA_HISTORIAL, true)) {
                String linea = "[" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "] "
                        + descripcion + " → " + estado;
                fw.write(linea + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mostrar historial
    public void mostrarHistorial() {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_HISTORIAL))) {
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Historial de Alarmas", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay historial guardado todavía");
        }
    }
}
