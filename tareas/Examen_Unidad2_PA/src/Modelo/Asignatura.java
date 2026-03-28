package Modelo;
public class Asignatura {
    private String nombre;

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura() {
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