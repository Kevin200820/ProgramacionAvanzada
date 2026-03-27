package Controlador;

import Modelo.*;
import Vista.VistaPuntoDeVenta;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class CtrlPuntoVenta {
    private VistaPuntoDeVenta vista;
    private VentaDAO modeloVenta;
    private ProductoDAO modeloProd;
    private CtrlReportes ctrlReportes;
    private double totalAcumulado = 0;

    public CtrlPuntoVenta(VistaPuntoDeVenta v, VentaDAO mv, ProductoDAO mp, CtrlReportes cr) {
        this.vista = v;
        this.modeloVenta = mv;
        this.modeloProd = mp;
        this.ctrlReportes = cr; 

        this.vista.txtSkuBusqueda.addActionListener(e -> {
            String sku = vista.txtSkuBusqueda.getText();
            Producto p = modeloProd.buscarPorSku(sku);
            
            if (p != null) {
                String cantStr = JOptionPane.showInputDialog(vista, 
                    "Producto: " + p.descripcion + "\nStock disponible: " + p.stock + "\n¿Cuántos vender?");
                
                if (cantStr != null && !cantStr.isEmpty()) {
                    try {
                        int cantidad = Integer.parseInt(cantStr);
                        if (cantidad <= p.stock) {
                            double subtotal = p.getPrecioVenta() * cantidad;
                            DefaultTableModel m = (DefaultTableModel) vista.tablaVenta.getModel();

                            m.addRow(new Object[]{
                                p.sku, p.descripcion, cantidad, p.getPrecioVenta(), subtotal
                            });
                            
                            totalAcumulado += subtotal;
                            vista.lblTotal.setText("TOTAL: $" + String.format("%.2f", totalAcumulado));
                        } else {
                            JOptionPane.showMessageDialog(vista, "No hay suficiente stock");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(vista, "Número inválido");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Producto no encontrado");
            }
            vista.txtSkuBusqueda.setText("");
        });

      
        this.vista.btnCobrar.addActionListener(e -> {
            if (totalAcumulado > 0) {
                DefaultTableModel m = (DefaultTableModel) vista.tablaVenta.getModel();
                
               
                for (int i = 0; i < m.getRowCount(); i++) {
                    String skuVendida = m.getValueAt(i, 0).toString();
                    int cantVendida = Integer.parseInt(m.getValueAt(i, 2).toString());
                    
                    Producto p = modeloProd.buscarPorSku(skuVendida);
                    if(p != null) p.stock -= cantVendida;
                }

               
                modeloVenta.registrarVenta(
                    new Venta((int)(Math.random()*1000), "26/03/2026", totalAcumulado),
                    new ArrayList<>()
                );

              
                ctrlReportes.actualizarTabla();

         
                totalAcumulado = 0;
                vista.lblTotal.setText("TOTAL: $0.00");
                m.setRowCount(0);

                JOptionPane.showMessageDialog(vista, "Venta realizada!");
            }
        });
    }
}