/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.clases;

/**
 *
 * @author Usuario
 */
import java.util.*;

public class Cocinero extends Usuario {

    public Cocinero(String id, String nombre, String contraseña) {
        super(id, nombre, contraseña);
        this.tipo = "propietario";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    
    public List<Pedido> verPedidosPendientes(List<Pedido> listaPedidos) {
        List<Pedido> pendientes = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if ("En preparación".equalsIgnoreCase(pedido.getEstado())) {
                pendientes.add(pedido);
            }
        }
        return pendientes;
    }

    public void actualizarEstadoPedido(Pedido pedido, String nuevoEstado) {
        pedido.cambiarEstado(nuevoEstado);
        System.out.println("Estado del pedido actualizado a: " + nuevoEstado);
    }
}
