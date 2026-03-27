package Modelo;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class ProductoDAO {
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public void agregar(Producto p) {
        listaProductos.add(p);
    }

    public ArrayList<Producto> listarTodo() {
        return listaProductos;
    }

    public Producto buscarPorSku(String sku) {
        for (Producto p : listaProductos) {
            if (p.sku.equalsIgnoreCase(sku)) {
                return p;
            }
        }
        return null;
    }

    public void cargarDesdeArchivo(String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");

                Producto p = new Producto();
                p.sku = datos[0];
                p.descripcion = datos[1];
                p.precioCompra = Double.parseDouble(datos[2]);
                p.porcentajeGanancia = Double.parseDouble(datos[3]);
                p.stock = Integer.parseInt(datos[4]);
                p.unidad = datos[5];
                p.categoria = datos[6];
                p.foto = datos[7];

                listaProductos.add(p);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error cargando productos: " + e.getMessage());
        }
    }
}