package Controlador;

import java.util.ArrayList;


import Controlador.ControladorDatosBase;

public class ControladorFiltro {

    private ControladorDatosBase datos;

    public ControladorFiltro() {
        datos = new ControladorDatosBase();
    }

   
    public ArrayList<String> obtenerProfesores() {
        return datos.obtenerProfesores();
    }

   
    public ArrayList<String> filtrarAsignaturas(String profesor) {
        return datos.obtenerAsignaturasPorProfesor(profesor);
    }

  
    public ArrayList<String> filtrarGrupos(String profesor, String materia) {
        return datos.obtenerGrupos(profesor, materia);
    }

  
    public ArrayList<String> obtenerAlumnos(String profesor, String materia, String grupo) {
        return datos.obtenerAlumnos(profesor, materia, grupo);
    }
}