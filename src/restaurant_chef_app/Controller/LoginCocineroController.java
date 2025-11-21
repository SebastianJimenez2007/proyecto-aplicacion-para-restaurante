/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import javax.swing.*;
import restaurant_chef_app.clases.*;

/**
 *
 * @author Sebastian
 */
public class LoginCocineroController {
    
    private static final String RUTA_USUARIOS = "src/restaurant_chef_app/Data/Cocinero.json";
    private static final Gson gson = new Gson();

    public static List<Cocinero> leerCocinero() {
        try {
            // Crear el directorio si no existe
            java.io.File directorio = new java.io.File(RUTA_USUARIOS);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear archivo si no existe
            java.io.File archivo = new java.io.File(RUTA_USUARIOS);
            if (!archivo.exists()) {
                crearArchivoCocineroInicial();
            }

            // Leer el archivo con BufferedReader y FileReader
            BufferedReader reader = new BufferedReader(new FileReader(RUTA_USUARIOS));
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }
            reader.close();

            // Convertir JSON a lista de usuarios usando Gson
            Type listType = new TypeToken<ArrayList<Cocinero>>() {
            }.getType();
            List<Cocinero> usuarios = gson.fromJson(contenido.toString(), listType);

            
            return usuarios != null ? usuarios : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void crearArchivoCocineroInicial() {
        try {
            List<Cocinero> usuarios = new ArrayList<>();

            // Crear usuario propietario por defecto
            Cocinero cocinero = new Cocinero("001", "Cocinero Principal", "admin123", "cocinero");
            usuarios.add(cocinero);

            // Guardar el archivo
            FileWriter file = new FileWriter(RUTA_USUARIOS);
            gson.toJson(usuarios, file);
            file.close();

        } catch (IOException e) {
            System.err.println("Error al crear archivo inicial: " + e.getMessage());
        }
    }

    public static boolean validarCredenciales(String id, String contraseña) {
        List<Cocinero> Usuarios = leerCocinero();

        for (Cocinero usuario : Usuarios) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña) && usuario.getTipo().equals("cocinero")) {
                return true;
            }
        }
        return false;
    }

    public static boolean validarCocineroRegistrado(String id) {
        List<Cocinero> Usuarios = leerCocinero();

        for (Cocinero usuario : Usuarios) {
            if (usuario.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Cocinero buscarCocinero(String id) {
        List<Cocinero> Usuarios = leerCocinero();

        for (Cocinero usuario : Usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public static void CrearUsuariosCocinero(List<Cocinero> usuarios, String id, String nombre, String contraseña) {
        try {

            // Crear usuario propietario
            Cocinero cocinero = new Cocinero(id, nombre, contraseña, "cocinero");
            usuarios.add(cocinero);

            // Guardar el archivo
            FileWriter file = new FileWriter(RUTA_USUARIOS);
            gson.toJson(usuarios, file);
            file.close();
        } catch (IOException e) {
            System.err.println("Error al crear Usuario " + e.getMessage());
        }
    }
}
