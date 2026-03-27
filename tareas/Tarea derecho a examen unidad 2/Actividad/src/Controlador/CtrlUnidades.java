package Controlador;

import Vista.VistaUnidadesMedida;
import javax.swing.table.DefaultTableModel;

public class CtrlUnidades {

    private VistaUnidadesMedida vista;

    public CtrlUnidades(VistaUnidadesMedida vista) {
        this.vista = vista;

        
        vista.tablaUnidades.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Unidad de Medida"}
        ));

    
        vista.btnAgregar.addActionListener(e -> agregarUnidad());
    }

    private void agregarUnidad() {
        String unidad = vista.txtNuevaUnidad.getText().trim();

        if (!unidad.isEmpty()) {
            DefaultTableModel m = (DefaultTableModel) vista.tablaUnidades.getModel();
            m.addRow(new Object[]{unidad});

            vista.txtNuevaUnidad.setText("");
        }
    }
}