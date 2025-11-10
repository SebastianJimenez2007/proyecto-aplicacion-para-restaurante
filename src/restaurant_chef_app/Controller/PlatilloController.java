/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.Controller;

import java.util.ArrayList;
import java.util.List;
import restaurant_chef_app.clases.Platillo;

/**
 *
 * @author Sebastian
 */
public class PlatilloController {
    
   
    public static List<Platillo> obtenerPlatillosDisponibles() {
        List<Platillo> platillos = new ArrayList<>();

        platillos.add(new Platillo("Hamburguesa", 15000, "Comidas"));
        platillos.add(new Platillo("Perro caliente", 10000, "Comidas"));
        platillos.add(new Platillo("Jugo de mora", 4000, "Bebidas"));
        platillos.add(new Platillo("Gaseosa", 3000, "Bebidas"));
        platillos.add(new Platillo("Torta de chocolate", 6000, "Postres"));
        platillos.add(new Platillo("Helado", 5000, "Postres"));

        return platillos;
    }
    
}
