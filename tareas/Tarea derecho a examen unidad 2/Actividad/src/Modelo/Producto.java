package Modelo;

public class Producto {
    public String sku;
    public String descripcion;
    public double precioCompra;
    public double porcentajeGanancia;
    public int stock;
    public String foto;
    public String unidad;
    public String categoria;

    public Producto() {}

    public double getPrecioVenta() {
        return precioCompra + (precioCompra * (porcentajeGanancia / 100));
    }
}