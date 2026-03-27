package Modelo;
public class DetalleVenta {
    public int idVenta;
    public String skuProducto;
    public int cantidad;
    public double precioCobrado;

    public DetalleVenta() {}

    public DetalleVenta(int idVenta, String skuProducto, int cantidad, double precioCobrado) {
        this.idVenta = idVenta;
        this.skuProducto = skuProducto;
        this.cantidad = cantidad;
        this.precioCobrado = precioCobrado;
    }
}