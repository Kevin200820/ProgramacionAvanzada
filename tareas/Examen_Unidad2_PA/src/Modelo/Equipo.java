package Modelo;
import java.util.ArrayList;

public class Equipo {
    private ArrayList<String> alumnos;
    private float calificacionRubrica;

    public Equipo() {
        alumnos = new ArrayList<>();
    }

    public ArrayList<String> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<String> alumnos) {
        this.alumnos = alumnos;
    }

    public float getCalificacionRubrica() {
        return calificacionRubrica;
    }

    public void setCalificacionRubrica(float calificacionRubrica) {
        this.calificacionRubrica = calificacionRubrica;
    }
}