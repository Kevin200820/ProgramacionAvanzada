package Modelo;

import Persistencia.ArchivoTXT;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventario {

    public ArrayList<Producto> listaProductos;
    private ArchivoTXT archivo = new ArchivoTXT();

    public Inventario() {
        
        listaProductos = archivo.leerTXT(); 
        if (listaProductos == null) {
            listaProductos = new ArrayList<>();
        }
    }

    public boolean existe(String id) {
        Iterator<Producto> it = listaProductos.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public Producto buscar(String id) {
        Iterator<Producto> it = listaProductos.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public void insertar(Producto p) {
        listaProductos.add(p);
        
        archivo.guardarTXT(listaProductos);
    }

    public boolean eliminar(String id) {
        Iterator<Producto> it = listaProductos.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getId().equalsIgnoreCase(id)) {
                it.remove();
                
                archivo.guardarTXT(listaProductos);
                return true;
            }
        }
        return false;
    }

    public boolean actualizar(String id, String nombre, double precio, String estado) {
        Producto p = buscar(id);
        if (p != null) {
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setEstado(estado);
          
            archivo.guardarTXT(listaProductos);
            return true;
        }
        return false;
    }
}