package com.tienda.exceptions;

public class ClienteNoEncontradoException extends Exception {
    public ClienteNoEncontradoException(String id) {
        super("No se encontro un cliente con id: " + id);
    }
}