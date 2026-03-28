package Controlador;


import Modelo.Evaluacion;
import Modelo.Profesor;
import Modelo.Asignatura;
import Modelo.Grupo;

public class ControladorEvaluacion {

    
    public Evaluacion crearEvaluacion(Profesor profesor, Asignatura asignatura, Grupo grupo) {

        Evaluacion ev = new Evaluacion();

        ev.setProfesor(profesor);
        ev.setAsignatura(asignatura);
        ev.setGrupo(grupo);

       
        ev.setId();

        return ev;
    }

    
    public boolean validar(Evaluacion ev) {

        if (ev == null) return false;

        if (ev.getProfesor() == null) return false;
        if (ev.getAsignatura() == null) return false;
        if (ev.getGrupo() == null) return false;

        return true;
    }
}