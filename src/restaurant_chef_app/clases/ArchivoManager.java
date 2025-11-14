/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.clases;

import java.io.*;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ArchivoManager {
    private static final String RUTA_USUARIOS = "data/usuarios.json";
    private static final Gson gson = new Gson();
    
    public static List<Propietario> leerPropietarios() {
        try {
            // Crear el directorio si no existe
            java.io.File directorio = new java.io.File("data");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            
            // Crear archivo si no existe
            java.io.File archivo = new java.io.File(RUTA_USUARIOS);
            if (!archivo.exists()) {
                crearArchivoUsuariosInicial();
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
            Type listType = new TypeToken<ArrayList<Propietario>>(){}.getType();
            List<Propietario> usuarios = gson.fromJson(contenido.toString(), listType);
            
            return usuarios != null ? usuarios : new ArrayList<>();
            
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private static void crearArchivoUsuariosInicial() {
        try {
            List<Propietario> usuarios = new ArrayList<>();
            
            // Crear usuario propietario por defecto
            Propietario propietario = new Propietario("001","Propietario Principal","admin123");
            usuarios.add(propietario);
            
            // Guardar el archivo
            FileWriter file = new FileWriter(RUTA_USUARIOS);
            gson.toJson(usuarios, file);
            file.close();
            
        } catch (IOException e) {
            System.err.println("Error al crear archivo inicial: " + e.getMessage());
        }
    }
    
    public static boolean validarCredenciales(String id, String contraseña) {
        List<Propietario> Usuarios = leerPropietarios();
        
        for (Propietario usuario : Usuarios) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }
}
