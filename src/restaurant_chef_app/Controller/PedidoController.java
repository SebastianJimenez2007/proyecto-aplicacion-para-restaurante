package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant_chef_app.clases.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    private static final String ARCHIVO_PEDIDOS = "pedidos.json";
    private static final Gson gson = new Gson();

    // -----------------------
    //  CARGAR PEDIDOS
    // -----------------------
    public static List<Pedido> cargarPedidos() {
        File file = new File(ARCHIVO_PEDIDOS);

        // Si no existe, lo creamos vacío
        if (!file.exists()) {
            guardarPedidos(new ArrayList<>());
        }

        try (Reader reader = new FileReader(file)) {

            List<Pedido> lista = gson.fromJson(reader, new TypeToken<List<Pedido>>() {}.getType());
            return (lista != null) ? lista : new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Error al cargar pedidos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // -----------------------
    //  GUARDAR PEDIDOS
    // -----------------------
    public static void guardarPedidos(List<Pedido> pedidos) {
        try (Writer writer = new FileWriter(ARCHIVO_PEDIDOS)) {
            gson.toJson(pedidos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar pedidos: " + e.getMessage());
        }
    }

    // -----------------------
    //  AGREGAR PEDIDO NUEVO
    // -----------------------
    public static void agregarPedido(Pedido nuevo) {
        List<Pedido> pedidos = cargarPedidos();
        pedidos.add(nuevo);
        guardarPedidos(pedidos);
    }

    // -----------------------
    //  ACTUALIZAR ESTADO
    // -----------------------
    public static void actualizarEstado(String idPedido, String nuevoEstado) {

        List<Pedido> pedidos = cargarPedidos();

        for (Pedido p : pedidos) {
            if (p.getId().equals(idPedido)) {
                p.setEstado(nuevoEstado);
                break;
            }
        }

        guardarPedidos(pedidos);
    }

    // -----------------------
    //  ELIMINAR PEDIDO ENTREGADO (opcional)
    // -----------------------
    public static void eliminarPedido(String idPedido) {

        List<Pedido> pedidos = cargarPedidos();

        pedidos.removeIf(p -> p.getId().equals(idPedido));

        guardarPedidos(pedidos);
    }
}
