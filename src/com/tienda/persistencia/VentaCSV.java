package com.tienda.persistencia;

import com.tienda.empleados.Empleado;
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

    public static void escribir(List<Venta> ventas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA))) {
            writer.write("idVenta,idCliente,idEmpleado,fecha,total");
            writer.newLine();

            for (Venta venta : ventas) {
                writer.write(venta.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<Venta> leer(List<Cliente> clientes, List<Empleado> empleados) throws IOException {
        List<Venta> ventas = new ArrayList<>();
        File archivo = new File(RUTA);

        if (!archivo.exists()) {
            return ventas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] campos = linea.split(",");
                if (campos.length < 5) {
                    continue;
                }

                String idCliente = campos[1].trim();
                String idEmpleado = campos[2].trim();

                Cliente cliente = buscarClientePorId(clientes, idCliente);
                Empleado empleado = buscarEmpleadoPorId(empleados, idEmpleado);

                if (cliente == null || empleado == null) {
                    continue;
                }

                ventas.add(Venta.fromCSV(linea, cliente, empleado));
            }
        }

        return ventas;
    }

    private static Cliente buscarClientePorId(List<Cliente> clientes, String idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }

    private static Empleado buscarEmpleadoPorId(List<Empleado> empleados, String idEmpleado) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(idEmpleado)) {
                return empleado;
            }
        }
        return null;
    }
}