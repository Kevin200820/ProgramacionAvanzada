package Modelo;

import java.util.ArrayList;

public class Evaluacion {
    private String id;
    private Asignatura asignatura;
    private Profesor profesor;
    private Grupo grupo;

    private ProductoIntegrador producto;
    private Rubrica rubrica;
    private ListaCotejo lista;

    private ArrayList<Equipo> equipos;

    public Evaluacion() {
        equipos = new ArrayList<>();
    }

  
    public String generarId() {
        if (asignatura == null || profesor == null || grupo == null) {
            return "";
        }
        return asignatura.getNombre() + "_" + profesor.getNombre() + "_" + grupo.getNombre();
    }

    
    public void setId() {
        this.id = generarId();
    }


    public String getId() {
        return id;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public ProductoIntegrador getProducto() {
        return producto;
    }

    public void setProducto(ProductoIntegrador producto) {
        this.producto = producto;
    }

    public Rubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }

    public ListaCotejo getLista() {
        return lista;
    }

    public void setLista(ListaCotejo lista) {
        this.lista = lista;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }
}