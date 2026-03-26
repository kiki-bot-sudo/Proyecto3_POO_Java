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

    @Override
    public String toString() {
        return "Proveedor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
