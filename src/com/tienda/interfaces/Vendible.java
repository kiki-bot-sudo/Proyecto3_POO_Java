package com.tienda.interfaces;

public interface Vendible {
    double calcularPrecioFinal();
    String generarEtiqueta();

    boolean estaDisponible();
}
