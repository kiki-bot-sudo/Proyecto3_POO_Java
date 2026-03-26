package com.tienda.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.tienda.models.Producto;
import com.tienda.models.ProductoPerecible;
import com.tienda.models.ProductoNoPerecible;
import com.tienda.exceptions.ProductoException;

public class ProductoCSV {
    private String ruta;

    public ProductoCSV(String ruta) {
        this.ruta = ruta;
    }

    public void guardar(List<Producto> productos) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            for (Producto p : productos) {
                pw.println(p.toCSV());
            }
        }
    }

    public List<Producto> cargar() throws IOException {
        List<Producto> lista = new ArrayList<>();
        File archivo = new File(ruta);

        if (!archivo.exists()) return lista; 

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                try {
                    String tipo = linea.split(",")[0].trim();
                    if (tipo.equals("PERECIBLE")) {
                        lista.add(ProductoPerecible.fromCSV(linea));

                    } else if (tipo.equals("NOPERECIBLE")) {
                        lista.add(ProductoNoPerecible.fromCSV(linea));
                    }
                } catch (ProductoException e) {
                    System.out.println("Linea ignorada por datos invalidos: " + e.getMessage());


                } catch (IllegalArgumentException e) {

                    System.out.println("Linea ignorada por formato incorrecto: " + e.getMessage());
                }
            }
        }
        return lista;
    }
}
