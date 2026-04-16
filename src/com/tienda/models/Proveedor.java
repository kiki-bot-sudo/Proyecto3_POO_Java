package com.tienda.models;
import com.tienda.exceptions.CSVParseException;
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
    return id + "," + nombre + "," + telefono; 
}

public static Proveedor fromCSV(String linea) throws CSVParseException {
    try {
        String[] campos = linea.split(",");

        if (campos.length < 3) {
            throw new CSVParseException("Línea CSV inválida: " + linea);
        }

        String id = campos[0].trim();
        String nombre = campos[1].trim();
        String telefono = campos[2].trim();

        
        if (id.isEmpty())
            throw new CSVParseException("ID vacío en línea: " + linea);

        if (nombre.isEmpty())
            throw new CSVParseException("Nombre vacío en línea: " + linea);

        if (telefono.isEmpty())
            throw new CSVParseException("Teléfono vacío en línea: " + linea);

        
        if (!telefono.matches("\\d+")) {
            throw new CSVParseException("Teléfono inválido: " + telefono);
        }

        return new Proveedor(id, nombre, telefono);

    } catch (Exception e) {
        
        throw new CSVParseException("Error al parsear proveedor: " + linea, e);
    }
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
