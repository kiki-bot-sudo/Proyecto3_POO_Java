package com.tienda.models;

import com.tienda.exceptions.ProductoException;
import com.tienda.interfaces.Vendible;

abstract class Producto implements Vendible{

    private String codigo; 
    private String nombre;
    private double precio;
    private int cantidad;

    
    public Producto(String codigo, String nombre, int cantidad, double precio )throws ProductoException { 
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ProductoException("El código del producto no puede estar vacío");
        }
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoException("El nombre del producto no puede estar vacío ");
        }
    
        if (precio <= 0) {
            throw new ProductoException("El precio debe ser mayor a 0" + precio);
        }
        if (cantidad < 0) {
            throw new ProductoException(" La cantidad debe ser mayor a 0 " + cantidad  );
        }
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigo = codigo;
        this.nombre = nombre;


    }

    //metodos x
    @Override
    public abstract double calcularPrecioFinal();
    @Override
    public abstract boolean estaDisponible();

    public abstract String getCategoria();

    //Metodos 
    //nuevo
    public void reducirCantidad(int unidades) throws  ProductoException{
        if (unidades > cantidad) {
            throw new ProductoException("Error la cantidad es insuficiente");
        }
        cantidad -= unidades;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + " |Nombre: " + nombre + " |Precio: $: " + precio + " |Cantidad: " + cantidad;
    }

    // agregar al UMl Persistencia 
    public String toCSV() {
        return new StringBuilder()
            .append(codigo).append(",").append(nombre).append(",").append(precio)
            .append(",").append(cantidad).toString();
    }


    //getter js
    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }

    // setter 
    public void setCantidad(int cantidad)throws ProductoException {
        if (cantidad < 0) {
            throw new ProductoException("La cantidad debe no debe ser negativo" + cantidad );
        }
        this.cantidad = cantidad;
    }
    public void setPrecio(double precio)throws ProductoException {

        if (precio < 0) {
            throw new ProductoException("Error el precio no puede ser negativo" + precio );
        }
        this.precio = precio;
    }

    

}
