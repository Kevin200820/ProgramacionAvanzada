package Controlador;

import java.util.ArrayList;


import Modelo.Evaluacion;
import Modelo.Profesor;
import Modelo.Asignatura;
import Modelo.Grupo;
import Modelo.ProductoIntegrador;
import Modelo.Rubrica;
import Modelo.ListaCotejo;
import Modelo.Equipo;


import Controlador.ControladorJSON;
import Controlador.ControladorExcel;
import Controlador.ControladorDatosBase;

public class ControladorPrincipal {

    private ControladorJSON json;
    private ControladorExcel excel;
    private ControladorDatosBase datos;

    public ControladorPrincipal() {
        json = new ControladorJSON();
        excel = new ControladorExcel();
        datos = new ControladorDatosBase();
    }

    public void guardarEvaluacion(Evaluacion ev) {

        if (ev == null) return;

        if (ev.getId() == null || ev.getId().isEmpty()) {
            ev.setId();
        }

        if (ev.getProfesor() == null || ev.getAsignatura() == null || ev.getGrupo() == null) {
            System.out.println("Datos incompletos");
            return;
        }

        json.guardarOActualizar(ev);
        excel.generarOActualizarExcel(ev);
    }

    public Evaluacion cargarEvaluacion(String id) {
        return json.buscar(id);
    }

    public void eliminarEvaluacion(String id) {
        json.eliminar(id);
    }

    public ArrayList<String> obtenerProfesores() {
        return datos.obtenerProfesores();
    }

    public ArrayList<String> obtenerAsignaturas(String profesor) {
        return datos.obtenerAsignaturasPorProfesor(profesor);
    }

    public ArrayList<String> obtenerGrupos(String profesor, String materia) {
        return datos.obtenerGrupos(profesor, materia);
    }

    public ArrayList<String> obtenerAlumnos(String profesor, String materia, String grupo) {
        return datos.obtenerAlumnos(profesor, materia, grupo);
    }
}