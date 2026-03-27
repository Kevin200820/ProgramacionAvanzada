package Vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaUnidadesMedida extends JPanel {
    public JTextField txtNuevaUnidad = new JTextField(10);
    public JButton btnAgregar = new JButton("Agregar Unidad");
    public JTable tablaUnidades = new JTable();

    public VistaUnidadesMedida() {
        setLayout(new BorderLayout());
        JPanel norte = new JPanel();
        norte.add(new JLabel("Nombre de Unidad (Kg, Pz, L):"));
        norte.add(txtNuevaUnidad);
        norte.add(btnAgregar);
        
        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tablaUnidades), BorderLayout.CENTER);
    }
}