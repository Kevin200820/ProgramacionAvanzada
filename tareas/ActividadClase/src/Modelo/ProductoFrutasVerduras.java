package Modelo;


public class ProductoFrutasVerduras extends Producto {

    private boolean esTemporada;
    private boolean vendidoPorKg; 
    private int     diasVidaUtil;
    private String  origen; 

    public ProductoFrutasVerduras() {}

    public ProductoFrutasVerduras(String id, String nombre, double precio, String estado,
                                   String urlImagen, boolean esTemporada,
                                   boolean vendidoPorKg, int diasVidaUtil, String origen) {
        super(id, nombre, precio, estado, urlImagen);
        this.esTemporada   = esTemporada;
        this.vendidoPorKg  = vendidoPorKg;
        this.diasVidaUtil  = diasVidaUtil;
        this.origen        = origen;
    }

    @Override public String getCategoria() { return "Frutas y Verduras"; }

    @Override public String getInfoExtra() {
        return "Temporada: " + (esTemporada ? "Sí" : "No") +
               " | Venta: " + (vendidoPorKg ? "por kg" : "por pieza") +
               " | Vida útil: " + diasVidaUtil + " días" +
               " | Origen: " + origen;
    }

    public boolean isEsTemporada()              { return esTemporada; }
    public boolean isVendidoPorKg()             { return vendidoPorKg; }
    public int     getDiasVidaUtil()            { return diasVidaUtil; }
    public String  getOrigen()                  { return origen; }
    public void    setEsTemporada(boolean v)    { this.esTemporada = v; }
    public void    setVendidoPorKg(boolean v)   { this.vendidoPorKg = v; }
    public void    setDiasVidaUtil(int v)       { this.diasVidaUtil = v; }
    public void    setOrigen(String origen)     { this.origen = origen; }
}