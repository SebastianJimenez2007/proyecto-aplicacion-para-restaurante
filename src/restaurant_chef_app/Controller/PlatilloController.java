package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant_chef_app.clases.Platillo;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlatilloController {

    private static final String CARPETA_DATA = "data";
    private static final String ARCHIVO_PLATILLOS = "data/platillos.json";
    private static final Gson gson = new Gson();

    // Crea la carpeta "data" si no existe
    private static void crearCarpetaData() {
        File carpeta = new File(CARPETA_DATA);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    // Crea el archivo platillos.json si no existe
    private static void crearArchivoSiNoExiste() {
        File archivo = new File(ARCHIVO_PLATILLOS);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();

                // Guardar lista vacía inicial
                FileWriter writer = new FileWriter(archivo);
                writer.write("[]");
                writer.close();

                System.out.println("Archivo platillos.json creado automáticamente.");

            } catch (IOException e) {
                System.out.println("Error al crear archivo platillos.json: " + e.getMessage());
            }
        }
    }

    public static List<Platillo> obtenerPlatillosDisponibles() {
        crearCarpetaData();
        crearArchivoSiNoExiste();

        try (Reader reader = new FileReader(ARCHIVO_PLATILLOS)) {

            Type tipoLista = new TypeToken<List<Platillo>>() {}.getType();
            List<Platillo> lista = gson.fromJson(reader, tipoLista);

            return (lista != null) ? lista : new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Error al cargar los platillos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void guardarPlatillos(List<Platillo> platillos) {
        crearCarpetaData();
        crearArchivoSiNoExiste();

        try (Writer writer = new FileWriter(ARCHIVO_PLATILLOS)) {
            gson.toJson(platillos, writer);
            System.out.println("Platillos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar platillos: " + e.getMessage());
        }
    }
}
