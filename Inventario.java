import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;
    private List<Provedor> provedores;
    private List<Empleado> empleados;
    private List<Venta> ventas;

    public Inventario() {
        this.productos = new ArrayList<>();
        this.provedores = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo))
                return p;
        }
        return null;
    }

    public void registrarVenta(Venta venta) {
        ventas.add(venta);
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public void agregarProvedor(Provedor p) {
        provedores.add(p);
    }

     @Override
    public String toString() {
        return "Inventario{productos=" + productos.size() +
               ", empleados=" + empleados.size() +
               ", ventas=" + ventas.size() + "}";
    }
}

