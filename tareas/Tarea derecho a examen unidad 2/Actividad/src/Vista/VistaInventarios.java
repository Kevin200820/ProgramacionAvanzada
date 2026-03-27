package Vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class VistaInventarios extends JPanel {
    public JTable tablaInventario = new JTable();
    public JButton btnActualizar = new JButton("Refrescar Stock");

    public VistaInventarios() {
        setLayout(new BorderLayout());
        add(new JLabel("Existencias en Almacén", SwingConstants.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(tablaInventario), BorderLayout.CENTER);
        add(btnActualizar, BorderLayout.SOUTH);
    }
}