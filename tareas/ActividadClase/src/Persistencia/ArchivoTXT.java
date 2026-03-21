package Persistencia;

import Modelo.Producto;
import java.io.*;
import java.util.ArrayList;

public class ArchivoTXT {
    private final String ARCHIVO = "productos.txt";

    public ArrayList<Producto> leerTXT() {
        ArrayList<Producto> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length == 4) {
                    lista.add(new Producto(d[0], d[1], Double.parseDouble(d[2]), d[3]));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public void guardarTXT(ArrayList<Producto> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : lista) {
                pw.println(p.toString());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}