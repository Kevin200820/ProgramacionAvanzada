package Modelo;


public class ProductoSnacks extends Producto {

    private String  tipoSnack;   
    private int     gramaje;       
    private boolean esSalado;
    private int     caloriasAprox;

    public ProductoSnacks() {}

    public ProductoSnacks(String id, String nombre, double precio, String estado,
                          String urlImagen, String tipoSnack,
                          int gramaje, boolean esSalado, int caloriasAprox) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoSnack     = tipoSnack;
        this.gramaje       = gramaje;
        this.esSalado      = esSalado;
        this.caloriasAprox = caloriasAprox;
    }

    @Override public String getCategoria() { return "Snacks y Dulcería"; }

    @Override public String getInfoExtra() {
        return "Tipo: " + tipoSnack +
               " | Gramaje: " + gramaje + "g" +
               " | Salado: " + (esSalado ? "Sí" : "No") +
               " | Calorías: ~" + caloriasAprox + " kcal";
    }

    public String  getTipoSnack()           { return tipoSnack; }
    public int     getGramaje()             { return gramaje; }
    public boolean isEsSalado()             { return esSalado; }
    public int     getCaloriasAprox()       { return caloriasAprox; }
    public void    setTipoSnack(String v)   { this.tipoSnack = v; }
    public void    setGramaje(int v)        { this.gramaje = v; }
    public void    setEsSalado(boolean v)   { this.esSalado = v; }
    public void    setCaloriasAprox(int v)  { this.caloriasAprox = v; }
}