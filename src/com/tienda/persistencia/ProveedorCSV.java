package com.tienda.persistencia;

import com.tienda.models.Proveedor; 

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorCSV {

    private static final String RUTA = "proveedores.csv";

    public static void escribir(List<Proveedor> proveedores) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA))) {
            writer.write("id,nombre,telefono"); 
            writer.newLine();
            for (Proveedor p : proveedores) {
                writer.write(p.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<Proveedor> leer() throws IOException {
        List<Proveedor> lista = new ArrayList<>();
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
                    lista.add(Proveedor.fromCSV(linea));
                } catch (Exception e) {
                    System.err.println("Error al leer línea: " + linea + " → " + e.getMessage());
                }
            }
        }
        return lista;
    }
}
