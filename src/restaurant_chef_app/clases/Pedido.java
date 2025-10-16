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

public class Pedido {
    private int idPedido;
    private ArrayList<Platillo> platillosSeleccionados;
    private String estado;
    private String nombreCliente;

    public Pedido(int idPedido, String nombreCliente) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.estado = "Pendiente";
        this.platillosSeleccionados = new ArrayList<>();
    }

    public void agregarPlatillo(Platillo platillo) {
        platillosSeleccionados.add(platillo);
    }

    public double calcularTotal() {
        double total = 0;
        for (Platillo p : platillosSeleccionados) {
            total += p.getPrecio();
        }
        return total;
    }

    public void imprimirRecibo() {
        System.out.println("Recibo del pedido #" + idPedido);
        System.out.println("Cliente: " + nombreCliente);
        for (Platillo p : platillosSeleccionados) {
            System.out.println("- " + p.getNombre() + ": $" + p.getPrecio());
        }
        System.out.println("Total: $" + calcularTotal());
        System.out.println("Estado: " + estado);
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Platillo> getPlatillosSeleccionados() {
        return platillosSeleccionados;
    }
}
