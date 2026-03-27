package Modelo;
import java.util.ArrayList;

public class ProveedorDAO {
    private ArrayList<Proveedor> listaProveedores;

    public ProveedorDAO() {
        listaProveedores = new ArrayList<>();
    }

    public void agregar(Proveedor p) {
        listaProveedores.add(p);
    }

    public ArrayList<Proveedor> listarTodo() {
        return listaProveedores;
    }
}