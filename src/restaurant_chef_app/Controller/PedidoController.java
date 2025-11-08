/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant_chef_app.clases.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private static final String ARCHIVO_PEDIDOS = "pedidos.json";
    private static Gson gson = new Gson();

    public static List<Pedido> cargarPedidos() {
        try (Reader reader = new FileReader(ARCHIVO_PEDIDOS)) {
            return gson.fromJson(reader, new TypeToken<List<Pedido>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void guardarPedidos(List<Pedido> pedidos) {
        try (Writer writer = new FileWriter(ARCHIVO_PEDIDOS)) {
            gson.toJson(pedidos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void agregarPedido(Pedido p) {
        List<Pedido> pedidos = cargarPedidos();
        pedidos.add(p);
        guardarPedidos(pedidos);
    }

    public static void actualizarEstado(String id, String nuevoEstado) {
        List<Pedido> pedidos = cargarPedidos();
        for (Pedido p : pedidos) {
            if (p.getId().equals(id)) {
                p.cambiarEstado(nuevoEstado);
                break;
            }
        }
        guardarPedidos(pedidos);
    }
}