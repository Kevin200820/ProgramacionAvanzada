package modelo;

import java.io.*;
import java.util.*;

public class FileManager {

    private String titulo;
    private String colA;
    private String colB;

    private List<String> listaA = new ArrayList<>();
    private List<String> listaB = new ArrayList<>();

    public void cargarArchivo(File archivo) throws Exception {

        listaA.clear();
        listaB.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            titulo = br.readLine();
            String encabezados = br.readLine();

            if (titulo == null || encabezados == null) {
                throw new Exception("Archivo incompleto");
            }

            String[] enc = encabezados.split(",");

            colA = enc[0].trim();
            colB = enc[1].trim();

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(",");

                listaA.add(partes[0].trim());
                listaB.add(partes[1].trim());
            }
        }

        if (listaA.size() < 5) {
            throw new Exception("Mínimo 5 registros");
        }
    }

    public List<Question> generarPreguntas(boolean invertir) {

        List<Question> preguntas = new ArrayList<>();

        List<String> enunciados = invertir ? listaB : listaA;
        List<String> respuestas = invertir ? listaA : listaB;

        for (int i = 0; i < enunciados.size(); i++) {

            String[] distractores = obtenerDistractores(respuestas, i);

            preguntas.add(new Question(enunciados.get(i), respuestas.get(i), distractores));
        }

        return preguntas;
    }

    private String[] obtenerDistractores(List<String> lista, int correcto) {

        List<String> copia = new ArrayList<>(lista);

        copia.remove(correcto);

        Collections.shuffle(copia);

        return new String[]{
                copia.get(0),
                copia.get(1),
                copia.get(2)
        };
    }

    public String getTitulo() {
        return titulo;
    }

    public String getInstruccion(boolean invertido) {

        if (invertido) {
            return "Dado [" + colB + "] selecciona [" + colA + "]";
        }

        return "Dado [" + colA + "] selecciona [" + colB + "]";
    }
}