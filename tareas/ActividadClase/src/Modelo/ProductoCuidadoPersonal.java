package Modelo;


public class ProductoCuidadoPersonal extends Producto {

    private String  tipoHigiene;       
    private String  paraGenero;     
    private int     contenidoMl;
    private boolean esHipoalergenico;

    public ProductoCuidadoPersonal() {}

    public ProductoCuidadoPersonal(String id, String nombre, double precio, String estado,
                                    String urlImagen, String tipoHigiene,
                                    String paraGenero, int contenidoMl,
                                    boolean esHipoalergenico) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoHigiene      = tipoHigiene;
        this.paraGenero       = paraGenero;
        this.contenidoMl      = contenidoMl;
        this.esHipoalergenico = esHipoalergenico;
    }

    @Override public String getCategoria() { return "Cuidado Personal"; }

    @Override public String getInfoExtra() {
        return "Tipo: " + tipoHigiene +
               " | Para: " + paraGenero +
               " | Contenido: " + contenidoMl + "ml" +
               " | Hipoalergénico: " + (esHipoalergenico ? "Sí" : "No");
    }

    public String  getTipoHigiene()               { return tipoHigiene; }
    public String  getParaGenero()                { return paraGenero; }
    public int     getContenidoMl()               { return contenidoMl; }
    public boolean isEsHipoalergenico()           { return esHipoalergenico; }
    public void    setTipoHigiene(String v)       { this.tipoHigiene = v; }
    public void    setParaGenero(String v)        { this.paraGenero = v; }
    public void    setContenidoMl(int v)          { this.contenidoMl = v; }
    public void    setEsHipoalergenico(boolean v) { this.esHipoalergenico = v; }
}