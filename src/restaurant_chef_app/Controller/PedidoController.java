package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant_chef_app.clases.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import restaurant_chef_app.clases.Platillo;

public class PedidoController {

    private static final String ARCHIVO_PEDIDOS = "data/pedidos.json";
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

    
   public static String generarNuevoId() {
    List<Pedido> pedidos = cargarPedidos();
    int max = 0;

    for (Pedido p : pedidos) {
        try {
            int idNum = Integer.parseInt(p.getId());
            if (idNum > max) {
                max = idNum;
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido encontrado: " + p.getId());
        }
    }

    return String.valueOf(max + 1);
}


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
    
    public static void mostrarFactura(Pedido pedido) {
    StringBuilder factura = new StringBuilder();

    factura.append("=========== FACTURA ===========\n");
    factura.append("ID Pedido: ").append(pedido.getId()).append("\n");
    factura.append("Cliente: ").append(pedido.getNombreCliente()).append("\n");
    factura.append("Estado: ").append(pedido.getEstado()).append("\n");
    factura.append("--------------------------------\n");
    factura.append("Platillos:\n");

    for (Platillo p : pedido.getPlatillos()) {
        factura.append(" - ").append(p.getNombre())
               .append(" .... $").append(p.getPrecio()).append("\n");
    }

    factura.append("--------------------------------\n");
    factura.append("TOTAL: $").append(pedido.getTotal()).append("\n");
    factura.append("================================");

    // Mostrar al usuario
    JOptionPane.showMessageDialog(null, factura.toString(), 
                                  "Factura del Pedido", 
                                  JOptionPane.INFORMATION_MESSAGE);
}

}
