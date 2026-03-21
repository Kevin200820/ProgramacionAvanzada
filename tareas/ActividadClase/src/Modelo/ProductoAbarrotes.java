package Modelo;



public class ProductoAbarrotes extends Producto {

    private String almacenamiento; 
    private double pesoKg;
    private boolean esPerecedero;  

    public ProductoAbarrotes() {}

    public ProductoAbarrotes(String id, String nombre, double precio, String estado,
                              String urlImagen, String almacenamiento, double pesoKg) {
        super(id, nombre, precio, estado, urlImagen);
        this.almacenamiento = almacenamiento;
        this.pesoKg         = pesoKg;
        this.esPerecedero   = false;
    }

    @Override public String getCategoria()  { return "Abarrotes"; }

    @Override public String getInfoExtra() {
        return "Almacenamiento: " + almacenamiento +
               " | Peso: " + pesoKg + "kg | Perecedero: No";
    }

    public String  getAlmacenamiento()                        { return almacenamiento; }
    public double  getPesoKg()                                { return pesoKg; }
    public boolean isEsPerecedero()                           { return esPerecedero; }
    public void    setAlmacenamiento(String almacenamiento)   { this.almacenamiento = almacenamiento; }
    public void    setPesoKg(double pesoKg)                   { this.pesoKg = pesoKg; }
    public void    setEsPerecedero(boolean esPerecedero)      { this.esPerecedero = esPerecedero; }
}