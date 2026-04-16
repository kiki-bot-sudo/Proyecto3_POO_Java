package com.tienda.persistencia;


import com.tienda.exceptions.CSVParseException;
import com.tienda.models.Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClienteCSV {

    private static final Path RUTA = Paths.get("clientes.csv");

    public static void escribir(List<Cliente> clientes) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(RUTA, StandardCharsets.UTF_8)) {
            writer.write("id,nombre,email");
            writer.newLine();
            for (Cliente c : clientes) {
                writer.write(c.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<Cliente> leer() throws CSVParseException {
        List<Cliente> lista = new ArrayList<>();

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
                    lista.add(Cliente.fromCSV(linea));
                } catch (CSVParseException e) {
                    System.err.println("Error al parsear Cliente en CSV: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CSVParseException("Error al leer clientes.csv: " + e.getMessage(), e);
        }

        return lista;
    }
}
