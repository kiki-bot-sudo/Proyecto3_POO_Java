package com.tienda.persistencia;


import com.tienda.exceptions.CSVParseException;
import com.tienda.models.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteCSV{
    
    private static final String RUTA = "clientes.csv";

    public static void escribir(List<Cliente> clientes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA))) {
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
                }

                if (linea.trim().isEmpty())
                    continue;

                try {
                lista.add(Cliente.fromCSV(linea));
            } catch (CSVParseException e) {
                System.err.println("Error al parsear línea: " + linea );
            }
        }
             } catch (IOException e) {
          
            throw new CSVParseException("Error al leer el archivo CSV: " + e.getMessage());
        }
    return lista;
}
            
}
