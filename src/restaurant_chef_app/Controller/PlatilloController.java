package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant_chef_app.clases.Platillo;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

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

            Type tipoLista = new TypeToken<List<Platillo>>() {
            }.getType();
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
            //System.out.println("Platillos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar platillos: " + e.getMessage());
        }
    }

    // MÉTODO ALTERNATIVO: Buscar por categoría (sin usar Streams)
    public static List<Platillo> buscarPorCategoriaAlternativo(String categoria) {
        List<Platillo> todosLosPlatillos = obtenerPlatillosDisponibles();
        List<Platillo> platillosFiltrados = new ArrayList<>();

        for (Platillo platillo : todosLosPlatillos) {
            if (platillo.getCategoria() != null
                    && platillo.getCategoria().equalsIgnoreCase(categoria)) {
                platillosFiltrados.add(platillo);
            }
        }

        return platillosFiltrados;
    }

    // MÉTODO PARA OBTENER TODAS LAS CATEGORÍAS ÚNICAS
    public static List<String> obtenerCategorias() {
        List<Platillo> todosLosPlatillos = obtenerPlatillosDisponibles();

        return todosLosPlatillos.stream()
                .map(Platillo::getCategoria)
                .distinct()
                .collect(Collectors.toList());
    }

    // MÉTODO PARA BUSCAR POR NOMBRE (BONUS)
    public static List<Platillo> buscarPorNombre(String nombre) {
        List<Platillo> todosLosPlatillos = obtenerPlatillosDisponibles();

        return todosLosPlatillos.stream()
                .filter(platillo -> platillo.getNombre() != null
                && platillo.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    // MÉTODO PARA MOSTRAR PLATILLOS POR CATEGORÍAS
    public static DefaultListModel<String> obtenerPlatillosPorCategoria() {
        List<Platillo> todosLosPlatillos = obtenerPlatillosDisponibles();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        if (todosLosPlatillos.isEmpty()) {
            listModel.addElement("No hay platillos disponibles");
            return listModel;
        }

        // Agrupar platillos por categoría
        Map<String, List<Platillo>> platillosPorCategoria = new HashMap<>();

        for (Platillo platillo : todosLosPlatillos) {
            String categoria = platillo.getCategoria();
            platillosPorCategoria.putIfAbsent(categoria, new ArrayList<>());
            platillosPorCategoria.get(categoria).add(platillo);
        }

        // Agregar al ListModel organizado por categorías
        for (Map.Entry<String, List<Platillo>> entry : platillosPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            List<Platillo> platillos = entry.getValue();

            // Agregar categoría como separador
            listModel.addElement("=== " + categoria.toUpperCase() + " ===");

            // Agregar platillos de esta categoría
            for (Platillo platillo : platillos) {
                listModel.addElement(" " + platillo.getNombre());
                listModel.addElement("  • Precio $ " + platillo.getPrecio());
                listModel.addElement("Descripcion: -" + platillo.getDescripcion());
            }

            // Agregar línea en blanco entre categorías
            listModel.addElement("");
        }

        return listModel;
    }

    public static boolean editarPlatillo(Platillo platilloActualizado) {
        List<Platillo> platillos = obtenerPlatillosDisponibles();

        for (int i = 0; i < platillos.size(); i++) {
            if (platillos.get(i).getId() == platilloActualizado.getId()) {
                platillos.set(i, platilloActualizado);
                guardarPlatillos(platillos);
                return true;
            }
        }
        return false;
    }

    public static boolean eliminarPlatillo(int id) {
        List<Platillo> platillos = obtenerPlatillosDisponibles();

        for (int i = 0; i < platillos.size(); i++) {
            if (platillos.get(i).getId() == id) {
                platillos.remove(i);
                guardarPlatillos(platillos);
                return true;
            }
        }
        return false;
    }

    public static boolean crearPlatillo(Platillo nuevoPlatillo) {
        List<Platillo> platillos = obtenerPlatillosDisponibles();

        // Verificar si ya existe un platillo con el mismo ID
        for (Platillo platillo : platillos) {
            if (platillo.getId() == nuevoPlatillo.getId()) {
                JOptionPane.showMessageDialog(null,"Ya existe un platillo con ID: " + nuevoPlatillo.getId());
                return false;
            }
        }

        platillos.add(nuevoPlatillo);
        guardarPlatillos(platillos);
        return true;
    }
}
