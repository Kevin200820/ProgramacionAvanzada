package Modelo;


public class ProductoLimpieza extends Producto {

    private String  tipoLimpieza;      
    private boolean esConcentrado;
    private boolean requiereAislamiento; 
    private double  volumenLitros;

    public ProductoLimpieza() {}

    public ProductoLimpieza(String id, String nombre, double precio, String estado,
                             String urlImagen, String tipoLimpieza,
                             boolean esConcentrado, boolean requiereAislamiento,
                             double volumenLitros) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoLimpieza        = tipoLimpieza;
        this.esConcentrado       = esConcentrado;
        this.requiereAislamiento = requiereAislamiento;
        this.volumenLitros       = volumenLitros;
    }

    @Override public String getCategoria() { return "Limpieza del Hogar"; }

    @Override public String getInfoExtra() {
        return "Tipo: " + tipoLimpieza +
               " | Concentrado: " + (esConcentrado ? "Sí" : "No") +
               " | Aislamiento: " + (requiereAislamiento ? "Sí" : "No") +
               " | Vol: " + volumenLitros + "L";
    }

    public String  getTipoLimpieza()                  { return tipoLimpieza; }
    public boolean isEsConcentrado()                  { return esConcentrado; }
    public boolean isRequiereAislamiento()            { return requiereAislamiento; }
    public double  getVolumenLitros()                 { return volumenLitros; }
    public void    setTipoLimpieza(String v)          { this.tipoLimpieza = v; }
    public void    setEsConcentrado(boolean v)        { this.esConcentrado = v; }
    public void    setRequiereAislamiento(boolean v)  { this.requiereAislamiento = v; }
    public void    setVolumenLitros(double v)         { this.volumenLitros = v; }
}