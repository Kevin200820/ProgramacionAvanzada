package Modelo;


public class ProductoSalchichoneria extends Producto {

    private String  tipoEmbutido;  
    private boolean vendidoPorKg;
    private int     diasCaducidad;
    private String  marca;

    public ProductoSalchichoneria() {}

    public ProductoSalchichoneria(String id, String nombre, double precio, String estado,
                                   String urlImagen, String tipoEmbutido,
                                   boolean vendidoPorKg, int diasCaducidad, String marca) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoEmbutido  = tipoEmbutido;
        this.vendidoPorKg  = vendidoPorKg;
        this.diasCaducidad = diasCaducidad;
        this.marca         = marca;
    }

    @Override public String getCategoria() { return "Salchichonería"; }

    @Override public String getInfoExtra() {
        return "Tipo: " + tipoEmbutido +
               " | Venta: " + (vendidoPorKg ? "por kg" : "empacado") +
               " | Caducidad: " + diasCaducidad + " días" +
               " | Marca: " + marca;
    }

    public String  getTipoEmbutido()                { return tipoEmbutido; }
    public boolean isVendidoPorKg()                 { return vendidoPorKg; }
    public int     getDiasCaducidad()               { return diasCaducidad; }
    public String  getMarca()                       { return marca; }
    public void    setTipoEmbutido(String v)        { this.tipoEmbutido = v; }
    public void    setVendidoPorKg(boolean v)       { this.vendidoPorKg = v; }
    public void    setDiasCaducidad(int v)          { this.diasCaducidad = v; }
    public void    setMarca(String marca)           { this.marca = marca; }
}