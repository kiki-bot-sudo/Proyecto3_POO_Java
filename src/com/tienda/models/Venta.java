package com.tienda.models;

import java.util.ArrayList;
import java.util.List;
import com.tienda.empleados.Empleado;

public class Venta {
    private String idVenta;
    private Cliente cliente;
    private Empleado empleado;
    private List<Producto> productos;
    private String fecha;
    private double total;

    public Venta(String idVenta, Cliente cliente, Empleado empleado, String fecha) {
        if (idVenta == null || idVenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de venta no puede estar vacío");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fecha = fecha;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto productos) {
        this.productos.add(productos);
    }

    public double calcularTotal() {
        double acumulado = 0.0;
        for (Producto producto : this.productos) {
            acumulado += producto.calcularPrecioFinal() * producto.getCantidad();
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

    public String getIdVenta() {
        return this.idVenta;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public String getFecha() {
        return this.fecha;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta='" + idVenta + '\'' +
                ", cliente=" + cliente.getNombre() +
                ", empleado=" + empleado.getNombre() +
                ", fecha='" + fecha + '\'' +
                ", productos=" + productos.size() +
                ", total=" + total +
                '}';
    }
}
