package Modelo;


public class ProductoPanaderia extends Producto {

    private String  tipoPan;           
    private boolean elaboracionDiaria; 
    private int     piezasPorPaquete;
    private boolean contieneGluten;

    public ProductoPanaderia() {}

    public ProductoPanaderia(String id, String nombre, double precio, String estado,
                              String urlImagen, String tipoPan,
                              boolean elaboracionDiaria, int piezasPorPaquete,
                              boolean contieneGluten) {
        super(id, nombre, precio, estado, urlImagen);
        this.tipoPan           = tipoPan;
        this.elaboracionDiaria = elaboracionDiaria;
        this.piezasPorPaquete  = piezasPorPaquete;
        this.contieneGluten    = contieneGluten;
    }

    @Override public String getCategoria() { return "Panadería y Tortillería"; }

    @Override public String getInfoExtra() {
        return "Tipo: " + tipoPan +
               " | Rotación diaria: " + (elaboracionDiaria ? "Sí" : "No") +
               " | Piezas/paq: " + piezasPorPaquete +
               " | Gluten: " + (contieneGluten ? "Sí" : "No");
    }

    public String  getTipoPan()                    { return tipoPan; }
    public boolean isElaboracionDiaria()           { return elaboracionDiaria; }
    public int     getPiezasPorPaquete()           { return piezasPorPaquete; }
    public boolean isContieneGluten()              { return contieneGluten; }
    public void    setTipoPan(String tipoPan)      { this.tipoPan = tipoPan; }
    public void    setElaboracionDiaria(boolean v) { this.elaboracionDiaria = v; }
    public void    setPiezasPorPaquete(int v)      { this.piezasPorPaquete = v; }
    public void    setContieneGluten(boolean v)    { this.contieneGluten = v; }
}