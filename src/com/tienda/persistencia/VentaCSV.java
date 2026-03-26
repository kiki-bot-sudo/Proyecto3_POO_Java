package com.tienda.persistencia;

import com.tienda.empleados.Empleado;
import com.tienda.exceptions.ClienteNoEncontradoException;
import com.tienda.exceptions.EmpleadoNoEncontradoException;
import com.tienda.models.Cliente;
import com.tienda.models.Venta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VentaCSV {

    private static final String RUTA = "ventas.csv";
    private static final String HEADER = "idVenta,idCliente,idEmpleado,fecha,total";

    public static void escribir(List<Venta> ventas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA))) {
            writer.write(HEADER);
            writer.newLine();
            for (Venta venta : ventas) {
                writer.write(venta.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<Venta> leer(List<Cliente> clientes, List<Empleado> empleados) throws IOException {
        List<Venta> ventas = new ArrayList<>();

        if (!new File(RUTA).exists()) {
            return ventas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA))) {
            reader.readLine();
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                try {
                    ventas.add(parsearVenta(linea, clientes, empleados));
                } catch (ClienteNoEncontradoException | EmpleadoNoEncontradoException | IllegalArgumentException e) {
                    System.err.println("Error al reconstruir venta desde CSV: " + e.getMessage());
                }
            }
        }

        return ventas;
    }

    private static Venta parsearVenta(String linea, List<Cliente> clientes, List<Empleado> empleados)
            throws ClienteNoEncontradoException, EmpleadoNoEncontradoException {
        String[] campos = linea.split(",");
        if (campos.length != 5) {
            throw new IllegalArgumentException("Linea CSV invalida para Venta: " + linea);
        }

        String idCliente = campos[1].trim();
        String idEmpleado = campos[2].trim();
        Cliente cliente = buscarClientePorId(clientes, idCliente);
        Empleado empleado = buscarEmpleadoPorId(empleados, idEmpleado);
        return Venta.fromCSV(linea, cliente, empleado);
    }

    private static Cliente buscarClientePorId(List<Cliente> clientes, String idCliente)
            throws ClienteNoEncontradoException {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }
        throw new ClienteNoEncontradoException(idCliente);
    }

    private static Empleado buscarEmpleadoPorId(List<Empleado> empleados, String idEmpleado)
            throws EmpleadoNoEncontradoException {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(idEmpleado)) {
                return empleado;
            }
        }
        throw new EmpleadoNoEncontradoException(idEmpleado);
    }
}