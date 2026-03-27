package Controlador;

import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Vista.VistaProveedores;
import javax.swing.table.DefaultTableModel;

public class CtrlProveedores {
    private VistaProveedores vista;
    private ProveedorDAO modelo;

    public CtrlProveedores(VistaProveedores vista, ProveedorDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        actualizarTabla(); 

        this.vista.btnGuardar.addActionListener(e -> {
            String nombre = vista.txtNombre.getText();
            if(!nombre.isEmpty()) {
                modelo.agregar(new Proveedor(nombre));
                actualizarTabla();
                vista.txtNombre.setText("");
            }
        });
    }

    public void actualizarTabla() {
        DefaultTableModel m = new DefaultTableModel(
            new String[]{"Nombre del Proveedor"}, 0
        );

        for(Proveedor p : modelo.listarTodo()) {
            m.addRow(new Object[]{p.nombre});
        }

        vista.tablaProveedores.setModel(m);
    }
}