package Controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Modelo.Evaluacion;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ControladorJSON {

    private final String ruta = "evaluaciones.json";
    private Gson gson;

    public ControladorJSON() {
        gson = new Gson();
    }

    public ArrayList<Evaluacion> leer() {
        try (FileReader reader = new FileReader(ruta)) {

            Type tipo = new TypeToken<ArrayList<Evaluacion>>(){}.getType();
            ArrayList<Evaluacion> lista = gson.fromJson(reader, tipo);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(ArrayList<Evaluacion> lista) {
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(lista, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Evaluacion buscar(String id) {
        for (Evaluacion e : leer()) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public void eliminar(String id) {
        ArrayList<Evaluacion> lista = leer();
        lista.removeIf(e -> e.getId().equals(id));
        guardar(lista);
    }

    public void guardarOActualizar(Evaluacion nueva) {

        ArrayList<Evaluacion> lista = leer();
        boolean existe = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(nueva.getId())) {
                lista.set(i, nueva);
                existe = true;
                break;
            }
        }

        if (!existe) lista.add(nueva);

        guardar(lista);
    }
}