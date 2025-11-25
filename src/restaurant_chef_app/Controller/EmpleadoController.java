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
public class EmpleadoController {

    private static final String RUTA_USUARIOS = "src/restaurant_chef_app/Data/Empleados.json";
    private static final Gson gson = new Gson();

    public static List<Empleado> leerEmpleado() {
        try {
            // Crear el directorio si no existe
            java.io.File directorio = new java.io.File(RUTA_USUARIOS);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear archivo si no existe
            java.io.File archivo = new java.io.File(RUTA_USUARIOS);
            if (!archivo.exists()) {
                crearArchivoEmpleadoInicial();
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
            Type listType = new TypeToken<ArrayList<Empleado>>() {
            }.getType();
            List<Empleado> usuarios = gson.fromJson(contenido.toString(), listType);

            return usuarios != null ? usuarios : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void crearArchivoEmpleadoInicial() {
        try {
            List<Empleado> usuarios = new ArrayList<>();

            // Crear usuario propietario por defecto
            Empleado empleado = new Empleado("001", "Empleado Principal", "admin123");
            usuarios.add(empleado);

            // Guardar el archivo
            FileWriter file = new FileWriter(RUTA_USUARIOS);
            gson.toJson(usuarios, file);
            file.close();

        } catch (IOException e) {
            System.err.println("Error al crear archivo inicial: " + e.getMessage());
        }
    }

    public static boolean validarCredenciales(String id, String contraseña) {
        List<Empleado> Usuarios = leerEmpleado();

        for (Empleado usuario : Usuarios) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña) && usuario.getTipo().equals("empleado")) {
                return true;
            }
        }
        return false;
    }

    public static boolean editarEmpleado(String id, String nuevoNombre, String nuevaContraseña) {
        List<Empleado> empleados = leerEmpleado();

        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId().equals(id)) {
                Empleado empleadoActualizado = new Empleado(id, nuevoNombre, nuevaContraseña);
                empleados.set(i, empleadoActualizado);

                guardarEmpleados(empleados);
                return true;
            }
        }
        return false;
    }

    public static boolean eliminarEmpleado(String id) {
        List<Empleado> empleados = leerEmpleado();

        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId().equals(id)) {
                empleados.remove(i);
                guardarEmpleados(empleados);
                return true;
            }
        }
        return false;
    }

    private static void guardarEmpleados(List<Empleado> empleados) {
        try (FileWriter file = new FileWriter(RUTA_USUARIOS)) {
            gson.toJson(empleados, file);
        } catch (IOException e) {
            System.err.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    public static boolean validarEmpleadoRegistrado(String id) {
        List<Empleado> Usuarios = leerEmpleado();

        for (Empleado usuario : Usuarios) {
            if (usuario.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Empleado buscarEmpleado(String id) {
        List<Empleado> Usuarios = leerEmpleado();

        for (Empleado usuario : Usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public static void CrearUsuariosEmpleado(List<Empleado> usuarios, String id, String nombre, String contraseña) {
        try {

            // Crear usuario propietario
            Empleado empleado = new Empleado(id, nombre, contraseña);
            usuarios.add(empleado);

            // Guardar el archivo
            FileWriter file = new FileWriter(RUTA_USUARIOS);
            gson.toJson(usuarios, file);
            file.close();
        } catch (IOException e) {
            System.err.println("Error al crear Usuario " + e.getMessage());
        }
    }

    public static DefaultListModel<String> obtenerEmpleadosParaLista() {
        List<Empleado> empleados = leerEmpleado();
        DefaultListModel<String> modelo = new DefaultListModel<>();

        if (empleados.isEmpty()) {
            modelo.addElement("No hay empleados registrados");
            return modelo;
        } else {
            modelo.addElement("===EMPLEADOS===");
        }

        for (Empleado empleado : empleados) {
            modelo.addElement("ID: " + empleado.getId() + " - " + empleado.getNombre());
        }

        return modelo;
    }

}
