package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.VistaPrincipal;

public class CtrlPrincipal implements ActionListener {
    VistaPrincipal vista;

    public CtrlPrincipal(VistaPrincipal vista) {
        this.vista = vista;
       
        this.vista.btnVenta.addActionListener(this);
        this.vista.btnProductos.addActionListener(this);
        this.vista.btnInventario.addActionListener(this);
        this.vista.btnProveedores.addActionListener(this);
        this.vista.btnReportes.addActionListener(this);
        this.vista.btnUnidades.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnVenta) {
            vista.tarjetas.show(vista.panelContenedor, "VENTA");
        } else if (e.getSource() == vista.btnProductos) {
            vista.tarjetas.show(vista.panelContenedor, "PRODUCTOS");
        } else if (e.getSource() == vista.btnInventario) {
            vista.tarjetas.show(vista.panelContenedor, "INVENTARIO");
        } else if (e.getSource() == vista.btnProveedores) {
            vista.tarjetas.show(vista.panelContenedor, "PROVEEDORES");
        } else if (e.getSource() == vista.btnReportes) {
            vista.tarjetas.show(vista.panelContenedor, "REPORTES");
        } else if (e.getSource() == vista.btnUnidades) {
            vista.tarjetas.show(vista.panelContenedor, "UNIDADES");
        }
    }
}