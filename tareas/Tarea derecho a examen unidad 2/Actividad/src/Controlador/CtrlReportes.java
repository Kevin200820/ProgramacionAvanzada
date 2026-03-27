package Controlador;

import Modelo.VentaDAO;
import Modelo.Venta;
import Vista.VistaReportes;
import javax.swing.table.DefaultTableModel;

public class CtrlReportes {
    VistaReportes vista;
    VentaDAO modelo;

    public CtrlReportes(VistaReportes v, VentaDAO m) {
        this.vista = v;
        this.modelo = m;

        actualizarTabla(); 
    }

    public void actualizarTabla() {
        DefaultTableModel m = new DefaultTableModel(
            new String[]{"ID", "Fecha", "Total"}, 0
        );

        
        for (Venta v : modelo.listarVentas()) {
           
            m.addRow(new Object[]{v.idVenta, v.fecha, v.total});
        }

        vista.tablaReporte.setModel(m);
    }
}