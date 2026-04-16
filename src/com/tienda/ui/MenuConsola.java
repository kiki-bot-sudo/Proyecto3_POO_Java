package com.tienda.ui;

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
import com.tienda.models.Proveedor;
import com.tienda.models.Venta;
import com.tienda.servicios.TiendaServicio;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class MenuConsola {

    private final TiendaServicio servicio;

    public MenuConsola(TiendaServicio servicio) {
        this.servicio = servicio;
    }

    public void iniciar() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerEntero(scanner, "Opcion: ");

                try {
                    switch (opcion) {
                        case 1 -> mostrarResumen();
                        case 2 -> listarProductos(servicio.getProductos());
                        case 3 -> agregarCliente(scanner);
                        case 4 -> agregarProducto(scanner);
                        case 5 -> agregarEmpleado(scanner);
                        case 6 -> registrarVenta(scanner);
                        case 7 -> listarClientesYEmpleados();
                        case 8 -> agregarProveedor(scanner);
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

    private void mostrarMenu() {
        System.out.println();
        System.out.println("1. Ver resumen y persistencia");
        System.out.println("2. Listar productos");
        System.out.println("3. Agregar cliente");
        System.out.println("4. Agregar producto");
        System.out.println("5. Agregar empleado");
        System.out.println("6. Registrar venta");
        System.out.println("7. Listar clientes,empleados y proveedores");
        System.out.println("8. Agregar proveedor");
        System.out.println("0. Salir");
    }

    private void mostrarResumen() {
        System.out.println();
        System.out.println("===== RESUMEN =====");
        System.out.println("Clientes: " + servicio.getClientes().size());
        System.out.println("Empleados: " + servicio.getEmpleados().size());
        System.out.println("Productos: " + servicio.getProductos().size());
        System.out.println("Proveedores: " + servicio.getProveedores().size());
        System.out.println("Ventas: " + servicio.getVentas().size());
        System.out.println();
        System.out.println("- Datos cargados desde CSV al iniciar");
        System.out.println("- Datos guardados al agregar registros");
        System.out.println("===================");
        System.out.println();
    }

    private void listarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        for (Producto producto : productos) {
            System.out.println("- " + producto.getClass().getSimpleName()
                    + " | " + producto.getCodigo()
                    + " | " + producto.getNombre()
                    + " | proveedor: " + producto.getIdProveedor()
                    + " | categoria: " + producto.getCategoria()
                    + " | disponible: " + producto.estaDisponible()
                    + " | precio final: $" + producto.calcularPrecioFinal());
        }
    }

    private void agregarCliente(Scanner scanner) {
        String id = leerTexto(scanner, "Id cliente: ");
        String nombre = leerNombreValido(scanner, "Nombre: ");
        String email = leerCorreoValido(scanner, "Email: ");
        servicio.agregarCliente(new Cliente(id, nombre, email));
        System.out.println("Cliente guardado.");
    }

    private void agregarProducto(Scanner scanner) throws ProductoException {
        if (servicio.getProveedores().isEmpty()) {
            System.out.println("Primero agrega al menos un proveedor (opcion 8).");
            return;
        }

        String idProveedor = leerIdProveedorExistente(scanner, "Id proveedor: ");
        int tipo = leerEntero(scanner, "1 Perecible, 2 No perecible: ");
        String codigo = leerTexto(scanner, "Codigo: ");
        String nombre = leerTexto(scanner, "Nombre: ");
        String categoria = leerTexto(scanner, "Categoria: ");
        int cantidad = leerEntero(scanner, "Cantidad: ");
        double precio = leerDouble(scanner, "Precio: ");

        if (tipo == 1) {
            String fechaVencimiento = leerFechaValida(scanner, "Fecha vencimiento (00/00/00): ");
            double descuento = leerDouble(scanner, "Descuento: ");
            ProductoPerecible producto = new ProductoPerecible(codigo, nombre, fechaVencimiento, categoria, cantidad, precio, descuento);
            producto.setIdProveedor(idProveedor);
            servicio.agregarProducto(producto);
        } else {
            double promocion = leerDouble(scanner, "Promocion: ");
            ProductoNoPerecible producto = new ProductoNoPerecible(codigo, nombre, categoria, cantidad, precio, promocion);
            producto.setIdProveedor(idProveedor);
            servicio.agregarProducto(producto);
        }

        System.out.println("Producto guardado con proveedor " + idProveedor + ".");
    }

    private void agregarEmpleado(Scanner scanner) throws SalarioInvalidoException, EmpleadoException {
        int tipo = leerEntero(scanner, "1 Cajero, 2 Gerente: ");
        String id = leerIdEmpleadoValido(scanner, "Id empleado (formato C000): ");
        String nombre = leerNombreValido(scanner, "Nombre: ");
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

        private void registrarVenta(Scanner scanner)
            throws ClienteNoEncontradoException, EmpleadoNoEncontradoException, SinCodigoEncontradoException, ProductoException {
        String idVenta = leerTexto(scanner, "Id venta: ");
        Cliente cliente = servicio.buscarClientePorId(leerTexto(scanner, "Id cliente: "));
        Empleado empleado = servicio.buscarEmpleadoPorId(leerTexto(scanner, "Id empleado: "));
        String fecha = leerFechaValida(scanner, "Fecha (00/00/00): ");
        Venta venta = new Venta(idVenta, cliente, empleado, fecha);

        int cantidadItems = leerEntero(scanner, "Cuantos productos va a agregar: ");
        List<Producto> productosVenta = new ArrayList<>();
        List<Integer> cantidadesVenta = new ArrayList<>();
        for (int i = 0; i < cantidadItems; i++) {
            Producto producto = servicio.buscarProductoPorCodigo(leerTexto(scanner, "Codigo producto: "));
            int cantidadVendida = leerEntero(scanner, "Cantidad vendida: ");
            if (cantidadVendida > producto.getCantidad()) {
                throw new ProductoException("No hay suficiente stock para " + producto.getNombre());
            }
            productosVenta.add(producto);
            cantidadesVenta.add(cantidadVendida);
        }

        for (int i = 0; i < productosVenta.size(); i++) {
            Producto producto = productosVenta.get(i);
            int cantidadVendida = cantidadesVenta.get(i);
            producto.reducirCantidad(cantidadVendida);
            venta.agregarProducto(producto, cantidadVendida);
        }

        venta.calcularTotal();
        servicio.registrarVenta(venta);
        System.out.println("Venta guardada. Total: $" + venta.getTotal());
    }

    private void agregarProveedor(Scanner scanner) {
        String id = leerTexto(scanner, "Id proveedor: ");
        String nombre = leerNombreValido(scanner, "Nombre proveedor: ");
        String telefono = leerTelefonoValido(scanner, "Telefono (solo numeros): ");
        servicio.agregarProveedor(new Proveedor(id, nombre, telefono));
        System.out.println("Proveedor guardado.");
    }

    private void listarClientesYEmpleados() {
        System.out.println("Clientes:");
        if (servicio.getClientes().isEmpty()) {
            System.out.println("- Sin clientes");
        } else {
            for (Cliente cliente : servicio.getClientes()) {
                System.out.println("- " + cliente.getId() + " | " + cliente.getNombre());
            }
        }

        System.out.println("Empleados:");
        if (servicio.getEmpleados().isEmpty()) {
            System.out.println("- Sin empleados");
        } else {
            for (Empleado empleado : servicio.getEmpleados()) {
                System.out.println("- " + empleado.getId() + " | " + empleado.getNombre() + " | " + empleado.getPuesto());
            }
        }

        System.out.println("Proveedores:");
        if (servicio.getProveedores().isEmpty()) {
            System.out.println("- Sin proveedores");
        } else {
            for (Proveedor proveedor : servicio.getProveedores()) {
                System.out.println("- " + proveedor.getId() + " | " + proveedor.getNombre() + " | " + proveedor.getTelefono());
            }
        }
    }

    private String leerTexto(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(scanner, mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero valido.");
            }
        }
    }

    private double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(scanner, mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un valor valido.");
            }
        }
    }

    private String leerNombreValido(Scanner scanner, String mensaje) {
        while (true) {
            String nombre = leerTexto(scanner, mensaje);
            if (nombre.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+")) {
                return nombre;
            }
            System.out.println("Nombre invalido. Usa solo letras y espacios.");
        }
    }

    private String leerCorreoValido(Scanner scanner, String mensaje) {
        while (true) {
            String correo = leerTexto(scanner, mensaje);
            if (correo.contains("@") && correo.indexOf('@') > 0 && correo.indexOf('@') < correo.length() - 1) {
                return correo;
            }
            System.out.println("Correo invalido. Debe incluir @.");
        }
    }

    private String leerIdEmpleadoValido(Scanner scanner, String mensaje) {
        while (true) {
            String id = leerTexto(scanner, mensaje).toUpperCase();
            if (id.matches("C\\d{3}")) {
                return id;
            }
            System.out.println("Id invalido. Usa el formato C000.");
        }
    }

    private String leerFechaValida(Scanner scanner, String mensaje) {
        while (true) {
            String fecha = leerTexto(scanner, mensaje);
            if (fecha.matches("\\d{2}/\\d{2}/\\d{2}")) {
                return fecha;
            }
            System.out.println("Fecha invalida. Usa formato 00/00/00.");
        }
    }

    private String leerTelefonoValido(Scanner scanner, String mensaje) {
        while (true) {
            String telefono = leerTexto(scanner, mensaje);
            if (telefono.matches("\\d+")) {
                return telefono;
            }
            System.out.println("Telefono invalido. Usa solo numeros.");
        }
    }

    private String leerIdProveedorExistente(Scanner scanner, String mensaje) {
        while (true) {
            String id = leerTexto(scanner, mensaje);
            for (Proveedor proveedor : servicio.getProveedores()) {
                if (proveedor.getId().equals(id)) {
                    return id;
                }
            }
            System.out.println("Proveedor no encontrado. Registra uno en la opcion 8.");
        }
    }
}
