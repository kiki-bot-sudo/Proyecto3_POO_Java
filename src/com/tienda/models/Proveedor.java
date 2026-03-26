package com.tienda.models;

public class Proveedor {
    private String id;
    private String nombre;
    private String telefono;

    public Proveedor(String id, String nombre, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre; 
    }

    public String getTelefono(){
        return telefono;
    }
public String toCSV() {
    return id + "," + nombre + "," + telefono; // tus atributos
}

public static Proveedor fromCSV(String linea) {
    String[] campos = linea.split(",");
    
    if (campos.length < 3)                         
        throw new RuntimeException("Línea CSV inválida para Proveedor: " + linea);

    String id = (campos[0].trim());
    String nombre = campos[1].trim();
    String telefono = campos[2].trim();
    return new Proveedor(id, nombre, telefono);
}

    @Override
    public String toString() {
        return "Proveedor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
