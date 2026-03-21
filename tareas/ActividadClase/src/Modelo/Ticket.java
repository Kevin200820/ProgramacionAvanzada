package Modelo;


public class Ticket extends Producto {

    private int    cantidad;
    private double subtotal;

    // Constructor vacío requerido por Jackson
    public Ticket() {}

    public Ticket(String id, String nombre, double precio,
                  String estado, int cantidad) {
        super(id, nombre, precio, estado, "");
        this.cantidad = cantidad;
        this.subtotal = precio * cantidad;
    }

    @Override public String getCategoria() { return "Ticket"; }
    @Override public String getInfoExtra() { return "Cantidad: " + cantidad + " | Subtotal: $" + subtotal; }

    public int    getCantidad()           { return cantidad; }
    public double getSubtotal()           { return subtotal; }
    public void   setCantidad(int v)      { this.cantidad = v; this.subtotal = getPrecio() * v; }
    public void   setSubtotal(double v)   { this.subtotal = v; }
}