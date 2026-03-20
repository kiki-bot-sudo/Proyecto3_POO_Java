package com.tienda;

import com.tienda.empleados.*;
import com.tienda.exceptions.ProductoException;
import com.tienda.models.*;

public class Main {
    public static void main(String[] args) {
        try {
            Inventario inventario = new Inventario();

            ProductoNoPerecible arroz = new ProductoNoPerecible("NP001", "Arroz", "Granos", 1, 2.50, 0);
            ProductoPerecible leche = new ProductoPerecible(
                    "P001", "Leche", "2026-04-10", "Lacteos", 2, 1.75, 10
            );

            inventario.agregarProducto(arroz);
            inventario.agregarProducto(leche);

            Empleado cajero = new Cajero("E01", "Ana", 500.0, "Cajero", 1, 30);
            inventario.agregarEmpleado(cajero);

            Cliente cliente = new Cliente("Carlos");
            Venta venta = new Venta("V001", cliente, cajero, "2026-03-12");
            venta.agregarProducto(arroz);
            venta.agregarProducto(leche);

            double total = venta.calcularTotal();
            inventario.registrarVenta(venta);

            System.out.println("Total venta: $" + total);
            System.out.println(inventario);
        } catch (ProductoException e) {
            System.err.println("Error al crear producto: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        }
    }
}
