package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaRubrica extends JPanel {

    public JTable tabla;
    private DefaultTableModel modelo;
    public JLabel lblPromedio;

    public VistaRubrica() {

        setLayout(new BorderLayout());

        String[] columnas = {"Alumno", "C1", "C2", "C3", "C4", "C5", "C6"};
        modelo = new DefaultTableModel(columnas, 0);

        tabla = new JTable(modelo);

        lblPromedio = new JLabel("Promedio: 0");

        JButton btnCalcular = new JButton("Calcular Promedio");
        btnCalcular.addActionListener(e -> calcularPromedio());

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel abajo = new JPanel();
        abajo.add(btnCalcular);
        abajo.add(lblPromedio);

        add(abajo, BorderLayout.SOUTH);
    }

    
    public void cargarAlumnos(java.util.ArrayList<String> alumnos) {

        modelo.setRowCount(0); 

        for (String alumno : alumnos) {
            modelo.addRow(new Object[]{alumno, 0, 0, 0, 0, 0, 0});
        }
    }

    
    private void calcularPromedio() {

        float suma = 0;
        int count = 0;

        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 1; j < tabla.getColumnCount(); j++) {

                Object val = tabla.getValueAt(i, j);

                try {
                    suma += Float.parseFloat(val.toString());
                    count++;
                } catch (Exception e) {
                    
                }
            }
        }

        float promedio = (count > 0) ? suma / count : 0;
        lblPromedio.setText("Promedio: " + promedio);
    }
}