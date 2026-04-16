package com.tienda;

import com.tienda.empleados.Cajero;
import com.tienda.exceptions.EmpleadoException;
import com.tienda.exceptions.ProductoException;
import com.tienda.exceptions.SalarioInvalidoException;
import com.tienda.models.Cliente;
import com.tienda.models.ProductoNoPerecible;
import com.tienda.models.ProductoPerecible;
import com.tienda.models.Venta;
import com.tienda.servicios.TiendaServicio;

public class Main {
    public static void main(String[] args) {
        TiendaServicio servicio = new TiendaServicio();
        servicio.cargarDatos();

        if (servicio.getClientes().isEmpty() && servicio.getEmpleados().isEmpty() && servicio.getProductos().isEmpty()) {
            try {
                ProductoNoPerecible arroz = new ProductoNoPerecible("NP001", "Arroz", "Granos", 10, 2.50, 0);
                ProductoPerecible leche = new ProductoPerecible("P001", "Leche", "2026-04-10", "Lacteos", 8, 1.75, 10);
                Cajero cajero = new Cajero("C001", "Ana", 500.0, 1, 20);
                Cliente cliente = new Cliente("C001", "Carlos", "carlos@example.com");

                servicio.agregarProducto(arroz);
                servicio.agregarProducto(leche);
                servicio.agregarEmpleado(cajero);
                servicio.agregarCliente(cliente);

                Venta venta = new Venta("V001", cliente, cajero, "2026-03-12");
                venta.agregarProducto(arroz, 3);
                venta.agregarProducto(leche, 2);
                venta.calcularTotal();
                servicio.registrarVenta(venta);
            } catch (ProductoException | SalarioInvalidoException | EmpleadoException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Clientes: " + servicio.getClientes().size());
        System.out.println("Empleados: " + servicio.getEmpleados().size());
        System.out.println("Productos: " + servicio.getProductos().size());
        System.out.println("Ventas: " + servicio.getVentas().size());
    }
}
