package Controlador;

import Modelo.*;
import Persistencia.GeneradorExcel;
import Vista.VistaTienda;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControladorInventario implements ActionListener {

    private ModeloTienda   modelo;
    private VistaTienda    vista;
    private GeneradorExcel excel;

    public ControladorInventario(ModeloTienda m, VistaTienda v) {
        this.modelo = m;
        this.vista  = v;
        this.excel  = new GeneradorExcel();

      
        vista.btnCrear.addActionListener(this);
        vista.btnConsultar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);

       
        vista.btnReporteGeneral.addActionListener(this);
        vista.btnReporteCategoria.addActionListener(this);

        
        vista.tabla.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) cargarDetalle();
                }
            });

        llenarTabla();
    }

    
    public void llenarTabla() {
        vista.modeloTabla.setRowCount(0);
        for (Producto p : modelo.listaProductos) {
            vista.modeloTabla.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getCategoria(),
                String.format("$%.2f", p.getPrecio()), p.getEstado()
            });
        }
    }

   
    private void cargarDetalle() {
        int fila = vista.tabla.getSelectedRow();
        if (fila < 0) return;
        String id = (String) vista.modeloTabla.getValueAt(fila, 0);
        Producto p = modelo.buscar(id);
        if (p != null) {
            vista.mostrarImagen(p.getUrlImagen());
            vista.mostrarInfoExtra(p.getInfoExtra());
        }
    }


    private Producto crearProducto(String id, String nombre, double precio,
                                   String estado, String url, String cat) {
        return switch (cat) {
            case "Abarrotes"               -> new ProductoAbarrotes(id, nombre, precio, estado, url, "Anaquel", 1.0);
            case "Bebidas"                 -> new ProductoBebidas(id, nombre, precio, estado, url, "Refresco", 500, false, 0);
            case "Lácteos y Huevo"         -> new ProductoLacteos(id, nombre, precio, estado, url, true, 4, 0);
            case "Frutas y Verduras"       -> new ProductoFrutasVerduras(id, nombre, precio, estado, url, true, true, 7, "N/D");
            case "Carnes y Pescados"       -> new ProductoCarnes(id, nombre, precio, estado, url, "Pollo", false, "General", 4);
            case "Salchichonería"          -> new ProductoSalchichoneria(id, nombre, precio, estado, url, "Jamón", false, 7, "N/D");
            case "Panadería y Tortillería" -> new ProductoPanaderia(id, nombre, precio, estado, url, "Pan de caja", true, 1, true);
            case "Limpieza del Hogar"      -> new ProductoLimpieza(id, nombre, precio, estado, url, "Detergente", false, true, 1.0);
            case "Cuidado Personal"        -> new ProductoCuidadoPersonal(id, nombre, precio, estado, url, "Shampoo", "Unisex", 200, false);
            case "Snacks y Dulcería"       -> new ProductoSnacks(id, nombre, precio, estado, url, "Snack", 100, false, 200);
            case "Mascotas"                -> new ProductoMascotas(id, nombre, precio, estado, url, "Perro", "Croquetas", 1.0, "Adulto");
            default                        -> new ProductoAbarrotes(id, nombre, precio, estado, url, "Anaquel", 1.0);
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnReporteGeneral) {
            String f = excel.generarListadoGeneral(modelo.listaProductos);
            JOptionPane.showMessageDialog(vista,
                "✅ Reporte generado:\n" + f, "Reporte General", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (e.getSource() == vista.btnReporteCategoria) {
            String f = excel.generarListadoPorCategoria(modelo.listaProductos);
            JOptionPane.showMessageDialog(vista,
                "Reporte por categoria generado:\n" + f, "Reporte Categorias", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            String id  = vista.txtId.getText().trim();
            String nom = vista.txtNombre.getText().trim();
            String url = vista.txtUrlImagen.getText().trim();
            String est = vista.rbDisponible.isSelected() ? "Disponible" : "Agotado";
            String cat = (String) vista.cbCategoria.getSelectedItem();

            if (e.getSource() == vista.btnCrear) {
                if (id.isEmpty() || nom.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "ID y Nombre son obligatorios"); return;
                }
                if (modelo.existe(id)) {
                    JOptionPane.showMessageDialog(vista, " ID duplicado: " + id); return;
                }
                double pre = Double.parseDouble(vista.txtPrecio.getText().trim());
                modelo.insertar(crearProducto(id, nom, pre, est, url, cat));
            }

            if (e.getSource() == vista.btnConsultar) {
                Producto p = modelo.buscar(id);
                if (p != null) {
                    vista.txtNombre.setText(p.getNombre());
                    vista.txtPrecio.setText(String.valueOf(p.getPrecio()));
                    vista.txtUrlImagen.setText(p.getUrlImagen() != null ? p.getUrlImagen() : "");
                    vista.cbCategoria.setSelectedItem(p.getCategoria());
                    if ("Disponible".equals(p.getEstado())) vista.rbDisponible.setSelected(true);
                    else vista.rbAgotado.setSelected(true);
                    vista.mostrarImagen(p.getUrlImagen());
                    vista.mostrarInfoExtra(p.getInfoExtra());
                } else {
                    JOptionPane.showMessageDialog(vista, "Producto no encontrado: " + id);
                }
            }

            if (e.getSource() == vista.btnModificar) {
                double pre = Double.parseDouble(vista.txtPrecio.getText().trim());
                Producto p = modelo.buscar(id);
                if (p != null) {
                    p.setNombre(nom); p.setPrecio(pre);
                    p.setEstado(est); p.setUrlImagen(url);
                    modelo.actualizar(id, nom, pre, est);
                } else {
                    JOptionPane.showMessageDialog(vista, "Producto no encontrado: " + id);
                }
            }

            if (e.getSource() == vista.btnEliminar) {
                int opt = JOptionPane.showConfirmDialog(vista,
                    "¿Eliminar el producto con ID: " + id + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) modelo.eliminar(id);
            }

            if (e.getSource() == vista.btnLimpiar) {
                vista.txtId.setText("");
                vista.txtNombre.setText("");
                vista.txtPrecio.setText("");
                vista.txtUrlImagen.setText("");
                vista.lblImagen.setIcon(null);
                vista.lblImagen.setText("Sin imagen");
                vista.mostrarInfoExtra("");
            }

            llenarTabla();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio invalido, ingresa un numero.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
}