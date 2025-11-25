/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.clases;

/**
 *
 * @author Usuario
 */
public class Administrador extends Usuario {
    
    public Administrador(String id, String nombre, String contraseña) {
        super(id, nombre, contraseña);
        this.tipo = "administrador";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    

    public void agregarPlatillo(Menu menu, Platillo platillo) {
        menu.agregarPlatillo(platillo);
    }

    public void eliminarPlatillo(Menu menu, String nombrePlatillo) {
        menu.eliminarPlatillo(nombrePlatillo);
    }

    public void modificarPlatillo(Menu menu, Platillo nuevoPlatillo) {
        menu.modificarPlatillo(nuevoPlatillo);
    }

    public void verMenu(Menu menu) {
        System.out.println("Menú actual:");
        for (Platillo p : menu.listarPlatillos()) {
            System.out.println("- " + p.getNombre() + ": $" + p.getPrecio());
        }
    }
}
