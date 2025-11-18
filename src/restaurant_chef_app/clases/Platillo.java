package restaurant_chef_app.clases;

import java.io.Serializable;

public class Platillo implements Serializable {
    
    private int id;
    private String nombre;
    private double precio;
    private String categoria;
    private String descripcion;

    public Platillo(int id, String nombre, double precio, String categoria, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = (categoria != null) ? categoria : "Sin categoría";
        this.descripcion = (descripcion != null) ? descripcion : "Sin descripción";
    }

    // Constructor vacío obligatorio para Gson
    public Platillo() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}
