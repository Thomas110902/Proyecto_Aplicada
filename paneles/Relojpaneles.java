package paneles;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.*;
import java.io.File;

import control.AlarmasControl;
import modulos.Botonesrelojmodulos;

public class Relojpaneles extends JPanel {

    private DefaultListModel<String> listaAlarmasModel;
    private JList<String> listaAlarmas;

    private List<LocalTime> alarmas;
    private List<String> descripciones;
    private List<Boolean> activadas;
    private List<Boolean> encendidas;

    private Clip clip;
    private AlarmasControl control;

    public Relojpaneles() {
        setLayout(new BorderLayout());

        control = new AlarmasControl();

        alarmas = new ArrayList<>();
        descripciones = new ArrayList<>();
        activadas = new ArrayList<>();
        encendidas = new ArrayList<>();

        listaAlarmasModel = new DefaultListModel<>();
        listaAlarmas = new JList<>(listaAlarmasModel);
        listaAlarmas.setFont(new Font("SansSerif", Font.PLAIN, 18));

        listaAlarmas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (activadas.size() > index && activadas.get(index)) {
                    c.setForeground(Color.RED);
                    c.setFont(c.getFont().deriveFont(Font.BOLD, 30f));
                } else {
                    c.setForeground(Color.BLACK);
                    c.setFont(c.getFont().deriveFont(Font.PLAIN, 18f));
                }
                return c;
            }
        });

        add(new JScrollPane(listaAlarmas), BorderLayout.CENTER);

        JPanel panelBotones = Botonesrelojmodulos.crearPanel(new Botonesrelojmodulos.Acciones() {
            @Override
            public void agregar() { agregarAlarmaUI(); }
            @Override
            public void eliminar() { eliminarAlarmaUI(); }
            @Override
            public void activarDesactivar() { alternarEstadoUI(); }
            @Override
            public void verHistorial() { control.mostrarHistorial(); }
        });
        add(panelBotones, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, e -> revisarAlarmas());
        timer.start();
    }

    private void agregarAlarmaUI() {
        try {
            String horaStr = JOptionPane.showInputDialog(this, "Ingrese hora (HH:MM:SS):");
            if (horaStr == null || horaStr.isEmpty()) return;
            String ampm = JOptionPane.showInputDialog(this, "AM o PM?");
            if (ampm == null) return;
            String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
            if (descripcion == null) descripcion = "";

            String[] partes = horaStr.split(":");
            int h = Integer.parseInt(partes[0]);
            int m = Integer.parseInt(partes[1]);
            int s = Integer.parseInt(partes[2]);
            if (ampm.equalsIgnoreCase("PM") && h != 12) h += 12;
            if (ampm.equalsIgnoreCase("AM") && h == 12) h = 0;

            LocalTime nuevaAlarma = LocalTime.of(h, m, s);
            control.agregarAlarma(alarmas, descripciones, activadas, encendidas, nuevaAlarma, descripcion);

            listaAlarmasModel.addElement(nuevaAlarma.format(DateTimeFormatter.ofPattern("hh:mm:ss a")) + " - " + descripcion + " [OFF]");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato inválido");
        }
    }

    private void eliminarAlarmaUI() {
        int index = listaAlarmas.getSelectedIndex();
        if (index != -1) {
            control.eliminarAlarma(alarmas, descripciones, activadas, encendidas, index);
            listaAlarmasModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una alarma");
        }
    }

    private void alternarEstadoUI() {
        int index = listaAlarmas.getSelectedIndex();
        if (index != -1) {
            control.alternarEstadoAlarma(alarmas, descripciones, activadas, encendidas, index);

            String estadoStr = encendidas.get(index) ? "ON" : "OFF";
            listaAlarmasModel.set(index,
                    alarmas.get(index).format(DateTimeFormatter.ofPattern("hh:mm:ss a")) + " - " +
                            descripciones.get(index) + " [" + estadoStr + "]");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una alarma");
        }
    }

    private void revisarAlarmas() {
        LocalTime ahora = LocalTime.now();
        for (int i = 0; i < alarmas.size(); i++) {
            if (encendidas.get(i) && !activadas.get(i)
                    && ahora.getHour() == alarmas.get(i).getHour()
                    && ahora.getMinute() == alarmas.get(i).getMinute()
                    && ahora.getSecond() == alarmas.get(i).getSecond()) {

                activarAlarmaUI(i);
            }
        }
    }

    private void activarAlarmaUI(int index) {
        activadas.set(index, true);
        listaAlarmas.repaint();

        reproducirSonido();

        LocalTime hora = alarmas.get(index);
        String descripcion = descripciones.get(index);

        JDialog popup = new JDialog((Frame) null, "ALERTA", true);
        popup.setUndecorated(true);
        popup.setSize(600, 400);
        popup.setLocationRelativeTo(null);
        popup.getContentPane().setBackground(Color.RED);
        popup.setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("<html><center>⏰ ¡Alarma Sonando!<br>" +
                hora.format(DateTimeFormatter.ofPattern("hh:mm:ss a")) + "<br>" + descripcion + "</center></html>");
        mensaje.setFont(new Font("SansSerif", Font.BOLD, 40));
        mensaje.setForeground(Color.WHITE);
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        popup.add(mensaje, BorderLayout.CENTER);

        JButton detener = new JButton("Detener Alarma");
        detener.setFont(new Font("SansSerif", Font.BOLD, 30));
        detener.addActionListener(e -> {
            detenerSonido();
            popup.dispose();
        });
        popup.add(detener, BorderLayout.SOUTH);

        control.guardarHistorial(descripcion, "Activada");

        popup.setVisible(true);
    }

    private void reproducirSonido() {
        try {
            File sonido = new File("sounds/TF039.WAV");
            AudioInputStream audio = AudioSystem.getAudioInputStream(sonido);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo reproducir el sonido");
        }
    }

    private void detenerSonido() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
        for (int i = 0; i < activadas.size(); i++) {
            activadas.set(i, false);
        }
        listaAlarmas.repaint();
    }
}
