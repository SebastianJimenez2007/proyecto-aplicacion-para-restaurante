/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.clases;


/**
 *
 * @author Usuario
 */
public class Propietario extends Usuario {
    
    public String tipo ;
    public Propietario(String id, String nombre, String contraseña, String tipo) {
        super(id, nombre, contraseña);
        this.tipo = "propietario";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Administrador crearCuentaAdministrador(String id, String nombre, String contraseña) {
        return new Administrador(id, nombre, contraseña,"administrador");
    }

    public Empleado crearCuentaEmpleado(String id, String nombre, String contraseña) {
        return new Empleado(id, nombre, contraseña,"empleado");
    }

    public void supervisarActividad() {
        System.out.println("Supervisando actividad del restaurante...");
        // Aquí podrías agregar lógica para ver reportes, pedidos, usuarios activos, etc.
    }
}

