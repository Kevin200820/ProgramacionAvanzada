package Vista;

import javax.swing.*;
import java.awt.*;

public class VistaProductos extends JPanel {
    public JTextField txtSku, txtDesc, txtPrecioCompra, txtPorcentajeGanancia, txtStock;
    public JComboBox<String> cbUnidad, cbCategoria;
    public JLabel lblPrevisualizacionFoto;
    public JButton btnGuardar = new JButton("Guardar Producto");
    public JTable tablaProductos = new JTable();

    public VistaProductos() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(8, 2, 5, 5));

        txtSku = new JTextField();
        txtDesc = new JTextField();
        txtPrecioCompra = new JTextField();
        txtPorcentajeGanancia = new JTextField();
        txtStock = new JTextField();
        cbUnidad = new JComboBox<>();
        cbCategoria = new JComboBox<>();

        formulario.add(new JLabel("SKU:")); formulario.add(txtSku);
        formulario.add(new JLabel("Descripción:")); formulario.add(txtDesc);
        formulario.add(new JLabel("Precio Compra:")); formulario.add(txtPrecioCompra);
        formulario.add(new JLabel("% Ganancia:")); formulario.add(txtPorcentajeGanancia);
        formulario.add(new JLabel("Stock:")); formulario.add(txtStock);
        formulario.add(new JLabel("Unidad:")); formulario.add(cbUnidad);
        formulario.add(new JLabel("Categoría:")); formulario.add(cbCategoria);
        formulario.add(btnGuardar);

        
        lblPrevisualizacionFoto = new JLabel("Imagen", SwingConstants.CENTER);
        lblPrevisualizacionFoto.setPreferredSize(new Dimension(220, 220));
        lblPrevisualizacionFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        add(lblPrevisualizacionFoto, BorderLayout.EAST);
    }

  
    public void mostrarImagen(String nombre) {
        try {
            ImageIcon img = new ImageIcon("imagenes/" + nombre);

            Image imagenEscalada = img.getImage().getScaledInstance(
                    200, 200,
                    Image.SCALE_SMOOTH
            );

            lblPrevisualizacionFoto.setIcon(new ImageIcon(imagenEscalada));
            lblPrevisualizacionFoto.setText(""); 

        } catch (Exception e) {
            lblPrevisualizacionFoto.setText("Sin imagen");
        }
    }
}