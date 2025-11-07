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
        
public class Empleado extends Usuario {

    public Empleado(String id, String nombre, String contraseña) {
        super(id, nombre, contraseña);
    }

    public void tomarPedido(Pedido pedido, ArrayList<Pedido> listaPedidos) {
        listaPedidos.add(pedido);
        System.out.println("Pedido tomado para " + pedido.getNombreCliente());
    }

    public void enviarPedidoACocina(Pedido pedido) {
        pedido.cambiarEstado("en preparacion");
        System.out.println("Pedido enviado a cocina: " + pedido.getId());
    }
}
