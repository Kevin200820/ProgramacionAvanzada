package Vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaProveedores extends JPanel {
    public JTextField txtNombre = new JTextField(20);
    public JButton btnGuardar = new JButton("Registrar Proveedor");
    public JTable tablaProveedores = new JTable();

    public VistaProveedores() {
        setLayout(new BorderLayout());
        JPanel norte = new JPanel();
        norte.add(new JLabel("Proveedor:")); norte.add(txtNombre);
        norte.add(btnGuardar);
        
        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tablaProveedores), BorderLayout.CENTER);
    }
}