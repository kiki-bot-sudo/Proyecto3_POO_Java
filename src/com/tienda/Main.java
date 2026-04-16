package com.tienda;

import com.tienda.empleados.Cajero;
import com.tienda.empleados.Empleado;
import com.tienda.empleados.Gerente;
import com.tienda.exceptions.ClienteNoEncontradoException;
import com.tienda.exceptions.EmpleadoException;
import com.tienda.exceptions.EmpleadoNoEncontradoException;
import com.tienda.exceptions.ProductoException;
import com.tienda.exceptions.SalarioInvalidoException;
import com.tienda.exceptions.SinCodigoEncontradoException;
import com.tienda.models.Cliente;
import com.tienda.models.Producto;
import com.tienda.models.ProductoNoPerecible;
import com.tienda.models.ProductoPerecible;
import com.tienda.models.Venta;
import com.tienda.servicios.TiendaServicio;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TiendaServicio servicio = new TiendaServicio();
        servicio.cargarDatos();

        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerEntero(scanner, "Opcion: ");

                try {
                    switch (opcion) {
                        case 1 -> mostrarResumen(servicio);
                        case 2 -> listarProductos(servicio.getProductos());
                        case 3 -> agregarCliente(scanner, servicio);
                        case 4 -> agregarProducto(scanner, servicio);
                        case 5 -> agregarEmpleado(scanner, servicio);
                        case 6 -> registrarVenta(scanner, servicio);
                        case 0 -> System.out.println("Saliendo...");
                        default -> System.out.println("Opcion invalida");
                    }
                } catch (ProductoException | SalarioInvalidoException | EmpleadoException |
                         ClienteNoEncontradoException | EmpleadoNoEncontradoException |
                         SinCodigoEncontradoException e) {
                    System.err.println(e.getMessage());
                }
            } while (opcion != 0);
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("1. Ver resumen y persistencia");
        System.out.println("2. Listar productos");
        System.out.println("3. Agregar cliente");
        System.out.println("4. Agregar producto");
        System.out.println("5. Agregar empleado");
        System.out.println("6. Registrar venta");
        System.out.println("0. Salir");
    }

    private static void mostrarResumen(TiendaServicio servicio) {
        System.out.println("Clientes: " + servicio.getClientes().size());
        System.out.println("Empleados: " + servicio.getEmpleados().size());
        System.out.println("Productos: " + servicio.getProductos().size());
        System.out.println("Proveedores: " + servicio.getProveedores().size());
        System.out.println("Ventas: " + servicio.getVentas().size());
        System.out.println("Persistencia: datos cargados desde CSV y guardados al agregar registros.");
    }

    private static void listarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        for (Producto producto : productos) {
            System.out.println("- " + producto.getClass().getSimpleName()
                    + " | " + producto.getCodigo()
                    + " | " + producto.getNombre()
                    + " | categoria: " + producto.getCategoria()
                    + " | disponible: " + producto.estaDisponible()
                    + " | precio final: $" + producto.calcularPrecioFinal());
        }
    }

    private static void agregarCliente(Scanner scanner, TiendaServicio servicio) {
        String id = leerTexto(scanner, "Id cliente: ");
        String nombre = leerTexto(scanner, "Nombre: ");
        String email = leerTexto(scanner, "Email: ");
        servicio.agregarCliente(new Cliente(id, nombre, email));
        System.out.println("Cliente guardado.");
    }

    private static void agregarProducto(Scanner scanner, TiendaServicio servicio) throws ProductoException {
        int tipo = leerEntero(scanner, "1 Perecible, 2 No perecible: ");
        String codigo = leerTexto(scanner, "Codigo: ");
        String nombre = leerTexto(scanner, "Nombre: ");
        String categoria = leerTexto(scanner, "Categoria: ");
        int cantidad = leerEntero(scanner, "Cantidad: ");
        double precio = leerDouble(scanner, "Precio: ");

        if (tipo == 1) {
            String fechaVencimiento = leerTexto(scanner, "Fecha vencimiento: ");
            double descuento = leerDouble(scanner, "Descuento: ");
            servicio.agregarProducto(new ProductoPerecible(codigo, nombre, fechaVencimiento, categoria, cantidad, precio, descuento));
        } else {
            double promocion = leerDouble(scanner, "Promocion: ");
            servicio.agregarProducto(new ProductoNoPerecible(codigo, nombre, categoria, cantidad, precio, promocion));
        }

        System.out.println("Producto guardado.");
    }

    private static void agregarEmpleado(Scanner scanner, TiendaServicio servicio) throws SalarioInvalidoException, EmpleadoException {
        int tipo = leerEntero(scanner, "1 Cajero, 2 Gerente: ");
        String id = leerTexto(scanner, "Id empleado: ");
        String nombre = leerTexto(scanner, "Nombre: ");
        double salario = leerDouble(scanner, "Salario: ");

        if (tipo == 1) {
            int caja = leerEntero(scanner, "Caja asignada: ");
            int dia = leerEntero(scanner, "Dias trabajados: ");
            servicio.agregarEmpleado(new Cajero(id, nombre, salario, caja, dia));
        } else {
            String departamento = leerTexto(scanner, "Departamento: ");
            servicio.agregarEmpleado(new Gerente(id, nombre, salario, departamento));
        }

        System.out.println("Empleado guardado.");
    }

    private static void registrarVenta(Scanner scanner, TiendaServicio servicio)
            throws ClienteNoEncontradoException, EmpleadoNoEncontradoException, SinCodigoEncontradoException {
        String idVenta = leerTexto(scanner, "Id venta: ");
        Cliente cliente = servicio.buscarClientePorId(leerTexto(scanner, "Id cliente: "));
        Empleado empleado = servicio.buscarEmpleadoPorId(leerTexto(scanner, "Id empleado: "));
        String fecha = leerTexto(scanner, "Fecha: ");
        Venta venta = new Venta(idVenta, cliente, empleado, fecha);

        int cantidadItems = leerEntero(scanner, "Cuantos productos va a agregar: ");
        for (int i = 0; i < cantidadItems; i++) {
            Producto producto = servicio.buscarProductoPorCodigo(leerTexto(scanner, "Codigo producto: "));
            int cantidadVendida = leerEntero(scanner, "Cantidad vendida: ");
            venta.agregarProducto(producto, cantidadVendida);
        }

        venta.calcularTotal();
        servicio.registrarVenta(venta);
        System.out.println("Venta guardada. Total: $" + venta.getTotal());
    }

    private static String leerTexto(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(scanner, mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero valido.");
            }
        }
    }

    private static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(scanner, mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un valor valido.");
            }
        }
    }
}
