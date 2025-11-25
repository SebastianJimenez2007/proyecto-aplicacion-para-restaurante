package restaurant_chef_app.Controller;

import java.util.ArrayList;
import restaurant_chef_app.clases.Pedido;
import restaurant_chef_app.clases.*;

public class DatosController {
 
 private static ArrayList<Pedido> pedidos = new ArrayList<>();
 
 public static void agregarPedidos(Pedido p){
     pedidos.add(p);
     
 }
 
 public static ArrayList<Pedido> getPedidos(){
     return pedidos;
 }
}
