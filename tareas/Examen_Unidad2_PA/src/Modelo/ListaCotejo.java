package Modelo;
import java.util.ArrayList;

public class ListaCotejo {
    private ArrayList<Boolean> indicadores;

    public ListaCotejo() {
        indicadores = new ArrayList<>();
    }

    public ArrayList<Boolean> getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(ArrayList<Boolean> indicadores) {
        this.indicadores = indicadores;
    }
}