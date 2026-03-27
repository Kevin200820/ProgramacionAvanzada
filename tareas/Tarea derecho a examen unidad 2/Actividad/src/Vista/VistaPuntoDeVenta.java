package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaPuntoDeVenta extends JPanel {
    public JTextField txtSkuBusqueda = new JTextField(15);
    public JTable tablaVenta = new JTable();
    public JLabel lblTotal = new JLabel("TOTAL: $0.00");
    public JButton btnCobrar = new JButton("Finalizar Venta");

    public VistaPuntoDeVenta() {
        setLayout(new BorderLayout());
        
      
        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"SKU", "Descripción", "Cantidad", "Precio", "Subtotal"}
        ));
        
        JPanel norte = new JPanel();
        norte.add(new JLabel("Escanear SKU:"));
        norte.add(txtSkuBusqueda);
        
        lblTotal.setFont(new Font("Arial", Font.BOLD, 30));
        lblTotal.setForeground(new Color(0, 100, 0));
        
        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tablaVenta), BorderLayout.CENTER);
        
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.add(lblTotal);
        sur.add(btnCobrar);
        add(sur, BorderLayout.SOUTH);
    }
}