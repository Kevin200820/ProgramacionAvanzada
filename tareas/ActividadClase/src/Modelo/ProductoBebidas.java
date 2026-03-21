package Modelo;


public class ProductoBebidas extends Producto {

    private String  tipoBebida;    
    private int     volumenML;
    private boolean esCarbonada;
    private double  gradosAlcohol; 

    public ProductoBebidas() {}

    public ProductoBebidas(String id, String nombre, double precio, String estado,
                           String urlImagen, String tipoBebida, int volumenML,
                           boolean esCarbonada, double gradosAlcohol) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoBebida    = tipoBebida;
        this.volumenML     = volumenML;
        this.esCarbonada   = esCarbonada;
        this.gradosAlcohol = gradosAlcohol;
    }

    @Override public String getCategoria() { return "Bebidas"; }

    @Override public String getInfoExtra() {
        return "Tipo: " + tipoBebida +
               " | Vol: " + volumenML + "ml" +
               " | Carbonada: " + (esCarbonada ? "Sí" : "No") +
               (gradosAlcohol > 0 ? " | Alcohol: " + gradosAlcohol + "°" : "");
    }

    public String  getTipoBebida()                  { return tipoBebida; }
    public int     getVolumenML()                   { return volumenML; }
    public boolean isEsCarbonada()                  { return esCarbonada; }
    public double  getGradosAlcohol()               { return gradosAlcohol; }
    public void    setTipoBebida(String tipoBebida) { this.tipoBebida = tipoBebida; }
    public void    setVolumenML(int volumenML)       { this.volumenML = volumenML; }
    public void    setEsCarbonada(boolean v)        { this.esCarbonada = v; }
    public void    setGradosAlcohol(double v)       { this.gradosAlcohol = v; }
}