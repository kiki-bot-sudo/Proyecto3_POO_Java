package com.tienda.exceptions;

public class EmpleadoNoEncontradoException extends Exception {
    public EmpleadoNoEncontradoException(String id) {
        super("No se encontro un empleado con id: " + id);
    }
}
