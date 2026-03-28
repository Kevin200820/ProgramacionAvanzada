package Modelo;

import java.util.ArrayList;

public class Rubrica {
    private ArrayList<Float> criterios;
    private float promedio;

    public Rubrica() {
        criterios = new ArrayList<>();
    }

    public ArrayList<Float> getCriterios() {
        return criterios;
    }

    public void setCriterios(ArrayList<Float> criterios) {
        this.criterios = criterios;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    
    public void calcularPromedio() {
        float suma = 0;

        for (float c : criterios) {
            suma += c;
        }

        promedio = (criterios.size() > 0) ? suma / criterios.size() : 0;
    }
}