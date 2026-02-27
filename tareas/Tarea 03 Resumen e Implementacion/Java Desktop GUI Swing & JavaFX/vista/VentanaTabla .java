package VISTA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaTabla extends JFrame {

    private JTable tabla;

    public VentanaTabla() {
        setTitle("Listado");
        setSize(420,260);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JScrollPane panel = new JScrollPane();
        panel.setBounds(40,40,330,150);
        add(panel);

        tabla = new JTable();
        tabla.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Código","Nombre","Estado"}
        ));
        panel.setViewportView(tabla);
    }

    public JTable getTabla(){
        return tabla;
    }
}