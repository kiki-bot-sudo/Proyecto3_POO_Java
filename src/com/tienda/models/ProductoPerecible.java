package com.tienda.models;

import com.tienda.exceptions.ProductoException;

public class ProductoPerecible extends Producto {
    private String fechaVencimiento;
    private double descuento;
    private String categoria;

    public ProductoPerecible(String codigo, String nombre, String fechaVencimiento, String categoria, int cantidad, double precio, double descuento) throws ProductoException{
        super(codigo, nombre, cantidad, precio);

        if (fechaVencimiento == null || fechaVencimiento.trim().isEmpty()){
            throw new ProductoException("La fecha de vencimiento no puede estar vacía ");
        }
        if (categoria == null || categoria.trim().isEmpty()){
            throw new ProductoException("Error la categoría no puede estar vacía ");
        }
        if (descuento < 0 ){
            throw new ProductoException(" Error el descuento tiene que ser entre 0 y 100 %");
        }

        this.categoria = categoria;
        this.descuento =descuento;
        this.fechaVencimiento = fechaVencimiento;

    }

    //agregar al UML
    public void setDescuento(double descuento)throws ProductoException {
        if (descuento < 0 || descuento > 100){
            throw new ProductoException(" Error el descuento tiene que ser entre 0 y 100");
        }
        this.descuento = descuento;
    }

    // agregar 
    @Override
    public boolean estaDisponible() {
        return getCantidad() > 0;
    }
    public void setEstrategiaPrecio(PrecioStrategy estrategiaPrecio) {
            this.estrategiaPrecio = estrategiaPrecio;
    }

    @Override
    public double calcularPrecioFinal() {
        return estrategiaPrecio.calcular(getPrecio(), descuento, getCantidad());
    }
    

    @Override
    public String generarEtiqueta(){
        return "Producto: " + getNombre() + " codigo: " + getCodigo() + "Descuento de: "+ descuento + "Precio total: " + calcularPrecioFinal();
    }
    @Override
    public String toString() {
        return super.toString() + " Vence: " + fechaVencimiento+ " Descuento: " + descuento + " % " + "Categoria" + categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
    public double getDescuento() {
        return descuento;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    @Override
    public String toCSV() {
        return "PERECIBLE," + super.toCSV()
            
            + "," + fechaVencimiento + "," + categoria + "," + descuento;                    
    }

    public static ProductoPerecible fromCSV(String linea) throws ProductoException  {
        String[] p = linea.split(",");

        if (p.length < 8) {
            throw new ProductoException("Linea inválida para ProductoPerecible (se esperan o se necesitan 8 campos): " + linea);
        }


        double precio;
        int    cantidad;
        double descuento;
        try {
            precio    = Double.parseDouble(p[3].trim());
            cantidad  = Integer.parseInt(p[4].trim());
            descuento = Double.parseDouble(p[7].trim());
        } catch (NumberFormatException e) {
            throw new ProductoException("Datos numéricos inválidos en la linea: " + linea);
        }

        return new ProductoPerecible(
            p[1].trim(),p[2].trim(),p[5].trim(),p[6].trim(),   
            cantidad,precio, descuento      
        );
    }
    


    



}

