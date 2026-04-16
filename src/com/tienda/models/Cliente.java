package com.tienda.models;

import com.tienda.exceptions.CSVParseException;

public class Cliente {
    private String id;
    private String nombre;
    private String email;
    

    public Cliente (String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        
    }
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
   
    public static Cliente fromCSV(String linea) throws CSVParseException {
        String[] campos = linea.split(",");
        if (campos.length < 3)
            throw new CSVParseException("Línea CSV inválida para Cliente: " + linea);

        String id = campos[0].trim();
        String nombre = campos[1].trim();
        String email = campos[2].trim();

        return new Cliente(id, nombre, email);
    }
    
    public String toCSV() {
        return id + "," + nombre + "," + email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

