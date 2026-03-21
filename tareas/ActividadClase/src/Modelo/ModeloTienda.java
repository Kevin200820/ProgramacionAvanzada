package Modelo;

import Persistencia.ArchivoJSON;
import java.util.ArrayList;
import java.util.Iterator;

public class ModeloTienda {

    public ArrayList<Producto> listaProductos;
    private ArchivoJSON archivo = new ArchivoJSON();

    public ModeloTienda() {
        listaProductos = archivo.leerProductos();
        if (listaProductos == null || listaProductos.isEmpty()) {
            listaProductos = cargarDatosPredeterminados();
            archivo.guardarProductos(listaProductos);
        }
    }

    public boolean existe(String id) {
        for (Producto p : listaProductos)
            if (p.getId().equalsIgnoreCase(id)) return true;
        return false;
    }

    public Producto buscar(String id) {
        for (Producto p : listaProductos)
            if (p.getId().equalsIgnoreCase(id)) return p;
        return null;
    }

    public void insertar(Producto p) {
        listaProductos.add(p);
        archivo.guardarProductos(listaProductos);
    }

    public boolean eliminar(String id) {
        Iterator<Producto> it = listaProductos.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equalsIgnoreCase(id)) {
                it.remove();
                archivo.guardarProductos(listaProductos);
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
            archivo.guardarProductos(listaProductos);
            return true;
        }
        return false;
    }

    private ArrayList<Producto> cargarDatosPredeterminados() {
        ArrayList<Producto> lista = new ArrayList<>();

        lista.add(new ProductoAbarrotes("001", "Aceite Cristal Canola 5L",         149.00, "Disponible", "", "Anaquel", 5.0));
        lista.add(new ProductoAbarrotes("002", "Arroz Superior 1kg",                28.50, "Disponible", "", "Anaquel", 1.0));
        lista.add(new ProductoAbarrotes("003", "Frijol Peruano 1kg",                38.00, "Disponible", "", "Anaquel", 1.0));
        lista.add(new ProductoAbarrotes("004", "Pasta Barilla Espagueti 500g",      22.00, "Disponible", "", "Anaquel", 0.5));
        lista.add(new ProductoAbarrotes("005", "Azúcar Estándar 1kg",               27.00, "Disponible", "", "Anaquel", 1.0));

        lista.add(new ProductoBebidas("006", "Agua Bonafont 1.5L",                  13.50, "Disponible", "", "Agua",       1500, false, 0));
        lista.add(new ProductoBebidas("007", "Coca-Cola 2L",                        32.00, "Disponible", "", "Refresco",   2000, true,  0));
        lista.add(new ProductoBebidas("008", "Jugo Del Valle Naranja 1L",           28.00, "Disponible", "", "Jugo",       1000, false, 0));
        lista.add(new ProductoBebidas("009", "Monster Energy Verde 473ml",          42.00, "Disponible", "", "Energética",  473, true,  0));
        lista.add(new ProductoBebidas("010", "Carta Blanca Caguama 940ml",          38.00, "Disponible", "", "Cerveza",     940, true,  4.5));

        lista.add(new ProductoLacteos("011", "Leche Lala Entera 1L",                25.50, "Disponible", "", true, 4,  3.5));
        lista.add(new ProductoLacteos("012", "Huevo San Juan Rojo 30pz",            89.00, "Disponible", "", true, 8, 10.5));
        lista.add(new ProductoLacteos("013", "Yogurt Activia Natural 900g",         62.00, "Disponible", "", true, 4,  2.5));
        lista.add(new ProductoLacteos("014", "Mantequilla Lala 90g",                32.00, "Disponible", "", true, 4, 80.0));
        lista.add(new ProductoLacteos("015", "Crema Lala 500ml",                    38.00, "Disponible", "", true, 4, 18.0));

        lista.add(new ProductoFrutasVerduras("016", "Manzana Roja 1kg",             35.00, "Disponible", "", true, true,  7, "Chihuahua"));
        lista.add(new ProductoFrutasVerduras("017", "Plátano Tabasco 1kg",          18.00, "Disponible", "", true, true,  5, "Tabasco"));
        lista.add(new ProductoFrutasVerduras("018", "Jitomate Bola 1kg",            22.00, "Disponible", "", true, true,  4, "Sinaloa"));
        lista.add(new ProductoFrutasVerduras("019", "Cebolla Blanca 1kg",           19.00, "Disponible", "", true, true, 14, "Sonora"));
        lista.add(new ProductoFrutasVerduras("020", "Aguacate Hass 1kg",            55.00, "Disponible", "", true, true,  5, "Michoacán"));

        lista.add(new ProductoCarnes("021", "Pechuga de Pollo 1kg",                 98.00, "Disponible", "", "Pollo",   false, "Pechuga",   4));
        lista.add(new ProductoCarnes("022", "Carne Molida Res 1kg",                145.00, "Disponible", "", "Res",     false, "Molida",    4));
        lista.add(new ProductoCarnes("023", "Costilla de Cerdo 1kg",               120.00, "Disponible", "", "Cerdo",   false, "Costilla",  4));
        lista.add(new ProductoCarnes("024", "Atún Herdez en Agua 175g",             24.00, "Disponible", "", "Pescado", false, "Enlatado", 20));
        lista.add(new ProductoCarnes("025", "Filete de Tilapia 1kg",               110.00, "Disponible", "", "Pescado", true,  "Filete",  -18));

        lista.add(new ProductoSalchichoneria("026", "Jamón FUD Clásico 500g",       65.00, "Disponible", "", "Jamón",     false,  7, "FUD"));
        lista.add(new ProductoSalchichoneria("027", "Salchichas FUD 500g",          48.00, "Disponible", "", "Salchicha", false,  7, "FUD"));
        lista.add(new ProductoSalchichoneria("028", "Tocino Kir 200g",              72.00, "Disponible", "", "Tocino",    false, 10, "Kir"));
        lista.add(new ProductoSalchichoneria("029", "Queso Manchego LALA 400g",     92.00, "Disponible", "", "Queso",     false, 14, "LALA"));
        lista.add(new ProductoSalchichoneria("030", "Chorizo San Manuel 500g",      85.00, "Disponible", "", "Chorizo",   false,  5, "San Manuel"));

        lista.add(new ProductoPanaderia("031", "Pan Bimbo Blanco 680g",             48.00, "Disponible", "", "Pan de caja",     false, 20, true));
        lista.add(new ProductoPanaderia("032", "Concha Bimbo",                       5.00, "Disponible", "", "Pan dulce",       true,   1, true));
        lista.add(new ProductoPanaderia("033", "Bolillo Pieza",                      3.00, "Disponible", "", "Bolillo",         true,   1, true));
        lista.add(new ProductoPanaderia("034", "Tortilla de Maíz 1kg",              24.00, "Disponible", "", "Tortilla maíz",   true,  30, false));
        lista.add(new ProductoPanaderia("035", "Tortilla de Harina 30pz",           32.00, "Disponible", "", "Tortilla harina", true,  30, true));

        lista.add(new ProductoLimpieza("036", "Fabuloso Lavanda 5L",               149.60, "Disponible", "", "Desinfectante", false, true,  5.0));
        lista.add(new ProductoLimpieza("037", "Suavitel Primavera 3L",              84.80, "Disponible", "", "Suavizante",    false, true,  3.0));
        lista.add(new ProductoLimpieza("038", "Ariel Polvo 1kg",                    89.00, "Disponible", "", "Detergente",    true,  true,  0.0));
        lista.add(new ProductoLimpieza("039", "Pinol Multiusos 1L",                 42.00, "Disponible", "", "Desinfectante", false, true,  1.0));
        lista.add(new ProductoLimpieza("040", "Papel Higiénico Regio 4R",           52.00, "Disponible", "", "Papel",         false, false, 0.0));

        lista.add(new ProductoCuidadoPersonal("041", "Shampoo Pantene 400ml",       72.00, "Disponible", "", "Shampoo",     "Unisex", 400, false));
        lista.add(new ProductoCuidadoPersonal("042", "Jabón Dove Barra 90g",        28.00, "Disponible", "", "Jabón",       "Unisex",  90, true));
        lista.add(new ProductoCuidadoPersonal("043", "Colgate Triple Acción 75ml",  38.00, "Disponible", "", "Dental",      "Unisex",  75, false));
        lista.add(new ProductoCuidadoPersonal("044", "Rexona Desodorante 150ml",    68.00, "Disponible", "", "Desodorante", "Unisex", 150, false));
        lista.add(new ProductoCuidadoPersonal("045", "Crema Nivea Original 200ml",  95.00, "Disponible", "", "Crema",       "Mujer",  200, true));

        lista.add(new ProductoSnacks("046", "Sabritas Clásicas 45g",                18.50, "Disponible", "", "Papa",          45, true,  230));
        lista.add(new ProductoSnacks("047", "Galletas Oreo 176g",                   35.00, "Disponible", "", "Galleta",      176, false, 480));
        lista.add(new ProductoSnacks("048", "Chocolate Carlos V 25g",                8.00, "Disponible", "", "Chocolate",     25, false, 130));
        lista.add(new ProductoSnacks("049", "Mazapán De La Rosa",                    5.50, "Disponible", "", "Dulce",         21, false, 110));
        lista.add(new ProductoSnacks("050", "Tostitos Salsa Verde 65g",             22.00, "Disponible", "", "Botana salada", 65, true,  310));

        lista.add(new ProductoMascotas("051", "Pedigree Adulto 1kg",                98.00, "Disponible", "", "Perro", "Croquetas", 1.0, "Adulto"));
        lista.add(new ProductoMascotas("052", "Whiskas Atún 500g",                  72.00, "Disponible", "", "Gato",  "Croquetas", 0.5, "Adulto"));
        lista.add(new ProductoMascotas("053", "Arena Kitty Sand 4kg",               85.00, "Disponible", "", "Gato",  "Accesorio", 4.0, "Todos"));
        lista.add(new ProductoMascotas("054", "Snack Biter para Perro 100g",        42.00, "Disponible", "", "Perro", "Snack",     0.1, "Adulto"));
        lista.add(new ProductoMascotas("055", "Alimento Royal Canin Mini 2kg",     285.00, "Disponible", "", "Perro", "Croquetas", 2.0, "Adulto"));

        return lista;
    }
}