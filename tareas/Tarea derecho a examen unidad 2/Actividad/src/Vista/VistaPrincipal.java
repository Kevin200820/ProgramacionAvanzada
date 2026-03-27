package Vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {
    public JPanel panelContenedor;
    public CardLayout tarjetas;
    public JButton btnVenta, btnProductos, btnInventario, btnProveedores, btnReportes, btnUnidades;

    public VistaPrincipal() {
        setTitle("Sistema de Abarrotes");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        JPanel menu = new JPanel(new GridLayout(6, 1, 10, 10));
        btnVenta = new JButton("Punto de Venta");
        btnProductos = new JButton("Catálogo Productos");
        btnInventario = new JButton("Inventarios");
        btnProveedores = new JButton("Proveedores");
        btnReportes = new JButton("Reportes");
        btnUnidades = new JButton("Config. Unidades");

        menu.add(btnVenta); menu.add(btnProductos); menu.add(btnInventario);
        menu.add(btnProveedores); menu.add(btnReportes); menu.add(btnUnidades);
        add(menu, BorderLayout.WEST);

   
        tarjetas = new CardLayout();
        panelContenedor = new JPanel(tarjetas);
        add(panelContenedor, BorderLayout.CENTER);
    }
}