package paneles;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Temporizadorpaneles {

    private JPanel panel;
    private DefaultListModel<String> modeloLista;
    private ArrayList<Ciclo> listaCiclos;
    private final String FILE = "database/ciclos.txt";

    public Temporizadorpaneles() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        listaCiclos = new ArrayList<>();
        modeloLista = new DefaultListModel<>();
        JList<String> listaVisual = new JList<>(modeloLista);
        panel.add(new JScrollPane(listaVisual), BorderLayout.CENTER);

        JButton agregar = new JButton("Agregar Ciclo");
        agregar.addActionListener(e -> agregarCiclo());
        panel.add(agregar, BorderLayout.SOUTH);

        cargarCiclos(); // Cargar datos guardados al iniciar
    }

    // Cargar ciclos desde el archivo de texto
    private void cargarCiclos() {
        modeloLista.clear();
        listaCiclos.clear();

        File dir = new File("database");
        if (!dir.exists()) dir.mkdir();

        File file = new File(FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(";");
                if (parts.length == 5) {
                    Ciclo c = new Ciclo(parts[0], parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4]));
                    listaCiclos.add(c);
                    modeloLista.addElement(c.toString());
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Agregar un nuevo ciclo desde inputs del usuario
    private void agregarCiclo() {
        String nombre = JOptionPane.showInputDialog("Nombre del ciclo:");
        if (nombre == null || nombre.isEmpty()) return;

        String hora = JOptionPane.showInputDialog("Hora de inicio (HH:mm):");
        if (hora == null || hora.isEmpty()) return;

        int trabajo, descanso, repeticiones;
        try {
            trabajo = Integer.parseInt(JOptionPane.showInputDialog("Minutos de trabajo:"));
            descanso = Integer.parseInt(JOptionPane.showInputDialog("Minutos de descanso:"));
            repeticiones = Integer.parseInt(JOptionPane.showInputDialog("Número de repeticiones:"));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel, "Por favor ingresa un número válido.");
            return;
        }

        Ciclo c = new Ciclo(nombre, hora, trabajo, descanso, repeticiones);
        listaCiclos.add(c);
        modeloLista.addElement(c.toString());

        guardarCiclos(); // Guardar automáticamente en archivo
    }

    // Guardar todos los ciclos en archivo de texto
    private void guardarCiclos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Ciclo c : listaCiclos) {
                bw.write(c.nombre + ";" + c.hora + ";" + c.trabajo + ";" + c.descanso + ";" + c.repeticiones);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    // Clase interna que representa un ciclo de trabajo/descanso
    private static class Ciclo {
        String nombre, hora;
        int trabajo, descanso, repeticiones;

        Ciclo(String nombre, String hora, int trabajo, int descanso, int repeticiones) {
            this.nombre = nombre;
            this.hora = hora;
            this.trabajo = trabajo;
            this.descanso = descanso;
            this.repeticiones = repeticiones;
        }

        public String toString() {
            return nombre + " | Inicio: " + hora +
                    " | Trabajo: " + trabajo + " min" +
                    " | Descanso: " + descanso + " min" +
                    " | Repeticiones: " + repeticiones;
        }
    }
}
