package com.tienda.models;

import com.tienda.exceptions.ProductoException;

public class ProductoNoPerecible extends Producto {
    private String categoria;
    private double promocion; 
    

    
    public ProductoNoPerecible(String codigo, String nombre, String categoria, int cantidad, double precio, double promocion) throws ProductoException {
        super(codigo, nombre, cantidad, precio);
        if (categoria == null || categoria.trim().isEmpty()){
            throw new ProductoException("Error la categoría no puede estar vacía ");
        }
        if(promocion < 0 || promocion > 100){
            throw new ProductoException("Error la promocion no puede ser negativo debe de ser entre 0 y 100 %");
        }
        this.categoria = categoria;
        this.promocion = promocion;
    }

    public void setPromocion(double promocion) throws ProductoException {
        if(promocion < 0 || promocion > 100){
            throw new ProductoException("Error la promocion no puede ser negativo debe de ser entre 0 y 100 %");
        }
        this.promocion = promocion;
    }
    
    public double getPromocion() {
        return promocion;
    }
    
    public String getCategoria() {
        return categoria;
    }

    @Override
    public double calcularPrecioFinal(){
        return getPrecio() - (getPrecio() * (promocion /100)) ;
    }
    
    @Override
    public String generarEtiqueta(){
        return "Producto: " + getNombre() + " codigo: " + getCodigo()  + "Precio: " + calcularPrecioFinal();
    }

    @Override
    public String toString() {
        return super.toString() + " Promocion: " + promocion +" % " + "Categoria" + categoria;
    }
}
