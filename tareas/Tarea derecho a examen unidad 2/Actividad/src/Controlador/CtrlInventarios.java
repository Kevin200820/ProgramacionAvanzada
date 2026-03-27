package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;
import Vista.VistaInventarios;
import javax.swing.table.DefaultTableModel;

public class CtrlInventarios {
    private VistaInventarios vista;
    private ProductoDAO modelo;

    public CtrlInventarios(VistaInventarios vista, ProductoDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

      
        this.vista.btnActualizar.addActionListener(e -> {
            actualizarTabla();
        });
    }

    public void actualizarTabla() {
        String[] col = {"Producto", "Existencia", "Estado"};
        DefaultTableModel m = new DefaultTableModel(col, 0);
        for (Producto p : modelo.listarTodo()) {
            String estado = (p.stock <= 3) ? "POR AGOTARSE" : "DISPONIBLE";
            m.addRow(new Object[]{p.descripcion, p.stock, estado});
        }
        vista.tablaInventario.setModel(m);
    }
}