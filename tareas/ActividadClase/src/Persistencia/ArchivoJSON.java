package Persistencia;

import Modelo.ModeloVenta.VentaJSON;
import Modelo.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class ArchivoJSON {

    private final ObjectMapper mapper;

    private static final String ARCHIVO_PRODUCTOS = "data/productos.json";
    private static final String ARCHIVO_HISTORIAL = "data/historial_tickets.json";
    private static final String DIR_TICKETS       = "data/tickets/";

    public ArchivoJSON() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        new File("data/tickets").mkdirs();
    }


    public ArrayList<Producto> leerProductos() {
        try {
            File f = new File(ARCHIVO_PRODUCTOS);
            if (!f.exists()) return new ArrayList<>();
            Producto[] arr = mapper.readValue(f, Producto[].class);
            return new ArrayList<>(Arrays.asList(arr));
        } catch (Exception e) {
            System.err.println("Error leyendo productos.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void guardarProductos(ArrayList<Producto> lista) {
        try {
            new File("data").mkdirs();
            mapper.writeValue(new File(ARCHIVO_PRODUCTOS), lista);
        } catch (Exception e) {
            System.err.println("Error guardando productos.json: " + e.getMessage());
        }
    }

    
    public void guardarTicket(String folio, VentaJSON venta) {
        try {
            mapper.writeValue(new File(DIR_TICKETS + folio + ".json"), venta);
        } catch (Exception e) {
            System.err.println("Error guardando ticket " + folio + ": " + e.getMessage());
        }
    }

  
    public ArrayList<VentaJSON> leerHistorial() {
        try {
            File f = new File(ARCHIVO_HISTORIAL);
            if (!f.exists()) return new ArrayList<>();
            VentaJSON[] arr = mapper.readValue(f, VentaJSON[].class);
            return new ArrayList<>(Arrays.asList(arr));
        } catch (Exception e) {
            System.err.println("Error leyendo historial: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void registrarEnHistorial(VentaJSON venta) {
        ArrayList<VentaJSON> historial = leerHistorial();
        historial.add(venta);
        try {
            mapper.writeValue(new File(ARCHIVO_HISTORIAL), historial);
        } catch (Exception e) {
            System.err.println("Error guardando historial: " + e.getMessage());
        }
    }
}