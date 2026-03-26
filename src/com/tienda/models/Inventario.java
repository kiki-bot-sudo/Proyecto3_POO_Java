package com.tienda.models;

import java.util.ArrayList;
import java.util.List;
import com.tienda.empleados.Empleado;
import com.tienda.exceptions.SinCodigoEncontradoException;

public class Inventario {
    private List<Producto> productos;
    private List<Proveedor> proveedores;
    private List<Empleado> empleados;
    private List<Venta> ventas;

    public Inventario() {
        this.productos = new ArrayList<>();
        this.proveedores = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        for (Producto p : productos) {
            if (p.getCodigo().equals(producto.getCodigo())) {
                throw new IllegalArgumentException("Ya existe un producto con codigo: " + producto.getCodigo());
            }
        }

        productos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    public Producto buscarPorCodigo(String codigo) throws SinCodigoEncontradoException{
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo))
                return p;
        }
        throw new SinCodigoEncontradoException(codigo);
    }

    public void registrarVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula");
        }
        if (venta.getProductos().isEmpty()) {
            throw new IllegalArgumentException("No se puede registrar una venta sin productos");
        }

        venta.calcularTotal();
        ventas.add(venta);
    }

    public boolean eliminarProductoPorCodigo(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(codigo)) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Producto> filtrarPorCategoria(String categoria) {
        List<Producto> resultado = new ArrayList<>();

        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public void agregarProveedor(Proveedor p) {
        proveedores.add(p);
    }

    @Override
    public String toString() {
        return "Inventario{productos=" + productos.size() +
               ", empleados=" + empleados.size() +
               ", ventas=" + ventas.size() + "}";
    }
}
