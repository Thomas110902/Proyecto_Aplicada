    import java.awt.BorderLayout;
    import java.awt.Component;
    import java.awt.Dimension;

    import javax.swing.Box;
    import javax.swing.JFrame;
    import javax.swing.JLabel;

    import paneles.Sesion;

    public class Main {
        public static void main(String[] args) {
            JFrame ventana = new JFrame();
            ventana.setTitle("Reloj Digital");
            ventana.setSize(1200,800);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ventana.setLayout(new BorderLayout());
            ventana.setLocationRelativeTo(null);

            Sesion panellogin = new Sesion();
            ventana.add(panellogin.getJPanel(), BorderLayout.CENTER);
        

            ventana.setVisible(true);
        }


    }
