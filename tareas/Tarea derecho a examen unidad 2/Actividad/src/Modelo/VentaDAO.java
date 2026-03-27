package Modelo;
import java.util.ArrayList;

public class VentaDAO {
    private ArrayList<Venta> listaVentas;
    private ArrayList<DetalleVenta> listaDetalles;

    public VentaDAO() {
        listaVentas = new ArrayList<>();
        listaDetalles = new ArrayList<>();
    }

    public void registrarVenta(Venta v, ArrayList<DetalleVenta> detalles) {
        listaVentas.add(v);
        listaDetalles.addAll(detalles);
    }

    public ArrayList<Venta> listarVentas() {
        return listaVentas;
    }
}