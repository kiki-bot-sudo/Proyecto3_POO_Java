package com.tienda.exceptions;
import java.io.IOException;

public class CSVParseException extends IOException {

    public CSVParseException(String mensaje) {
        super(mensaje);
    }

    public CSVParseException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
