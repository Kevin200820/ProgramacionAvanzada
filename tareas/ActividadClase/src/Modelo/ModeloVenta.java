package Modelo;

import Persistencia.ArchivoJSON;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModeloVenta {

    private ArrayList<Ticket> carrito = new ArrayList<>();
    private ArchivoJSON archivoJSON   = new ArchivoJSON();

    public void agregarAlCarrito(Ticket t) { carrito.add(t); }
    public void limpiarCarrito()           { carrito.clear(); }
    public ArrayList<Ticket> getCarrito()  { return carrito; }

    public double calcularTotalCarrito() {
        double total = 0;
        for (Ticket t : carrito) total += t.getSubtotal();
        return total;
    }


    public String registrarVentaFinal() {
        String folio = "TKT-" + LocalDateTime.now()
                       .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        VentaJSON venta = new VentaJSON();
        venta.folio  = folio;
        venta.fecha  = LocalDateTime.now()
                       .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        venta.items  = new ArrayList<>(carrito);
        venta.total  = calcularTotalCarrito();

        archivoJSON.guardarTicket(folio, venta);     
        archivoJSON.registrarEnHistorial(venta);   
        return folio;
    }


    public static class VentaJSON {
        public String            folio;
        public String            fecha;
        public ArrayList<Ticket> items;
        public double            total;
        public VentaJSON() {}
    }
}