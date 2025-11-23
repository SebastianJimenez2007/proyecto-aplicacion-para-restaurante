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
import restaurant_chef_app.Controller.PedidoController;

public class Empleado extends Usuario {

    public String tipo;

    public Empleado(String id, String nombre, String contraseña) {
        super(id, nombre, contraseña);
        this.tipo = "empleado";
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

    public void tomarPedido(Pedido pedido, List<Pedido> listaPedidos) {
        listaPedidos.add(pedido);
        PedidoController.guardarPedidos(listaPedidos);
        System.out.println("Pedido tomado para " + pedido.getNombreCliente());
    }

    public void enviarPedidoACocina(Pedido pedido, List<Pedido> listaPedidos) {
        pedido.cambiarEstado("en preparacion");
        PedidoController.guardarPedidos(listaPedidos);
        System.out.println("Pedido enviado a cocina: " + pedido.getId());
    }
}
