package Main;

import Vista.*;
import Modelo.*;
import Controlador.*;

public class Main {
    public static void main(String[] args) {

        ProductoDAO pDao = new ProductoDAO();
        VentaDAO vDao = new VentaDAO();
        ProveedorDAO provDao = new ProveedorDAO();

    
        pDao.cargarDesdeArchivo("productos.txt");

        VistaPrincipal principal = new VistaPrincipal();
        VistaProductos vProd = new VistaProductos();
        VistaPuntoDeVenta vVenta = new VistaPuntoDeVenta();
        VistaInventarios vInv = new VistaInventarios();
        VistaProveedores vProv = new VistaProveedores();
        VistaReportes vRep = new VistaReportes();
        VistaUnidadesMedida vUni = new VistaUnidadesMedida();

        new CtrlPrincipal(principal);
        new CtrlProductos(vProd, pDao);
        new CtrlInventarios(vInv, pDao);

        CtrlReportes ctrlRep = new CtrlReportes(vRep, vDao);
        new CtrlPuntoVenta(vVenta, vDao, pDao, ctrlRep);

        new CtrlProveedores(vProv, provDao);
        new CtrlUnidades(vUni);

        principal.panelContenedor.add(vVenta, "VENTA");
        principal.panelContenedor.add(vProd, "PRODUCTOS");
        principal.panelContenedor.add(vInv, "INVENTARIO");
        principal.panelContenedor.add(vProv, "PROVEEDORES");
        principal.panelContenedor.add(vRep, "REPORTES");
        principal.panelContenedor.add(vUni, "UNIDADES");

        principal.setVisible(true);
    }
}