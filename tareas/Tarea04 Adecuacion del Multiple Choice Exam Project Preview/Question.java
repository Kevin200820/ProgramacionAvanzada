package modelo;

import java.util.*;

public class Question {

    private String enunciado;
    private String correcta;
    private String[] opciones;

    public Question(String enunciado, String correcta, String[] distractores) {

        this.enunciado = enunciado;
        this.correcta = correcta;

        opciones = new String[4];

        opciones[0] = correcta;
        System.arraycopy(distractores, 0, opciones, 1, 3);

        List<String> lista = Arrays.asList(opciones);
        Collections.shuffle(lista);
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespuestaCorrecta() {
        return correcta;
    }

    public String[] getOpciones() {
        return opciones;
    }
}