import java.util.ArrayList;
import java.util.List;

public class Venta {
    private String idVenta;
   private Cliente cliente;
   private Empleado empleado;
   private List<Producto> productos;
   private String fecha;
   private double total;

   public Venta(String idVenta, Cliente cliente, Empleado empleado, String fecha) {
      this.idVenta = idVenta;
      this.cliente = cliente;
      this.empleado = empleado;
      this.fecha = fecha;
      this.productos = new ArrayList<>();
   }

   public void agregarProducto(Producto var1) {
      this.productos.add(var1);
   }

   public double calcularTotal() {
      double acumulado = 0.0;
      for (Producto producto : this.productos) {
         acumulado += producto.calcularPreciofinal() * producto.getCantidad();
      }
      this.total = acumulado;
      return this.total;
   }

   public List<Producto> getProductos() {
      return this.productos;
   }

   public double getTotal() {
      return this.total;
   }

  
}
