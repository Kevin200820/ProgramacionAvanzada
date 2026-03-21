package Main;

import Vista.*;
import Modelo.*;
import Controlador.*;

public class Main {
    public static void main(String[] args) {

        try {
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        VistaPrincipal vp = new VistaPrincipal();

        ModeloTienda mt = new ModeloTienda();  
        ModeloVenta  mv = new ModeloVenta();

        vp.menuInventario.addActionListener(e -> {
            VistaTienda vt = new VistaTienda();
            vp.desktop.add(vt);
            new ControladorInventario(mt, vt);
            vt.setVisible(true);
        });

        vp.menuVenta.addActionListener(e -> {
            VistaVenta vv = new VistaVenta();
            vp.desktop.add(vv);
            new ControladorVenta(mt, mv, vv);
            vv.setVisible(true);
        });

        vp.menuSalir.addActionListener(e -> System.exit(0));

        vp.setVisible(true);
    }
}