
package restaurant_chef_app.clases;


import java.io.Serializable;

public class Platillo implements Serializable {
    private String nombre;
    private double precio;

    public Platillo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
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
}
