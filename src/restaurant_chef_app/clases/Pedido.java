/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_chef_app.clases;

/**
 *
 * @author Usuario
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {
    private String id;
    private ArrayList<Platillo> platillos;
    private String estado;
    private String nombreCliente;
    private double total;

    public Pedido(String id, String nombreCliente) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.platillos = new ArrayList<>();
        this.estado = "Pendiente";
        this.total = 0;
    }

    public void agregarPlatillo(Platillo p){
        platillos.add(p);
        total += p.getPrecio();
    }

    public void cambiarEstado(String nuevoEstado){
        this.estado = nuevoEstado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString(){
        return "Pedido [ID=" + id + ", Cliente=" + nombreCliente + ", Total = $" +
                total + ", Estado=" + estado + "]";
    }
}
