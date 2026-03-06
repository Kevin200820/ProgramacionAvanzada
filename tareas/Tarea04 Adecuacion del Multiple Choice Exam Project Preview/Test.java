package modelo;

import java.util.*;

public class Test {

    private List<Question> preguntas;
    private int posicion;
    private int aciertos;
    private int intentos;

    public Test(List<Question> lista) {

        preguntas = lista;
        posicion = 0;
        aciertos = 0;
        intentos = 0;

        Collections.shuffle(preguntas);
    }

    public Question getPreguntaActual() {

        if (posicion < preguntas.size()) {
            return preguntas.get(posicion);
        }

        return null;
    }

    public boolean verificarRespuesta(String seleccion) {

        intentos++;

        boolean correcta = seleccion.equals(getPreguntaActual().getRespuestaCorrecta());

        if (correcta) {
            aciertos++;
        }

        return correcta;
    }

    public void siguientePregunta() {
        posicion++;
    }

    public int getIndiceActual() {
        return posicion;
    }

    public int getCorrectas() {
        return aciertos;
    }

    public int getTotalPreguntas() {
        return preguntas.size();
    }
}