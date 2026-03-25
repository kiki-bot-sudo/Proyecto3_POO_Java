package com.tienda.exceptions;

public class SinCodigoEncontradoException extends Exception {
    public SinCodigoEncontradoException(String codigo) {
        super("No se encuentra el codigo del producto: " + codigo);
    }
}
