package Modelo;

 public class ProductoMascotas extends Producto {

    private String tipoMascota;      
    private String tipoPresentacion;  
    private double pesoKg;
    private String edadMascota;    

    public ProductoMascotas() {}

    public ProductoMascotas(String id, String nombre, double precio, String estado,
                             String urlImagen, String tipoMascota,
                             String tipoPresentacion, double pesoKg, String edadMascota) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoMascota      = tipoMascota;
        this.tipoPresentacion = tipoPresentacion;
        this.pesoKg           = pesoKg;
        this.edadMascota      = edadMascota;
    }

    @Override public String getCategoria() { return "Mascotas"; }

    @Override public String getInfoExtra() {
        return "Mascota: " + tipoMascota +
               " | Presentación: " + tipoPresentacion +
               " | Peso: " + pesoKg + "kg" +
               " | Edad: " + edadMascota;
    }

    public String getTipoMascota()                  { return tipoMascota; }
    public String getTipoPresentacion()             { return tipoPresentacion; }
    public double getPesoKg()                       { return pesoKg; }
    public String getEdadMascota()                  { return edadMascota; }
    public void   setTipoMascota(String v)          { this.tipoMascota = v; }
    public void   setTipoPresentacion(String v)     { this.tipoPresentacion = v; }
    public void   setPesoKg(double v)               { this.pesoKg = v; }
    public void   setEdadMascota(String v)          { this.edadMascota = v; }
}