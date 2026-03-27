package Modelo;
public class Venta {
    public int idVenta;
    public String fecha;
    public double total;

    public Venta() {}

    public Venta(int idVenta, String fecha, double total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
    }
}