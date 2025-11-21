/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.clases;

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;

public class Restaurante {
    private String nombre;
    private String direccion;
    private ArrayList<Usuario> usuarios;
    private Menu menu;
    private ArrayList<Pedido> pedidos;

    public Restaurante(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.usuarios = new ArrayList<>();
        this.menu = new Menu();
        this.pedidos = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario agregado: " + usuario.getNombre());
    }

    public void eliminarUsuario(String idUsuario) {
        usuarios.removeIf(u -> u.getId().equalsIgnoreCase(idUsuario));
        System.out.println("Usuario eliminado con ID: " + idUsuario);
    }

    public void mostrarInformacion() {
        System.out.println("Restaurante: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Usuarios registrados: " + usuarios.size());
        System.out.println("Platillos en el menú: " + menu.listarPlatillos().size());
        System.out.println("Pedidos activos: " + pedidos.size());
    }

    // Getters
    public Menu getMenu() {
        return menu;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
