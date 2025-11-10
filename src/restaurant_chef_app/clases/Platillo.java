
package restaurant_chef_app.clases;



import java.io.Serializable;

public class Platillo implements Serializable {
    private String nombre;
    private double precio;
    private String categoria;

    public Platillo(String nombre, double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = (categoria != null) ? categoria : "Sin categoría";
    }
    

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }

    public String getCategoria() {
        return categoria;
    }
    
    
}