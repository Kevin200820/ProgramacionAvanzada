package Modelo;


public class ProductoCarnes extends Producto {

    private String tipoAnimal;     
    private boolean esCongelado;
    private String  corte;         
    private int     tempAlmacenC;  

    public ProductoCarnes() {}

    public ProductoCarnes(String id, String nombre, double precio, String estado,
                          String urlImagen, String tipoAnimal, boolean esCongelado,
                          String corte, int tempAlmacenC) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoAnimal   = tipoAnimal;
        this.esCongelado  = esCongelado;
        this.corte        = corte;
        this.tempAlmacenC = tempAlmacenC;
    }

    @Override public String getCategoria() { return "Carnes y Pescados"; }

    @Override public String getInfoExtra() {
        return "Animal: " + tipoAnimal +
               " | Congelado: " + (esCongelado ? "Sí" : "No") +
               " | Corte: " + corte +
               " | Temp: " + tempAlmacenC + "°C";
    }

    public String  getTipoAnimal()              { return tipoAnimal; }
    public boolean isEsCongelado()              { return esCongelado; }
    public String  getCorte()                   { return corte; }
    public int     getTempAlmacenC()            { return tempAlmacenC; }
    public void    setTipoAnimal(String v)      { this.tipoAnimal = v; }
    public void    setEsCongelado(boolean v)    { this.esCongelado = v; }
    public void    setCorte(String corte)       { this.corte = corte; }
    public void    setTempAlmacenC(int v)       { this.tempAlmacenC = v; }
}