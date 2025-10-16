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
        pedido.setEstado(nuevoEstado);
        System.out.println("Estado del pedido actualizado a: " + nuevoEstado);
    }
}