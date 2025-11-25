/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.Controller;

import java.util.List;
import javax.swing.*;
import restaurant_chef_app.clases.*;

/**
 *
 * @author Sebastian JB
 */
public class UsuariosController {
    
    public static DefaultListModel<String> obtenerTodosLosUsuariosParaLista() {
        DefaultListModel<String> modelo = new DefaultListModel<>();

        // Obtener usuarios de cada tipo
        List<Cocinero> cocineros = CocineroController.leerCocinero();
        List<Administrador> administradores = AdministradorController.leerAdministrador();
        List<Empleado> empleados = EmpleadoController.leerEmpleado();

        // Agregar administradores
        modelo.addElement("=== ADMINISTRADORES ===");
        for (Administrador admin : administradores) {
            modelo.addElement(" ID: " + admin.getId() + " - " + admin.getNombre());
        }

        modelo.addElement(""); // Línea separadora

        // Agregar empleados
        modelo.addElement("=== EMPLEADOS ===");
        for (Empleado empleado : empleados) {
            modelo.addElement(" ID: " + empleado.getId() + " - " + empleado.getNombre());
        }

        modelo.addElement(""); // Línea separadora

        // Agregar cocineros
        modelo.addElement("=== COCINEROS ===");
        for (Cocinero cocinero : cocineros) {
            modelo.addElement("ID: " + cocinero.getId() + " - " + cocinero.getNombre());
        }

        if (cocineros.isEmpty() && administradores.isEmpty() && empleados.isEmpty()) {
            modelo.addElement("No hay usuarios registrados");
        }

        return modelo;
    }
    
    
}
