package restaurant_chef_app.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant_chef_app.clases.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
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

            List<Pedido> lista = gson.fromJson(reader, new TypeToken<List<Pedido>>() {
            }.getType());
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

    public static void guardarPedidosEntregados(List<Pedido> pedidosEntregados) {
        String archivoEntregados = "data/pedidos_entregados.json";

        try (Writer writer = new FileWriter(archivoEntregados)) {
            gson.toJson(pedidosEntregados, writer);
            JOptionPane.showMessageDialog(null, "Pedidos entregados guardados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar pedidos entregados: " + e.getMessage());
        }
    }

    public static DefaultListModel<String> obtenerPedidosEntregadosParaLista() {
        String archivoEntregados = "data/pedidos_entregados.json";
        DefaultListModel<String> listModel = new DefaultListModel<>();

        File file = new File(archivoEntregados);
        if (!file.exists()) {
            listModel.addElement("No hay pedidos entregados registrados");
            return listModel;
        }

        try (Reader reader = new FileReader(file)) {
            List<Pedido> pedidosEntregados = gson.fromJson(reader, new TypeToken<List<Pedido>>() {
            }.getType());

            if (pedidosEntregados == null || pedidosEntregados.isEmpty()) {
                listModel.addElement("No hay pedidos entregados registrados");
            } else {
               for (Pedido pedido : pedidosEntregados) {
                // Encabezado del pedido
                listModel.addElement("═══════════════════════════════════");
                listModel.addElement("Cliente: " + pedido.getNombreCliente());
                listModel.addElement("Total: $" + String.format("%,.0f", pedido.getTotal()));
                listModel.addElement("Platillos:");
                
                // Lista de platillos
                for (Platillo platillo : pedido.getPlatillos()) {
                    listModel.addElement("  • " + platillo.getNombre() + " - $" + 
                                        String.format("%,.0f", platillo.getPrecio()));
                }
                listModel.addElement(""); // Línea en blanco entre pedidos
            }
            }

        } catch (IOException e) {
            listModel.addElement("Error al cargar pedidos entregados");
        }

        return listModel;
    }

    public static void marcarPedidoComoEntregado(String idPedido) {
        List<Pedido> pedidos = cargarPedidos();
        List<Pedido> pedidosEntregados = cargarPedidosEntregados();

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId().equals(idPedido)) {
                Pedido pedidoEntregado = pedidos.get(i);
                pedidoEntregado.setEstado("Entregado");
                pedidosEntregados.add(pedidoEntregado);
                pedidos.remove(i);
                break;
            }
        }
        
        guardarPedidos(pedidos);
        guardarPedidosEntregados(pedidosEntregados);
    }


    public static List<Pedido> cargarPedidosEntregados() {
        String archivoEntregados = "data/pedidos_entregados.json";
        File file = new File(archivoEntregados);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            List<Pedido> lista = gson.fromJson(reader, new TypeToken<List<Pedido>>() {
            }.getType());
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al cargar pedidos entregados: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
