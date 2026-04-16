package com.tienda.servicios;

import com.tienda.empleados.Empleado;
import com.tienda.exceptions.CSVParseException;
import com.tienda.models.Cliente;
import com.tienda.models.Producto;
import com.tienda.models.Proveedor;
import com.tienda.models.Venta;
import com.tienda.persistencia.ClienteCSV;
import com.tienda.persistencia.EmpleadoCSV;
import com.tienda.persistencia.ProductoCSV;
import com.tienda.persistencia.ProveedorCSV;
import com.tienda.persistencia.VentaCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TiendaServicio {

    private final ProductoCSV productoCSV = new ProductoCSV("productos.csv");
    private List<Cliente> clientes = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Proveedor> proveedores = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();

    public void cargarDatos() {
        try {
            clientes = ClienteCSV.leer();
        } catch (CSVParseException e) {
            clientes = new ArrayList<>();
        }

        try {
            empleados = EmpleadoCSV.leer();
        } catch (IOException e) {
            empleados = new ArrayList<>();
        }

        try {
            productos = productoCSV.cargar();
        } catch (IOException e) {
            productos = new ArrayList<>();
        }

        try {
            proveedores = ProveedorCSV.leer();
        } catch (CSVParseException e) {
            proveedores = new ArrayList<>();
        }

        try {
            ventas = VentaCSV.leer(clientes, empleados);
        } catch (IOException e) {
            ventas = new ArrayList<>();
        }
    }

    public void guardarDatos() {
        try {
            ClienteCSV.escribir(clientes);
            EmpleadoCSV.escribir(empleados);
            productoCSV.guardar(productos);
            ProveedorCSV.escribir(proveedores);
            VentaCSV.escribir(ventas);
        } catch (IOException e) {
            System.err.println("No se pudieron guardar los datos: " + e.getMessage());
        }
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        guardarDatos();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        guardarDatos();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        guardarDatos();
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
        guardarDatos();
    }

    public void registrarVenta(Venta venta) {
        ventas.add(venta);
        guardarDatos();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public List<Venta> getVentas() {
        return ventas;
    }
}
