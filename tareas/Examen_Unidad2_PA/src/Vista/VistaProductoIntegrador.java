package Vista;
import javax.swing.*;
import java.awt.*;

public class VistaProductoIntegrador extends JPanel {

    public JTextField txtFecha;
    public JTextArea txtObservaciones;

    public VistaProductoIntegrador() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(2,2));

        panel.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        panel.add(txtFecha);

        panel.add(new JLabel("Observaciones:"));
        txtObservaciones = new JTextArea(3,20);
        panel.add(new JScrollPane(txtObservaciones));

        add(panel, BorderLayout.CENTER);
    }
}