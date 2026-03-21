package Controlador;

import Modelo.*;
import Persistencia.ArchivoJSON;
import Persistencia.GeneradorExcel;
import Vista.VistaVenta;

import java.awt.event.*;
import javax.swing.JOptionPane;

public class ControladorVenta implements ActionListener {

    private ModeloTienda   inventario;
    private ModeloVenta    modeloVenta;
    private VistaVenta     vista;
    private GeneradorExcel excel      = new GeneradorExcel();
    private ArchivoJSON    archivoJSON = new ArchivoJSON();

    public ControladorVenta(ModeloTienda inv, ModeloVenta mv, VistaVenta vv) {
        this.inventario  = inv;
        this.modeloVenta = mv;
        this.vista       = vv;
        vista.btnAgregar.addActionListener(this);
        vista.btnQuitar.addActionListener(this);
        vista.btnFinalizar.addActionListener(this);
        vista.btnReporteVentas.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            String idBusq = vista.txtIdBusqueda.getText().trim();
            Producto p = inventario.buscar(idBusq);
            if (p != null) {
                try {
                    int cant = Integer.parseInt(vista.txtCantidad.getText().trim());
                    if (cant <= 0) { JOptionPane.showMessageDialog(vista,"Cantidad debe ser mayor a 0"); return; }
                    modeloVenta.agregarAlCarrito(
                        new Ticket(p.getId(), p.getNombre(), p.getPrecio(), p.getEstado(), cant));
                    actualizar();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, " Cantidad inválida");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Producto no encontrado: " + idBusq);
            }
        }

        if (e.getSource() == vista.btnQuitar) {
            int fila = vista.tablaCarrito.getSelectedRow();
            if (fila >= 0) {
                modeloVenta.getCarrito().remove(fila);
                actualizar();
            } else {
                JOptionPane.showMessageDialog(vista, "Selecciona un producto del carrito");
            }
        }

        if (e.getSource() == vista.btnFinalizar) {
            if (modeloVenta.getCarrito().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El carrito está vacío"); return;
            }
            String folio = modeloVenta.registrarVentaFinal();
            modeloVenta.limpiarCarrito();
            actualizar();
            JOptionPane.showMessageDialog(vista,
                "✅ Venta registrada exitosamente\n" +
                "Folio: " + folio + "\n" +
                "Ticket guardado en: data/tickets/" + folio + ".json",
                "Venta Guardada", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == vista.btnReporteVentas) {
            String f = excel.generarReporteVentas(archivoJSON.leerHistorial());
            if (f != null) {
                JOptionPane.showMessageDialog(vista,
                    "Reporte de ventas generado:\n" + f,
                    "Reporte Ventas", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void actualizar() {
        vista.modeloTabla.setRowCount(0);
        for (Ticket t : modeloVenta.getCarrito()) {
            vista.modeloTabla.addRow(new Object[]{
                t.getId(), t.getNombre(), t.getCantidad(),
                String.format("$%.2f", t.getPrecio()),
                String.format("$%.2f", t.getSubtotal())
            });
        }
        vista.txtTotal.setText(String.format("%.2f", modeloVenta.calcularTotalCarrito()));
    }
}