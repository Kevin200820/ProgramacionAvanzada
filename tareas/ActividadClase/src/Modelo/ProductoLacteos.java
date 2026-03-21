package Modelo;


public class ProductoLacteos extends Producto {

    private boolean requiereRefrigeracion;
    private int     tempMaxCelsius;    
    private double  contenidoGrasaPct;   

    public ProductoLacteos() {}

    public ProductoLacteos(String id, String nombre, double precio, String estado,
                           String urlImagen, boolean requiereRefrigeracion,
                           int tempMaxCelsius, double contenidoGrasaPct) {
        super(id, nombre, precio, estado, urlImagen);
        this.requiereRefrigeracion = requiereRefrigeracion;
        this.tempMaxCelsius        = tempMaxCelsius;
        this.contenidoGrasaPct     = contenidoGrasaPct;
    }

    @Override public String getCategoria() { return "Lácteos y Huevo"; }

    @Override public String getInfoExtra() {
        return "Refrigeración: " + (requiereRefrigeracion ? "Sí" : "No") +
               " | Temp. máx: " + tempMaxCelsius + "°C" +
               " | Grasa: " + contenidoGrasaPct + "%";
    }

    public boolean isRequiereRefrigeracion()                 { return requiereRefrigeracion; }
    public int     getTempMaxCelsius()                       { return tempMaxCelsius; }
    public double  getContenidoGrasaPct()                    { return contenidoGrasaPct; }
    public void    setRequiereRefrigeracion(boolean v)       { this.requiereRefrigeracion = v; }
    public void    setTempMaxCelsius(int v)                  { this.tempMaxCelsius = v; }
    public void    setContenidoGrasaPct(double v)            { this.contenidoGrasaPct = v; }
}