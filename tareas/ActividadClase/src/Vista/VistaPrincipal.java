package Vista;

import javax.swing.*;

public class VistaPrincipal extends JFrame {

    public JDesktopPane desktop;
    public JMenuItem menuInventario;
    public JMenuItem menuVenta;
    public JMenuItem menuSalir;

    public VistaPrincipal() {
        setTitle("Sistema de Gestión");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setBackground(new java.awt.Color(40, 40, 40));
        add(desktop);

        JMenuBar barra     = new JMenuBar();
        JMenu menuArchivo  = new JMenu("Archivo");

        menuInventario = new JMenuItem("Inventario");
        menuVenta      = new JMenuItem("Venta");
        menuSalir      = new JMenuItem("Salir");

        menuArchivo.add(menuInventario);
        menuArchivo.add(menuVenta);
        menuArchivo.addSeparator();
        menuArchivo.add(menuSalir);

        barra.add(menuArchivo);
        setJMenuBar(barra);
    }
}