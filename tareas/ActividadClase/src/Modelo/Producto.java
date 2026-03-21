package Modelo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProductoAbarrotes.class,       name = "Abarrotes"),
    @JsonSubTypes.Type(value = ProductoBebidas.class,         name = "Bebidas"),
    @JsonSubTypes.Type(value = ProductoLacteos.class,         name = "Lacteos"),
    @JsonSubTypes.Type(value = ProductoFrutasVerduras.class,  name = "FrutasVerduras"),
    @JsonSubTypes.Type(value = ProductoCarnes.class,          name = "Carnes"),
    @JsonSubTypes.Type(value = ProductoSalchichoneria.class,  name = "Salchichoneria"),
    @JsonSubTypes.Type(value = ProductoPanaderia.class,       name = "Panaderia"),
    @JsonSubTypes.Type(value = ProductoLimpieza.class,        name = "Limpieza"),
    @JsonSubTypes.Type(value = ProductoCuidadoPersonal.class, name = "CuidadoPersonal"),
    @JsonSubTypes.Type(value = ProductoSnacks.class,          name = "Snacks"),
    @JsonSubTypes.Type(value = ProductoMascotas.class,        name = "Mascotas")
})
public abstract class Producto {

    protected String id;
    protected String nombre;
    protected double precio;
    protected String estado;       
    protected String urlImagen;    

   
    public Producto() {}

    public Producto(String id, String nombre, double precio,
                    String estado, String urlImagen) {
        this.id        = id;
        this.nombre    = nombre;
        this.precio    = precio;
        this.estado    = estado;
        this.urlImagen = urlImagen;
    }

        public abstract String getCategoria();

    
    public abstract String getInfoExtra();

 
    public String getId()        { return id; }
    public String getNombre()    { return nombre; }
    public double getPrecio()    { return precio; }
    public String getEstado()    { return estado; }
    public String getUrlImagen() { return urlImagen; }

    
    public void setId(String id)           { this.id = id; }
    public void setNombre(String nombre)   { this.nombre = nombre; }
    public void setPrecio(double precio)   { this.precio = precio; }
    public void setEstado(String estado)   { this.estado = estado; }
    public void setUrlImagen(String url)   { this.urlImagen = url; }

    @Override
    public String toString() {
        return id + " | " + nombre + " | $" + precio + " | " + estado + " | " + getCategoria();
    }
}