/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import restaurant_chef_app.clases.Platillo;

/**
 *
 * @author Sebastian
 */
public class PlatilloController {

    private static final String FILE_PATH = "src/restaurant_chef_app/Data/platillos.json";

    public static List<Platillo> obtenerPlatillosDisponibles() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Platillo>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.out.println("Error al cargar los platillos: " + e.getMessage());
            return List.of(); 
        }
    }
}