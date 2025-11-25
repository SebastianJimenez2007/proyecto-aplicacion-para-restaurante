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
public class AdministradorController {

    private static final String RUTA_USUARIOS = "src/restaurant_chef_app/Data/Administrador.json";
    private static final Gson gson = new Gson();

    public static List<Administrador> leerAdministrador() {
        try {
            // Crear el directorio si no existe
            java.io.File directorio = new java.io.File(RUTA_USUARIOS);
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
            Type listType = new TypeToken<ArrayList<Administrador>>() {
            }.getType();
            List<Administrador> usuarios = gson.fromJson(contenido.toString(), listType);

            return usuarios != null ? usuarios : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void crearArchivoUsuariosInicial() {
        try {
            List<Administrador> usuarios = new ArrayList<>();

            // Crear usuario propietario por defecto
            Administrador administrador = new Administrador("001", "Administrador Principal", "admin123");
            usuarios.add(administrador);

            // Guardar el archivo
            FileWriter file = new FileWriter(RUTA_USUARIOS);
            gson.toJson(usuarios, file);
            file.close();

        } catch (IOException e) {
            System.err.println("Error al crear archivo inicial: " + e.getMessage());
        }
    }

    public static boolean validarCredenciales(String id, String contraseña) {
        List<Administrador> Usuarios = leerAdministrador();

        for (Administrador usuario : Usuarios) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña) && usuario.getTipo().equals("administrador")) {
                return true;
            }
        }
        return false;
    }

    public static boolean validarUsuarioRegistrado(String id) {
        List<Administrador> Usuarios = leerAdministrador();

        for (Administrador usuario : Usuarios) {
            if (usuario.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Administrador buscarAdministrador(String id) {
        List<Administrador> Usuarios = leerAdministrador();

        for (Administrador usuario : Usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public static void CrearUsuariosAdministrador(List<Administrador> usuarios, String id, String nombre, String contraseña) {
        try {

            // Crear usuario propietario
            Administrador administrador = new Administrador(id, nombre, contraseña);
            usuarios.add(administrador);

            // Guardar el archivo
            FileWriter file = new FileWriter(RUTA_USUARIOS);
            gson.toJson(usuarios, file);
            file.close();
        } catch (IOException e) {
            System.err.println("Error al crear Usuario " + e.getMessage());
        }
    }

    public static DefaultListModel<String> obtenerAdministradoresParaLista() {
        List<Administrador> administradores = leerAdministrador();
        DefaultListModel<String> modelo = new DefaultListModel<>();

        if (administradores.isEmpty()) {
            modelo.addElement("No hay administradores registrados");
            return modelo;
        }else{
            modelo.addElement("===ADMINISTRADORES===");
        }

        for (Administrador admin : administradores) {
            modelo.addElement("ID: " + admin.getId() + " - " + admin.getNombre());
        }

        return modelo;
    }
}
