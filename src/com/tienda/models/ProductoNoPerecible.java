package com.tienda.models;

import com.tienda.exceptions.ProductoException;

public class ProductoNoPerecible extends Producto  {
    private String categoria;
    private double promocion; 
    

    
    public ProductoNoPerecible(String codigo, String nombre, String categoria, int cantidad, double precio, double promocion)throws ProductoException{
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

     @Override
    public boolean estaDisponible() {
        return getCantidad() > 0;
    }

    //metodos nuevos 
    public void setEstrategiaPrecio(PrecioStrategy estrategiaPrecio) {
        this.estrategiaPrecio = estrategiaPrecio;
    }

    @Override
    public double calcularPrecioFinal(){
        return estrategiaPrecio.calcular(getPrecio(), promocion, getCantidad());
    }

    @Override
    public String toCSV() {
        return "No Perecible," + super.toCSV() + "," + categoria + "," + promocion;                
    }

    public void setPromocion(double promocion)throws ProductoException {
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

    public static ProductoNoPerecible fromCSV(String linea) throws ProductoException  {
        String[] p = linea.split(",");

        if (p.length < 7) {
            throw new ProductoException("Línea inválida para ProductoNoPerecible (se esperan o se necesitan 7 campos): " + linea);
        }

    
        double precio;
        int    cantidad;
        double descuento;
        try {
            precio    = Double.parseDouble(p[3].trim());
            cantidad  = Integer.parseInt(p[4].trim());
            descuento = Double.parseDouble(p[6].trim());  
        } catch (NumberFormatException e) {
            throw new ProductoException("Datos numéricos inválidos en la linea: " + linea);
        }
        return new ProductoNoPerecible(
            p[1].trim(), p[2].trim(), p[5].trim(), 
            cantidad,precio,descuento      
        );
    }
    


}
