package Modelo;
public class Grupo {
    private String nombre;

    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    public Grupo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
