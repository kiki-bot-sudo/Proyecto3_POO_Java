package com.tienda.persistencia;

import com.tienda.empleados.Cajero;
import com.tienda.empleados.Empleado;
import com.tienda.empleados.Gerente;
import com.tienda.exceptions.EmpleadoException;
import com.tienda.exceptions.SalarioInvalidoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoCSV {

    private static final String RUTA = "empleados.csv";

    // ✅ Escribe la lista completa al archivo
    public static void escribir(List<Empleado> empleados) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA))) {
            writer.write("id,nombre,salario,puesto,extra1,extra2");
            writer.newLine();
            for (Empleado e : empleados) {
                writer.write(e.toCSV());
                writer.newLine();
            }
        }
    }

    // ✅ Lee el archivo y reconstruye la lista usando fromCSV()
    public static List<Empleado> leer() throws IOException {
        List<Empleado> lista = new ArrayList<>();
        File archivo = new File(RUTA);

        if (!archivo.exists())
            return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                } // salta encabezado
                if (linea.trim().isEmpty())
                    continue;

                String[] campos = linea.split(",");
                String puesto = campos[3].trim();

                try {
                    if (puesto.equalsIgnoreCase("Cajero")) {
                        lista.add(Cajero.fromCSV(linea));
                    } else if (puesto.equalsIgnoreCase("Gerente")) {
                        lista.add(Gerente.fromCSV(linea));
                    }
                } catch (SalarioInvalidoException | EmpleadoException e) {
                    System.err.println("Error al leer línea: " + linea + " → " + e.getMessage());
                }
            }
        }
        return lista;
    }
}
