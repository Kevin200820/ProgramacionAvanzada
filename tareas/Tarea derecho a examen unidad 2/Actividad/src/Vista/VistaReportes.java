package Vista;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VistaReportes extends JPanel {
    public JTable tablaReporte = new JTable();
    public JLabel lblResumen = new JLabel("Resumen de Ventas y Ganancias");

    public VistaReportes() {
        setLayout(new BorderLayout());
        add(lblResumen, BorderLayout.NORTH);
        add(new JScrollPane(tablaReporte), BorderLayout.CENTER);
    }
}