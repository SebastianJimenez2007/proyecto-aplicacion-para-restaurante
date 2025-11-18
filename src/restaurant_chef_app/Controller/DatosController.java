package restaurant_chef_app.Controller;

import java.util.ArrayList;
import restaurant_chef_app.clases.Pedido;
import restaurant_chef_app.clases.Restaurante;

public class DatosController {
 private static ArrayList<Restaurante> restaurantes = new ArrayList<>();
 private static ArrayList<Pedido> pedidos = new ArrayList<>();
 
 
 public static ArrayList<Restaurante> getRestaurantes(){
     return restaurantes;
 }
 public static void agregarRestaurante(Restaurante r){
     restaurantes.add(r);
 }
 
 public static void agregarPedidos(Pedido p){
     pedidos.add(p);
     
 }
 
 public static ArrayList<Pedido> getPedidos(){
     return pedidos;
 }
}
