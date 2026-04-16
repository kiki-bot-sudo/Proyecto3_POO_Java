package com.tienda.models;

import java.util.ArrayList;
import java.util.List;
import com.tienda.empleados.Empleado;

public class Venta {
    private String idVenta;
    private Cliente cliente;
    private Empleado empleado;
    private List<Producto> productos;
    private List<Integer> cantidadesVendidas;
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
        this.cantidadesVendidas = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto) {
        agregarProducto(producto, 1);
    }

    public void agregarProducto(Producto producto, int cantidadVendida) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (cantidadVendida <= 0) {
            throw new IllegalArgumentException("La cantidad vendida debe ser mayor que 0");
        }

        this.productos.add(producto);
        this.cantidadesVendidas.add(cantidadVendida);
    }

    public double calcularTotal() {
        double acumulado = 0.0;
        for (int i = 0; i < this.productos.size(); i++) {
            Producto producto = this.productos.get(i);
            int cantidadVendida = this.cantidadesVendidas.get(i);
            acumulado += producto.calcularPrecioFinal() * cantidadVendida;
        }
        this.total = acumulado;
        return this.total;
    }


    //getters
    public List<Producto> getProductos() {
        return this.productos;
    }

    public List<Integer> getCantidadesVendidas() {
        return this.cantidadesVendidas;
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

    public String toCSV() {
        return idVenta + "," + cliente.getId() + "," + empleado.getId() + "," + fecha + "," + total;
    }

    public static Venta fromCSV(String linea, Cliente cliente, Empleado empleado) {
        if (linea == null || linea.trim().isEmpty()) {
            throw new IllegalArgumentException("La línea CSV de venta no puede estar vacía");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo para reconstruir la venta");
        }
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo para reconstruir la venta");
        }

        String[] campos = linea.split(",");
        if (campos.length < 5) {
            throw new IllegalArgumentException("Línea CSV inválida para Venta: " + linea);
        }

        String idVenta = campos[0].trim();
        String fecha = campos[3].trim();
        double total = Double.parseDouble(campos[4].trim());

        Venta venta = new Venta(idVenta, cliente, empleado, fecha);
        venta.total = total;
        return venta;
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
