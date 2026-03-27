package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;
import Vista.VistaProductos;
import javax.swing.table.DefaultTableModel;

public class CtrlProductos {
    private VistaProductos vista;
    private ProductoDAO modelo;

    public CtrlProductos(VistaProductos vista, ProductoDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        actualizarTabla();

        vista.btnGuardar.addActionListener(e -> guardarProducto());

      
        vista.tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tablaProductos.getSelectedRow();
            if (fila != -1) {
                String sku = vista.tablaProductos.getValueAt(fila, 0).toString();
                Producto p = modelo.buscarPorSku(sku);
                if (p != null) {
                    vista.mostrarImagen(p.foto);
                }
            }
        });
    }

    private void guardarProducto() {
        try {
            Producto p = new Producto();
            p.sku = vista.txtSku.getText();
            p.descripcion = vista.txtDesc.getText();
            p.precioCompra = Double.parseDouble(vista.txtPrecioCompra.getText());
            p.porcentajeGanancia = Double.parseDouble(vista.txtPorcentajeGanancia.getText());
            p.stock = Integer.parseInt(vista.txtStock.getText());
            p.unidad = vista.cbUnidad.getSelectedItem().toString();
            p.categoria = vista.cbCategoria.getSelectedItem().toString();
            p.foto = p.sku + ".jpg";

            modelo.agregar(p);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception e) {
            System.out.println("Error al guardar");
        }
    }

    public void actualizarTabla() {
        DefaultTableModel m = new DefaultTableModel(
            new String[]{"SKU", "Descripción", "Costo", "Ganancia %", "Venta", "Stock"}, 0
        );

        for (Producto p : modelo.listarTodo()) {
            m.addRow(new Object[]{
                p.sku, p.descripcion, p.precioCompra,
                p.porcentajeGanancia, p.getPrecioVenta(), p.stock
            });
        }

        vista.tablaProductos.setModel(m);
    }

    private void limpiarCampos() {
        vista.txtSku.setText("");
        vista.txtDesc.setText("");
        vista.txtPrecioCompra.setText("");
        vista.txtPorcentajeGanancia.setText("");
        vista.txtStock.setText("");
    }
}