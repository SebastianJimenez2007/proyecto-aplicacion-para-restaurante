/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.clases;

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;

public class Menu {
    private ArrayList<Platillo> platillos;

    public Menu() {
        this.platillos = new ArrayList<>();
    }

    public void agregarPlatillo(Platillo platillo) {
        platillos.add(platillo);
        System.out.println("Platillo agregado: " + platillo.getNombre());
    }

    public void eliminarPlatillo(String nombre) {
        platillos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
        System.out.println("Platillo eliminado: " + nombre);
    }

    public void modificarPlatillo(Platillo nuevo) {
        for (int i = 0; i < platillos.size(); i++) {
            if (platillos.get(i).getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                platillos.set(i, nuevo);
                System.out.println("Platillo modificado: " + nuevo.getNombre());
                return;
            }
        }
        System.out.println("Platillo no encontrado: " + nuevo.getNombre());
    }

    public ArrayList<Platillo> listarPlatillos() {
        return platillos;
    }
}
