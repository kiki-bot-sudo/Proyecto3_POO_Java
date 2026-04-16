package com.tienda.persistencia;

import com.tienda.exceptions.CSVParseException;
import com.tienda.models.Proveedor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProveedorCSV {

        private static final Path RUTA = Paths.get("proveedores.csv");

    public static void escribir(List<Proveedor> proveedores) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(RUTA, StandardCharsets.UTF_8)) {
            writer.write("id,nombre,telefono");
            writer.newLine();
            for (Proveedor p : proveedores) {
                writer.write(p.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<Proveedor> leer() throws CSVParseException {
        List<Proveedor> lista = new ArrayList<>();

        if (!Files.exists(RUTA)) {
            return lista;
        }

        try (BufferedReader reader = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {
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

                try {
                    lista.add(Proveedor.fromCSV(linea));
                } catch (CSVParseException e) {
                    System.err.println("Error al parsear Proveedor en CSV: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CSVParseException("Error al leer proveedores.csv: " + e.getMessage(), e);
        }

        return lista;
    }
}
