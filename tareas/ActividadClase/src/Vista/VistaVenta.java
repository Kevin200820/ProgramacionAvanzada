package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaVenta extends JInternalFrame {

    public JTextField    txtIdBusqueda, txtCantidad, txtTotal;
    public JButton       btnAgregar, btnFinalizar, btnQuitar, btnReporteVentas;
    public JTable        tablaCarrito;
    public DefaultTableModel modeloTabla;

    public VistaVenta() {
        super("Punto de Venta", true, true, true, true);
        setSize(860, 540);
        setLayout(new BorderLayout(5, 5));
        add(panelBusqueda(), BorderLayout.NORTH);
        add(panelCarrito(),  BorderLayout.CENTER);
        add(panelTotal(),    BorderLayout.SOUTH);
    }

    private JPanel panelBusqueda() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        p.setBorder(BorderFactory.createTitledBorder("Agregar Producto al Carrito"));
        p.add(new JLabel("ID Producto:"));
        txtIdBusqueda = new JTextField(12); p.add(txtIdBusqueda);
        p.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField(5); p.add(txtCantidad);
        btnAgregar = new JButton("➕ Agregar"); p.add(btnAgregar);
        return p;
    }

    private JPanel panelCarrito() {
        modeloTabla = new DefaultTableModel(
            new String[]{"ID","Nombre","Cantidad","Precio Unit.","Subtotal"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaCarrito = new JTable(modeloTabla);
        tablaCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder("Carrito de Compra"));
        p.add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);
        return p;
    }

    private JPanel panelTotal() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        p.setBorder(BorderFactory.createTitledBorder("Acciones"));
        txtTotal = new JTextField(12);
        txtTotal.setEditable(false);
        txtTotal.setFont(new Font("Arial", Font.BOLD, 15));
        txtTotal.setForeground(new Color(0, 100, 0));
        btnQuitar        = new JButton("➖ Quitar");
        btnFinalizar     = new JButton("Pagar");
        btnFinalizar.setBackground(new Color(0, 120, 0));
        btnFinalizar.setForeground(Color.WHITE);
        btnReporteVentas = new JButton("Reporte Ventas Excel");
        p.add(new JLabel("Total: $")); p.add(txtTotal);
        p.add(btnQuitar); p.add(btnFinalizar); p.add(btnReporteVentas);
        return p;
    }
}