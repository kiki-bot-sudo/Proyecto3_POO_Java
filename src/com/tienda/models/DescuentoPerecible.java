package com.tienda.models;
import com.tienda.interfaces.StrategiaPrecio ;

public class DescuentoPerecible implements StrategiaPrecio {
    private static final double UMBRAL_LIQUIDACION  = 50.0;
    private static final double PORCENTAJE_EXTRA    = 0.05;

    @Override
    public double calcular(double precioBase, double porcentaje, int cantidad) {
        double precio = precioBase - (precioBase * (porcentaje / 100));

        if (porcentaje > UMBRAL_LIQUIDACION) {
            precio = precio - (precio * PORCENTAJE_EXTRA);
        }

        return Math.round(precio * 100.0) / 100.0;
    }
}
